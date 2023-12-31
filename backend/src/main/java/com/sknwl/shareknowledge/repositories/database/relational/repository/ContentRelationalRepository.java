package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.ContentRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.*;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ContentRelationalRepository implements ContentRepository {
    private final ContentJpaRepository contentJpaRepository;
    private final ContentRatingJpaRepository contentRatingJpaRepository;
    private final StudyFieldJpaRepository studyFieldJpaRepository;
    private final ContentPriceJpaRepository contentPriceJpaRepository;
    private final SourceJpaRepository sourceJpaRepository;

    private final ContentRepositoryMapper mapper = ContentRepositoryMapper.INSTANCE;

    public ContentRelationalRepository(ContentJpaRepository contentJpaRepository, ContentRatingJpaRepository contentRatingJpaRepository, StudyFieldJpaRepository studyFieldJpaRepository, ContentPriceJpaRepository contentPriceJpaRepository, SourceJpaRepository sourceJpaRepository) {
        this.contentJpaRepository = contentJpaRepository;
        this.contentRatingJpaRepository = contentRatingJpaRepository;
        this.studyFieldJpaRepository = studyFieldJpaRepository;
        this.contentPriceJpaRepository = contentPriceJpaRepository;
        this.sourceJpaRepository = sourceJpaRepository;
    }

    @Transactional
    @Override
    public Content register(Content content) {
        ContentModel contentModel = mapper.map(content);
        for (CoverUrlModel url : contentModel.getCoverImage().getUrls()) {
            url.setCoverImage(contentModel.getCoverImage());
        }

        var studyFieldModelOptional = studyFieldJpaRepository.findFirstByNameIgnoreCase(content.getStudyField().getName());
        var studyFieldModel = studyFieldModelOptional.orElseGet(() -> studyFieldJpaRepository.save(contentModel.getStudyField()));
        contentModel.setStudyField(studyFieldModel);

        var sourceModelOptional = sourceJpaRepository.findFirstByWebSiteUri(content.getSource().getWebSiteUri());
        var sourceModel = sourceModelOptional.orElseGet(() -> sourceJpaRepository.save(contentModel.getSource()));
        contentModel.setSource(sourceModel);

        var createdContent = contentJpaRepository.save(contentModel);
        createdContent.getPrice().setContent(createdContent);
        contentPriceJpaRepository.save(createdContent.getPrice());

        return mapper.map(createdContent);
    }

    @Transactional
    @Override
    public Content update(Content content) {
        var contentModel = getContent(content.getId());
        mapper.update(content, contentModel);

        contentJpaRepository.save(contentModel);
        return mapper.map(contentModel);
    }

    @Transactional
    @Override
    public void hardDelete(Long id) {
        getContent(id);
        contentJpaRepository.deleteById(id);
    }

    @Override
    public Content get(Long id) {
        var contentModel = getContent(id);
        var summary = getSummary(id);
        if (summary != null) {
            contentModel.setReviewers(summary.getCount());
            contentModel.setRating(summary.getAverage());
        } else{
            contentModel.setReviewers(0L);
            contentModel.setRating(0.0);
        }
        return mapper.map(contentModel);
    }

    @Override
    public Page<Content> list(Pageable pageable) {
        var contentsModel = contentJpaRepository.findAll(pageable);
        var summaries = listSummary(contentsModel
                .stream()
                .map(ContentModel::getId)
                .toList());

        var contents = contentsModel
                .stream()
                .map(contentModel -> {
                    var ratingSummary = summaries.getOrDefault(contentModel.getId(), new ContentModelSummary(contentModel, 0L, 0.0));
                    contentModel.setReviewers(ratingSummary.getCount());
                    contentModel.setRating(ratingSummary.getAverage());
                    return mapper.map(contentModel);
                })
                .toList();

        return new PageImpl<>(contents);
    }

    public Page<Content> list(Pageable pageable, SortType sort, String keyphrase, Integer minRatings, List<ContentType> contentTypes, Boolean isFree, List<Long> sourceIds, List<Long> languageIds, Integer minDuration, Integer maxDuration, List<String> fields) {

        var contentsSummary = contentJpaRepository.findContents(keyphrase, contentTypes, isFree, sourceIds, languageIds, minDuration, maxDuration, minRatings, fields, sort.name(), pageable);
        var contents = contentsSummary.getContent().stream().map(contentModelSummary -> {
            var contentModel = contentModelSummary.getContent();
            contentModel.setReviewers(contentModelSummary.getCount());
            contentModel.setRating(contentModelSummary.getAverage());
            return mapper.map(contentModel);
        }).toList();
        return new PageImpl<>(contents, pageable, contentsSummary.getTotalElements());
    }

    private ContentModel getContent(Long id) {
        return contentJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified content"));
    }

    private ContentModelSummary getSummary(Long contentId) {
        return contentRatingJpaRepository.findRatingCountAndAverageByContentId(contentId);
    }

    private Map<Long, ContentModelSummary> listSummary(List<Long> contentIds) {
        return contentRatingJpaRepository.findRatingCountAndAverageByContentIds(contentIds);
    }

    @Transactional
    @Override
    public ContentRating registerRating(ContentRating contentRating) {
        ContentRatingModel contentRatingModel = mapper.map(contentRating);
        contentRatingJpaRepository.save(contentRatingModel);
        return mapper.map(contentRatingModel);
    }

    @Transactional
    @Override
    public ContentRating updateRating(ContentRating contentRating) {
        ContentRatingModel contentRatingModel = getRatingIfExists(contentRating.getId());
        mapper.update(contentRating, contentRatingModel);

        contentRatingJpaRepository.save(contentRatingModel);
        return mapper.map(contentRatingModel);
    }

    @Transactional
    @Override
    public void deleteRating(Long id) {
        getRatingIfExists(id);
        contentRatingJpaRepository.deleteById(id);

    }

    @Override
    public ContentRating getRating(Long id) {
        var ratingModel = getRatingIfExists(id);
        return mapper.map(ratingModel);
    }

    @Override
    public List<ContentRating> listRating() {
        return contentRatingJpaRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    private ContentRatingModel getRatingIfExists(Long id) {
        return contentRatingJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified rating"));
    }

}

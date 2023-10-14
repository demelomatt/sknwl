package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.ContentRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentRatingModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.ContentJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.ContentRatingJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.LanguageJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.SourceJpaRepository;
import com.sknwl.shareknowledge.repositories.model.RatingSummary;
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
    private final SourceJpaRepository sourceJpaRepository;
    private final LanguageJpaRepository languageJpaRepository;
    private final ContentRatingJpaRepository contentRatingJpaRepository;
    private final ContentRepositoryMapper mapper = ContentRepositoryMapper.INSTANCE;

    public ContentRelationalRepository(ContentJpaRepository contentJpaRepository, SourceJpaRepository sourceJpaRepository, LanguageJpaRepository languageJpaRepository, ContentRatingJpaRepository contentRatingJpaRepository) {
        this.contentJpaRepository = contentJpaRepository;
        this.sourceJpaRepository = sourceJpaRepository;
        this.languageJpaRepository = languageJpaRepository;
        this.contentRatingJpaRepository = contentRatingJpaRepository;
    }

    @Transactional
    @Override
    public Content publish(Content content) {
        ContentModel contentModel = mapper.map(content);
        contentJpaRepository.save(contentModel);
        return mapper.map(contentModel);
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
                    var ratingSummary = summaries.getOrDefault(contentModel.getId(), new RatingSummary(contentModel.getId(), 0L, 0.0));
                    contentModel.setReviewers(ratingSummary.getCount());
                    contentModel.setRating(ratingSummary.getAverage());
                    return mapper.map(contentModel);
                })
                .toList();

        return new PageImpl<>(contents);
    }

    public Page<Content> list(Pageable pageable, SortType sort, String keyphrase, Integer minRatings, List<ContentType> contentTypes, Long sourceId, Long languageId, Integer minDuration, Integer maxDuration) {
        var contentsSummary = contentJpaRepository.findContents(keyphrase, contentTypes, sourceId, languageId, minDuration, maxDuration, minRatings, sort.name(), pageable);
        var contents = contentsSummary.getContent().stream().map(contentModelSummary -> {
            var contentModel = contentModelSummary.getContent();
            contentModel.setReviewers(contentModelSummary.getCount());
            contentModel.setRating(contentModelSummary.getAverage());
            return mapper.map(contentModel);
        }).toList();
        return new PageImpl<>(contents);
    }

    private ContentModel getContent(Long id) {
        return contentJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified content"));
    }

    private RatingSummary getSummary(Long contentId) {
        return contentRatingJpaRepository.findRatingCountAndAverageByContentId(contentId);
    }

    private Map<Long, RatingSummary> listSummary(List<Long> contentIds) {
        return contentRatingJpaRepository.findRatingCountAndAverageByContentIds(contentIds);
    }

    @Transactional
    @Override
    public ContentRating newRating(ContentRating contentRating) {
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

    @Transactional
    @Override
    public Source newSource(Source source) {
        SourceModel sourceModel = mapper.map(source);
        sourceJpaRepository.save(sourceModel);
        return mapper.map(sourceModel);
    }

    @Transactional
    @Override
    public Source updateSource(Source source) {
        var sourceModel = getSourceIfExists(source.getId());
        mapper.update(source, sourceModel);

        sourceJpaRepository.save(sourceModel);
        return mapper.map(sourceModel);
    }

    @Transactional
    @Override
    public void deleteSource(Long id) {
        getSourceIfExists(id);
        sourceJpaRepository.deleteById(id);
    }

    @Override
    public Source getSource(Long id) {
        var sourceModel = getSourceIfExists(id);
        return mapper.map(sourceModel);
    }

    @Override
    public List<Source> listSource(String uri) {
        return sourceJpaRepository.findTop30ByWebSiteUriContainingIgnoreCase(uri)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<Source> listSource(Pageable pageable) {
        var sources = sourceJpaRepository.findAll(pageable)
                .stream()
                .map(mapper::map)
                .toList();
        return new PageImpl<>(sources);
    }

    private SourceModel getSourceIfExists(Long id) {
        return sourceJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified source"));
    }

    @Override
    public List<Language> listLanguageByName(String name) {
        return languageJpaRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Language> listLanguageByCode(String code) {
        return languageJpaRepository.findByCodeContainingIgnoreCase(code)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Language> listLanguage() {
        return languageJpaRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }
}

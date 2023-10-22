package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentUseCase {

    private final ContentRepository contentRepository;

    public ContentUseCase(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content register(Content content) {
        content.setPublishedDateTime(LocalDateTime.now());
        return contentRepository.register(content);
    }

    public Content update(Content content) {
        return contentRepository.update(content);
    }

    public Page<Content> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return contentRepository.list(pageable);
    }

    public Page<Content> list(Integer pageNumber, Integer pageSize, SortType sort, String keyphrase, Integer minRatings, List<ContentType> contentTypes, Long sourceId, Long languageId, Integer minDuration, Integer maxDuration) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        if (contentTypes == null) {
            contentTypes = List.of(ContentType.values());
        }
        return contentRepository.list(pageable, sort, keyphrase, minRatings, contentTypes, sourceId, languageId, minDuration, maxDuration);
    }

    public void delete(Long id) {
        contentRepository.hardDelete(id);
    }

    public Content get(Long id) {
        return contentRepository.get(id);
    }

    public ContentRating registerRating(ContentRating contentRating) {
        contentRating.setRatingDateTime(LocalDateTime.now());
        return contentRepository.registerRating(contentRating);
    }

    public ContentRating updateRating(ContentRating contentRating) {
        return contentRepository.updateRating(contentRating);
    }

    public void deleteRating(Long id) {
        contentRepository.deleteRating(id);
    }

    public ContentRating getRating(Long id) {
        return contentRepository.getRating(id);
    }

    public List<ContentRating> listRating() {
        return contentRepository.listRating();
    }
}

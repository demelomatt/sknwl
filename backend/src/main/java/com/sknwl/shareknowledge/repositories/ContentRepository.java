package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentRepository {
    Content register(Content content);
    Content update(Content content);
    void hardDelete(Long id);
    Content get(Long id);
    Page<Content> list(Pageable pageable);
    Page<Content> list(Pageable pageable, SortType sort, String keyphrase, Integer minRatings, List<ContentType> contentTypes, Long sourceId, Long languageId, Integer minDuration, Integer maxDuration);

    ContentRating registerRating(ContentRating contentRating);
    ContentRating updateRating(ContentRating contentRating);
    void deleteRating(Long id);
    ContentRating getRating(Long id);
    List<ContentRating> listRating();
}

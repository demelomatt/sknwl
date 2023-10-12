package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentRepository {
    Content publish(Content content);
    Content update(Content content);
    void hardDelete(Long id);
    Content get(Long id);
    Page<Content> list(Pageable pageable);

    ContentRating newRating(ContentRating contentRating);
    ContentRating updateRating(ContentRating contentRating);
    void deleteRating(Long id);
    ContentRating getRating(Long id);
    List<ContentRating> listRating();

    Source newSource(Source source);

    Source updateSource(Source source);

    void deleteSource(Long id);

    Source getSource(Long id);
    List<Source> listSource(String uri);
    Page<Source> listSource(Pageable pageable);
    List<Language> listLanguageByName(String name);
    List<Language> listLanguageByCode(String code);
    List<Language> listLanguage();
}

package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
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

    public Content create(Content content) {
        content.setPublishedDateTime(LocalDateTime.now());
        return contentRepository.publish(content);
    }

    public Content update(Content content) {
        return contentRepository.update(content);
    }

    public Page<Content> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return contentRepository.list(pageable);
    }

    public void delete(Long id) {
        contentRepository.hardDelete(id);
    }

    public Content get(Long id) {
        return contentRepository.get(id);
    }

    public Source newSource(Source source) {
        return contentRepository.newSource(source);
    }

    public Source updateSource(Source source) {
        return contentRepository.updateSource(source);
    }

    public void deleteSource(Long id) {
        contentRepository.deleteSource(id);
    }

    public Source getSource(Long id) {
        return contentRepository.getSource(id);
    }

    public Page<Source> listSource(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return contentRepository.listSource(pageable);
    }

    public List<Source> listSource(String uri) {
        return contentRepository.listSource(uri);
    }

    public List<Language> listLanguageByName(String name) {
        return contentRepository.listLanguageByName(name);
    }

    public List<Language> listLanguageByCode(String name) {
        return contentRepository.listLanguageByCode(name);
    }

    public List<Language> listLanguage() {
        return contentRepository.listLanguage();
    }
}

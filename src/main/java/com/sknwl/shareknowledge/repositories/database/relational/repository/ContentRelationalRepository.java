package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.ContentRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.ContentJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.LanguageJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.SourceJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentRelationalRepository implements ContentRepository {
    private final ContentJpaRepository contentJpaRepository;
    private final SourceJpaRepository sourceJpaRepository;
    private final LanguageJpaRepository languageJpaRepository;
    private final ContentRepositoryMapper mapper = ContentRepositoryMapper.INSTANCE;

    public ContentRelationalRepository(ContentJpaRepository contentJpaRepository, SourceJpaRepository sourceJpaRepository, LanguageJpaRepository languageJpaRepository) {
        this.contentJpaRepository = contentJpaRepository;
        this.sourceJpaRepository = sourceJpaRepository;
        this.languageJpaRepository = languageJpaRepository;
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
        return mapper.map(contentModel);
    }

    @Override
    public Page<Content> list(Pageable pageable) {
        var contents = contentJpaRepository.findAll(pageable)
                .stream()
                .map(mapper::map)
                .toList();
        return new PageImpl<>(contents);
    }

    private ContentModel getContent(Long id) {
        return contentJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified content"));
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

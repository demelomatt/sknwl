package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.SourceRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.SourceRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.SourceJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SourceRelationalRepository implements SourceRepository {
    private final SourceRepositoryMapper mapper = SourceRepositoryMapper.INSTANCE;
    private final SourceJpaRepository sourceJpaRepository;

    public SourceRelationalRepository(SourceJpaRepository sourceJpaRepository) {
        this.sourceJpaRepository = sourceJpaRepository;
    }

    @Transactional
    @Override
    public Source register(Source source) {
        SourceModel sourceModel = mapper.map(source);
        sourceJpaRepository.save(sourceModel);
        return mapper.map(sourceModel);
    }

    @Transactional
    @Override
    public Source update(Source source) {
        var sourceModel = getSourceIfExists(source.getId());
        mapper.update(source, sourceModel);

        sourceJpaRepository.save(sourceModel);
        return mapper.map(sourceModel);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getSourceIfExists(id);
        sourceJpaRepository.deleteById(id);
    }

    @Override
    public Source get(Long id) {
        var sourceModel = getSourceIfExists(id);
        return mapper.map(sourceModel);
    }

    @Override
    public List<Source> list(String uri) {
        return sourceJpaRepository.findTop30ByWebSiteUriContainingIgnoreCase(uri)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<Source> list(Pageable pageable) {
        var sources = sourceJpaRepository.findAll(pageable)
                .stream()
                .map(mapper::map)
                .toList();
        return new PageImpl<>(sources);
    }

    private SourceModel getSourceIfExists(Long id) {
        return sourceJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified source"));
    }
}

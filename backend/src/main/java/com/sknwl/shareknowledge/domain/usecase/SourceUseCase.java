package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.repositories.SourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceUseCase {
    private final SourceRepository coreRepository;

    public SourceUseCase(SourceRepository coreRepository) {
        this.coreRepository = coreRepository;
    }

    public Source register(Source source) {
        return coreRepository.register(source);
    }

    public Source update(Source source) {
        return coreRepository.update(source);
    }

    public void delete(Long id) {
        coreRepository.delete(id);
    }

    public Source get(Long id) {
        return coreRepository.get(id);
    }

    public Page<Source> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return coreRepository.list(pageable);
    }

    public List<Source> list(String uri) {
        return coreRepository.list(uri);
    }
}

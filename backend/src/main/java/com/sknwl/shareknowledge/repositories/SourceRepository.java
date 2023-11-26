package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SourceRepository {
    Source register(Source source);

    Source update(Source source);

    void delete(Long id);

    Source get(Long id);
    List<Source> list(String search);
    Page<Source> list(Pageable pageable);
}

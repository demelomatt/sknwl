package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContentRepository {
    Content publish(Content content);
    Content update(Content content);
    void delete(Long id);
    Optional<Content> get(Long id);
    Page<Content> list(Pageable pageable);
}

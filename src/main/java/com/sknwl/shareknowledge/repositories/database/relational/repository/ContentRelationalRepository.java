package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.ContentJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ContentRelationalRepository implements ContentRepository {
    private final ContentJpaRepository contentJpaRepository;

    public ContentRelationalRepository(ContentJpaRepository contentJpaRepository) {
        this.contentJpaRepository = contentJpaRepository;
    }

    @Override
    public Content publish(Content content) {
       return null;
    }

    @Override
    public Content update(Content content) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Content> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Content> list(Pageable pageable) {
        return null;
    }
}

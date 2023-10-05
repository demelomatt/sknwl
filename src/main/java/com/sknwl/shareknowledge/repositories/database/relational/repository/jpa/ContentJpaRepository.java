package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentJpaRepository extends JpaRepository<ContentModel, Long> {
}

package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.ContentPriceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentPriceJpaRepository extends JpaRepository<ContentPriceModel, Long> {
}

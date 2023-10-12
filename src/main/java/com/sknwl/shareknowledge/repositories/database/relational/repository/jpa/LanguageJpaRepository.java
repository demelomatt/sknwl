package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageJpaRepository extends JpaRepository<LanguageModel, Long> {
    List<LanguageModel> findByNameContainingIgnoreCase(String name);
    List<LanguageModel> findByCodeContainingIgnoreCase(String code);
}

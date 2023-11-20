package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageJpaRepository extends JpaRepository<LanguageModel, Long> {
    List<LanguageModel> findByNameContainingIgnoreCase(String name);
    List<LanguageModel> findByCodeContainingIgnoreCase(String code);

    List<LanguageModel> findByName(String name);

    List<LanguageModel> findByCode(String code);

    @Query("SELECT e FROM LanguageModel e WHERE e.name LIKE %:value% OR e.code LIKE %:value%")
    List<LanguageModel> findByValue(String value);

}

package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.StudyFieldModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyFieldJpaRepository extends JpaRepository<StudyFieldModel, Long> {
    List<StudyFieldModel> findFirst30ByOrderByNameAsc();
    List<StudyFieldModel> findTop30ByNameContainingIgnoreCase(String name);
}

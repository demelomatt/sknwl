package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.StudyGuideModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGuideJpaRepository extends JpaRepository<StudyGuideModel, Long> {
}

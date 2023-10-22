package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.StudyGuide;
import com.sknwl.shareknowledge.repositories.StudyGuideRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.StudyGuideJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudyGuideRelationalRepository implements StudyGuideRepository {
    private final StudyGuideJpaRepository studyGuideJpaRepository;

    public StudyGuideRelationalRepository(StudyGuideJpaRepository studyGuideJpaRepository) {
        this.studyGuideJpaRepository = studyGuideJpaRepository;
    }

    @Override
    public StudyGuide register(StudyGuide studyGuide) {
        return null;
    }

    @Override
    public StudyGuide update(StudyGuide studyGuide) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<StudyGuide> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<StudyGuide> list(Pageable pageable) {
        return null;
    }
}

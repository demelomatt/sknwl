package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.StudyGuide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudyGuideRepository {
    StudyGuide publish(StudyGuide studyGuide);
    StudyGuide update(StudyGuide studyGuide);
    void delete(Long id);
    Optional<StudyGuide> get(Long id);
    Page<StudyGuide> list(Pageable pageable);
}

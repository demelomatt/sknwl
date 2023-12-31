package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.StudyField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyFieldRepository {
    StudyField register(StudyField studyField);
    StudyField update(StudyField studyField);
    void delete(Long id);
    StudyField get(Long id);
    List<StudyField> list(String search);
    Page<StudyField> list(Pageable pageable);
}

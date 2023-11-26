package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.StudyField;
import com.sknwl.shareknowledge.repositories.StudyFieldRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyFieldUseCase {
    private final StudyFieldRepository studyFieldRepository;

    public StudyFieldUseCase(StudyFieldRepository studyFieldRepository) {
        this.studyFieldRepository = studyFieldRepository;
    }

    public StudyField register(StudyField studyField) {
        return studyFieldRepository.register(studyField);
    }

    public StudyField update(StudyField studyField) {
        return studyFieldRepository.update(studyField);
    }

    public void delete(Long id) {
        studyFieldRepository.delete(id);
    }

    public StudyField get(Long id) {
        return studyFieldRepository.get(id);
    }

    public Page<StudyField> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return studyFieldRepository.list(pageable);
    }

    public List<StudyField> list(String search) {
        return studyFieldRepository.list(search);
    }
}

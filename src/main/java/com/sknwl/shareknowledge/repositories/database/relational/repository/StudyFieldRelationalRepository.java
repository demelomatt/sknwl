package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.StudyField;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.StudyFieldRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.ContentRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.StudyFieldModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.StudyFieldJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyFieldRelationalRepository implements StudyFieldRepository {
    private final ContentRepositoryMapper mapper = ContentRepositoryMapper.INSTANCE;
    private final StudyFieldJpaRepository studyFieldJpaRepository;

    public StudyFieldRelationalRepository(StudyFieldJpaRepository studyFieldJpaRepository) {
        this.studyFieldJpaRepository = studyFieldJpaRepository;
    }

    @Transactional
    @Override
    public StudyField create(StudyField studyField) {
        var studyFieldModel = mapper.map(studyField);
        studyFieldJpaRepository.save(studyFieldModel);
        return mapper.map(studyFieldModel);
    }

    @Transactional
    @Override
    public StudyField update(StudyField studyField) {
        var studyFieldModel = getFieldIfExists(studyField.getId());
        mapper.update(studyField, studyFieldModel);

        studyFieldJpaRepository.save(studyFieldModel);
        return mapper.map(studyFieldModel);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getFieldIfExists(id);
        studyFieldJpaRepository.deleteById(id);
    }

    @Override
    public StudyField get(Long id) {
        var studyFieldModel = getFieldIfExists(id);
        return mapper.map(studyFieldModel);
    }

    @Override
    public List<StudyField> list(String name) {
        return studyFieldJpaRepository.findTop30ByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<StudyField> list(Pageable pageable) {
        var fields = studyFieldJpaRepository.findAll(pageable)
                .stream()
                .map(mapper::map)
                .toList();
        return new PageImpl<>(fields);
    }

    private StudyFieldModel getFieldIfExists(Long id) {
        return studyFieldJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified study field"));
    }
}

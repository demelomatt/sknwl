package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.StudyField;
import com.sknwl.shareknowledge.repositories.database.relational.model.StudyFieldModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyFieldRepositoryMapper {
    StudyFieldRepositoryMapper INSTANCE = Mappers.getMapper(StudyFieldRepositoryMapper.class);

    StudyFieldModel map(StudyField studyField);
    StudyField map(StudyFieldModel studyFieldModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(StudyField studyField, @MappingTarget StudyFieldModel studyFieldModel);
}

package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.StudyFieldPayload;
import com.sknwl.shareknowledge.domain.entity.StudyField;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyFieldApiMapper {
    StudyFieldApiMapper INSTANCE = Mappers.getMapper(StudyFieldApiMapper.class);

    StudyField map(StudyFieldPayload studyFieldRequest);
    StudyFieldPayload map(StudyField studyField);
}

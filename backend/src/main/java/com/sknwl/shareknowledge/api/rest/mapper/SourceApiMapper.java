package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.SourcePayload;
import com.sknwl.shareknowledge.domain.entity.Source;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourceApiMapper {
    SourceApiMapper INSTANCE = Mappers.getMapper(SourceApiMapper.class);

    Source map(SourcePayload sourceRequest);
    SourcePayload map(Source source);
}

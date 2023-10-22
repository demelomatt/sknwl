package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

public interface SourceRepositoryMapper {
    SourceRepositoryMapper INSTANCE = Mappers.getMapper(SourceRepositoryMapper.class);

    SourceModel map(Source source);
    Source map(SourceModel sourceModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Source source, @MappingTarget SourceModel sourceModel);
}

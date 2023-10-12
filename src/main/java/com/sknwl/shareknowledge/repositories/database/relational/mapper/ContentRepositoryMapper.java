package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.LanguageModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentRepositoryMapper {
    ContentRepositoryMapper INSTANCE = Mappers.getMapper(ContentRepositoryMapper.class);

    ContentModel map(Content content);

    @Mapping(target = "publisher.socialMedias", ignore = true)
    @Mapping(target = "publisher.publishedContents", ignore = true)
    Content map(ContentModel contentModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Content content, @MappingTarget ContentModel contentModel);

    SourceModel map(Source source);

    Source map(SourceModel sourceModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Source source, @MappingTarget SourceModel sourceModel);

    LanguageModel map(Language language);

    Language map(LanguageModel languageModel);
}

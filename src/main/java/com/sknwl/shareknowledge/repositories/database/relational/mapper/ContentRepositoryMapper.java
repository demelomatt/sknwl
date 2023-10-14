package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.*;
import com.sknwl.shareknowledge.repositories.database.relational.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentRepositoryMapper {
    ContentRepositoryMapper INSTANCE = Mappers.getMapper(ContentRepositoryMapper.class);

    ContentModel map(Content content);
    @Mapping(target = "publisher", qualifiedByName = "memberModelToMember")
    Content map(ContentModel contentModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Content content, @MappingTarget ContentModel contentModel);

    ContentRatingModel map(ContentRating contentRating);
    @Mapping(target = "member", qualifiedByName = "memberModelToMember")
    ContentRating map(ContentRatingModel contentRatingModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ContentRating contentRating, @MappingTarget ContentRatingModel contentRatingModel);

    SourceModel map(Source source);
    Source map(SourceModel sourceModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Source source, @MappingTarget SourceModel sourceModel);

    LanguageModel map(Language language);
    Language map(LanguageModel languageModel);

    StudyFieldModel map(StudyField studyField);
    StudyField map(StudyFieldModel studyFieldModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(StudyField studyField, @MappingTarget StudyFieldModel studyFieldModel);

    @Named("memberModelToMember")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    Member map(MemberModel memberModel);
}

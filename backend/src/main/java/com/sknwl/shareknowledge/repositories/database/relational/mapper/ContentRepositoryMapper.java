package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.*;
import com.sknwl.shareknowledge.repositories.database.relational.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CoreRepositoryMapper.class})
public interface ContentRepositoryMapper {
    ContentRepositoryMapper INSTANCE = Mappers.getMapper(ContentRepositoryMapper.class);

    ContentModel map(Content content);
    @Mapping(target = "publisher", qualifiedByName = "memberModelToMember")
    @Mapping(target = "price.content", ignore = true)
    Content map(ContentModel contentModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(Content content, @MappingTarget ContentModel contentModel);

    ContentRatingModel map(ContentRating contentRating);
    @Mapping(target = "member", qualifiedByName = "memberModelToMember")
    ContentRating map(ContentRatingModel contentRatingModel);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ContentRating contentRating, @MappingTarget ContentRatingModel contentRatingModel);

    ContentPriceModel map(ContentPrice contentPrice);
    ContentPrice map(ContentPriceModel contentPriceModel);

    CoverImageModel map(CoverImage coverImage);
    CoverImage map(CoverImageModel coverImageModel);

    @Named("memberModelToMember")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id")
    @Mapping(target = "name")
    Member map(MemberModel memberModel);

    List<Content> map(List<ContentModel> contentModels);
}

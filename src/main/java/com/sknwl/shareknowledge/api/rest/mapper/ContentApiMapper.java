package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.*;
import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentApiMapper {
    ContentApiMapper INSTANCE = Mappers.getMapper(ContentApiMapper.class);

    Content map(ContentRequestCreate contentRequestCreate);
    Content map (ContentRequestUpdate contentRequestUpdate);
    ContentResponse map(Content content);

    @Mapping(target = "member.id", source = "memberId")
    @Mapping(target = "content.id", source = "contentId")
    ContentRating map(ContentRatingRequest contentRatingRequest);

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "contentId", source = "content.id")
    ContentRatingResponse map(ContentRating contentRating);

    Source map(SourceRequest sourceRequest);
    SourceResponse map(Source source);

    Language map(LanguageRequest languageRequest);
    LanguageResponse map(Language language);
}

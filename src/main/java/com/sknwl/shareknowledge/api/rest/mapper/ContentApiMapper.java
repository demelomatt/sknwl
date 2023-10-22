package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.*;
import com.sknwl.shareknowledge.domain.entity.*;
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
    ContentRating map(ContentRatingPayload contentRatingRequest);

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "contentId", source = "content.id")
    ContentRatingPayload map(ContentRating contentRating);

    Source map(SourcePayload sourceRequest);
    SourcePayload map(Source source);

    Language map(LanguagePayload languageRequest);
    LanguagePayload map(Language language);

    StudyField map(StudyFieldPayload studyFieldRequest);
    StudyFieldPayload map(StudyField studyField);
}

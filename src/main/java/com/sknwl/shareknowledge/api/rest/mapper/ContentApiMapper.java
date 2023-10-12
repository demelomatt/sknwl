package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.*;
import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Source;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentApiMapper {
    ContentApiMapper INSTANCE = Mappers.getMapper(ContentApiMapper.class);

    Content map(ContentRequestCreate contentRequestCreate);
    Content map (ContentRequestUpdate contentRequestUpdate);

    ContentResponse map(Content content);

    Source map(SourceRequest sourceRequest);
    SourceResponse map(Source source);

    Language map(LanguageRequest languageRequest);
    LanguageResponse map(Language language);
}

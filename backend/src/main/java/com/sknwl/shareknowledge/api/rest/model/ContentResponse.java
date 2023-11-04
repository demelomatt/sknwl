package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;

import java.time.LocalDateTime;
import java.util.List;

public record ContentResponse(Long id,
                              String title,
                              String description,
                              ContentType contentType,
                              String url,
                              SourcePayload source,
                              List<String> authors,
                              List<String> subjects,
                              StudyFieldPayload studyField,
                              LanguagePayload language,
                              Long durationMinutes,
                              MemberBasicResponse publisher,
                              LocalDateTime publishedDateTime,
                              Long reviewers,
                              Double rating,
                              MoneyPayload price,
                              CoverImageResponse coverImage
   )
{
}
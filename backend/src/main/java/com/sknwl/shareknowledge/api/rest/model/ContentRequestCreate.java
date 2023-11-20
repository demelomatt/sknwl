package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;

import java.util.List;

public record ContentRequestCreate(
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
        MemberRequest publisher,
        String coverUri,
        MoneyPayload price) {
}
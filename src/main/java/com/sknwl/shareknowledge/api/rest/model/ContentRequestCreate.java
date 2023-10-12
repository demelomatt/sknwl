package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;

import java.util.List;
import java.util.SortedSet;

public record ContentRequestCreate(
        String name,
        String description,
        ContentType contentType,
        String url,
        SourceRequest source,
        List<String> authors,
        SortedSet<String> subjects,
        LanguageRequest language,
        Long durationMinutes,
        MemberRequestUpdate publisher) {
}
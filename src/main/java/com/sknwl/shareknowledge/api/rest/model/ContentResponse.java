package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public record ContentResponse(Long id, String name, String description, ContentType contentType, String url,
                              SourceResponse source,
                              List<String> authors, SortedSet<String> subjects, LanguageResponse language, Long durationMinutes,
                              MemberResponse publisher,
                              LocalDateTime publishedDateTime, Long rating, Long reviewers, List<CommentResponse> comments,
                              MultipartFile cover) {
}
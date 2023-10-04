package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public record ContentRequest(Long id, String name, String description, ContentType contentType, String url,
                             SourceRequest source,
                             List<String> authors, SortedSet<String> subjects, LanguageRequest language, Long durationMinutes,
                             MemberRequest publisher,
                             LocalDateTime publishedDateTime, Long rating, Long reviewers, List<CommentRequest> comments,
                             MultipartFile cover) {
}
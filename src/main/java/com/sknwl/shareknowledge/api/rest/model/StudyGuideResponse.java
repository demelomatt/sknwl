package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record StudyGuideResponse(Long id, MemberResponse publisher, List<MemberResponse> contributors, String name,
                                 String description,
                                 Set<String> subjects, String language, Long durationMinutes, LocalDateTime publishedDateTime,
                                 Long rating, Long reviewers,
                                 Long subscribers, Long finished, Long favorites, List<ContentResponse> contents,
                                 List<CommentResponse> comments, boolean verified) {
}
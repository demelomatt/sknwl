package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record CommentRequest(Long id, CommentRequest parent, MemberRequest member, LocalDateTime publishedDateTime,
                             String text, ContentRequest content, StudyGuideRequest studyGuide) {
}

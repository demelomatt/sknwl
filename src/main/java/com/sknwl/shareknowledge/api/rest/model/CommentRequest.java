package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record CommentRequest(Long id, CommentRequest parent, MemberRequestCreate member, LocalDateTime publishedDateTime,
                             String text, ContentRequestCreate content, StudyGuideRequest studyGuide) {
}

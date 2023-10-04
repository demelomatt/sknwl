package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record CommentResponse(Long id, CommentResponse parent, MemberResponse member, LocalDateTime publishedDateTime,
                              String text, ContentResponse content, StudyGuideResponse studyGuide) {
}

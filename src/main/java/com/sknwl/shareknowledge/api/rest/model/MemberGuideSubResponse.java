package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.StudyGuideStatus;

import java.time.LocalDateTime;

public record MemberGuideSubResponse(StudyGuideStatus status, StudyGuideResponse studyGuide, Long rating, LocalDateTime subscribedDateTime, MemberResponse member) { }

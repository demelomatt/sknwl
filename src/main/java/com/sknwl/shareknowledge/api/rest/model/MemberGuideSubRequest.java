package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.StudyGuideStatus;

import java.time.LocalDateTime;

public record MemberGuideSubRequest(StudyGuideStatus status, StudyGuideRequest studyGuide, Long rating, LocalDateTime subscribedDateTime, MemberRequest member) { }

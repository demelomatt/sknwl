package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record CertificateRequest(String name, LocalDateTime issuanceDateTime, StudyGuideRequest studyGuide, MemberRequestCreate member) { }

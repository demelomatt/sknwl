package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record CertificateResponse(String name, LocalDateTime issuanceDateTime, StudyGuideResponse studyGuide, MemberResponse member) { }

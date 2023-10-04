package com.sknwl.shareknowledge.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Certificate {
    private Long id;
    private String name;
    private LocalDateTime issuanceDateTime;
    private StudyGuide studyGuide;
    private Member member;
}

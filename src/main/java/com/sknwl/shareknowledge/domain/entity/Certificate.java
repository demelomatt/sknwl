package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Certificate {
    private Long id;
    private String name;
    private LocalDateTime issuanceDateTime;
    private StudyGuide studyGuide;
    private Member member;
}

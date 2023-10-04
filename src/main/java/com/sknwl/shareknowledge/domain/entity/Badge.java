package com.sknwl.shareknowledge.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Badge {
    private Long id;
    private String subject;
    private LocalDateTime issuanceDateTime;
    private Member member;
}

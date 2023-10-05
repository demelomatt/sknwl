package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Badge {
    private Long id;
    private String subject;
    private LocalDateTime issuanceDateTime;
    private Member member;
}

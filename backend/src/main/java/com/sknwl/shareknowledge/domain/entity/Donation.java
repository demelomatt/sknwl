package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Donation {
    private Long id;
    private Member from;
    private Member to;
    private Money memberAmount;
    private Money systemFee;
    private Money total;
    private LocalDateTime executedDateTime;
}

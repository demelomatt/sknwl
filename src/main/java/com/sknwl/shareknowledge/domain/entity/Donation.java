package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Donation {
    private Long id;
    private Member from;
    private Member to;
    private BigDecimal amount;
    private BigDecimal systemFee;
    private BigDecimal total;
    private LocalDateTime executedDateTime;
}

package com.sknwl.shareknowledge.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Donation {
    private Long id;
    private Member from;
    private Member to;
    private BigDecimal amount;
    private BigDecimal systemFee;
    private BigDecimal total;
    private LocalDateTime executedDateTime;
}

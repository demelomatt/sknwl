package com.sknwl.shareknowledge.api.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DonationRequest(MemberRequestCreate from, MemberRequestCreate to, BigDecimal amount, BigDecimal systemFee,
                              BigDecimal total,
                              LocalDateTime executedDateTime) {
}
package com.sknwl.shareknowledge.api.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DonationResponse(MemberResponse from, MemberResponse to, BigDecimal amount, BigDecimal systemFee,
                               BigDecimal total,
                               LocalDateTime executedDateTime) {
}
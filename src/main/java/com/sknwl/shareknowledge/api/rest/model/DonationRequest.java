package com.sknwl.shareknowledge.api.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DonationRequest(MemberRequest from, MemberRequest to, BigDecimal amount, BigDecimal systemFee,
                              BigDecimal total,
                              LocalDateTime executedDateTime) {
}
package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record BadgeResponse(
        String subject,
        LocalDateTime issuanceDateTime,
        MemberResponse member
) {
}

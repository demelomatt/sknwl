package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record BadgeRequest(
        String subject,
        LocalDateTime issuanceDateTime,
        MemberRequest member
) {
}

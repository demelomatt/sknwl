package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record ContentRatingPayload(
        Long id,
        Long memberId,
        Long contentId,
        Double rating,
        LocalDateTime ratingDateTime
) {
}

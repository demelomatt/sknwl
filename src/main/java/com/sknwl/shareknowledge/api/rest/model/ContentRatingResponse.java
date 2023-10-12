package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;

public record ContentRatingResponse(
        Long id,
        Long memberId,
        Long contentId,
        Double rating,
        LocalDateTime ratingDateTime
) {
}

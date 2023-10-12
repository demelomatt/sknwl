package com.sknwl.shareknowledge.api.rest.model;

public record ContentRatingRequest(
        Long id,
        Long memberId,
        Long contentId,
        Double rating
) {
}

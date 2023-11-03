package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;

public record CoverImageResponse(Long id, String identifier, ImageAuthorResponse author, List<CoverUrlResponse> urls) {
}

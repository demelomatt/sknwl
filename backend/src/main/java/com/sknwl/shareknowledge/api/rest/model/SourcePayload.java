package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;

import java.util.List;

public record SourcePayload(Long id, String name, String webSiteUri, List<ContentType> contentTypes) {
}

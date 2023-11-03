package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CoverImage {
    private Long id;
    private String identifier;
    private ImageAuthor author;
    private List<CoverUrl> urls;
}

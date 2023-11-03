package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageAuthor {
    private Long id;
    private String identifier;
    private String name;
    private String userName;
    private String profileUrl;
}

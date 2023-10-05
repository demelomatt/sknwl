package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMedia {
    private Long id;
    private String name;
    private String url;
    private Member member;
}

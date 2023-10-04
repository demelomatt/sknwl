package com.sknwl.shareknowledge.domain.entity;

import lombok.Data;

@Data
public class SocialMedia {
    private Long id;
    private String name;
    private String url;
    private Member member;
}

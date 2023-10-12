package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {
    private Long id;
    private String name;
    private String webSiteUri;
    private String iconUrl;
}

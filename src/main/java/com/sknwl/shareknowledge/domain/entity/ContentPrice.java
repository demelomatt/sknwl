package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentPrice {
    private Long id;
    private Content content;
    private Money price;
}

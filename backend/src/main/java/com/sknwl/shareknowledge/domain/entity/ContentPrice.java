package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContentPrice {
    private Long id;
    private Content content;
    private Money price;
}

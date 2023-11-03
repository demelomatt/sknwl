package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Currency {
    private Long id;
    private String country;
    private String currency;
    private String code;
    private String symbol;
}

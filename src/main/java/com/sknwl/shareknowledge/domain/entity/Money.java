package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Money {
    private BigDecimal amount;
    private Currency currency;
}

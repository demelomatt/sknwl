package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Embeddable
public class MoneyModel {
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyModel currency;
}

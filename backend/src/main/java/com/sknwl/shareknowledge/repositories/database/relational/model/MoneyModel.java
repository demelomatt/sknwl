package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MoneyModel {
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyModel currency;
}

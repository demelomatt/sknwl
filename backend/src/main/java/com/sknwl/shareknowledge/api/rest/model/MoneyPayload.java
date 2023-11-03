package com.sknwl.shareknowledge.api.rest.model;

import java.math.BigDecimal;

public record MoneyPayload(BigDecimal amount, CurrencyPayload currency) {
}

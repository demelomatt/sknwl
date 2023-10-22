package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Currency;

import java.util.List;

public interface CurrencyRepository {
    List<Currency> list(String code, String name);
}

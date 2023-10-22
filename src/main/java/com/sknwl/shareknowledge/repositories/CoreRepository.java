package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.domain.entity.Language;

import java.util.List;

public interface CoreRepository {
    List<Currency> listCurrencies(String code, String name);

    List<Language> listLanguage(String code, String name);
}

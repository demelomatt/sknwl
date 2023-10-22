package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyUseCase {
    private final CurrencyRepository currencyRepository;

    public CurrencyUseCase(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> list(String code, String name) {
        return currencyRepository.list(code, name);
    }
}

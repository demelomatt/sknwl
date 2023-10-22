package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.repositories.CoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreUseCase {
    private final CoreRepository coreRepository;

    public CoreUseCase(CoreRepository coreRepository) {
        this.coreRepository = coreRepository;
    }

    public List<Currency> listCurrencies(String code, String name) {
        return coreRepository.listCurrencies(code, name);
    }

    public List<Language> listLanguage(String code, String name) {
        return coreRepository.listLanguage(code, name);
    }
}

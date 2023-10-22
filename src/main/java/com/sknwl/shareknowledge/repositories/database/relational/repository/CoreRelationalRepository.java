package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.repositories.CoreRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.CoreRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.CurrencyJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.LanguageJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoreRelationalRepository implements CoreRepository {
    private final CoreRepositoryMapper mapper = CoreRepositoryMapper.INSTANCE;

    private final CurrencyJpaRepository currencyJpaRepository;
    private final LanguageJpaRepository languageJpaRepository;


    public CoreRelationalRepository(CurrencyJpaRepository currencyJpaRepository, LanguageJpaRepository languageJpaRepository) {
        this.currencyJpaRepository = currencyJpaRepository;
        this.languageJpaRepository = languageJpaRepository;
    }

    @Override
    public List<Currency> listCurrencies(String code, String name) {
        return currencyJpaRepository.findByCodeAndName(code, name)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Language> listLanguage(String code, String name) {
        return languageJpaRepository.findByCodeAndName(code, name)
                .stream()
                .map(mapper::map)
                .toList();
    }
}

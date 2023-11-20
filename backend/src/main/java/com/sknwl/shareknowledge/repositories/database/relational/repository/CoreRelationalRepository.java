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
    public List<Currency> listCurrencies(String code, String currencyName) {
        return currencyJpaRepository.findByCodeAndCurrency(code, currencyName)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Language> listLanguage() {
        return languageJpaRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<Language> listLanguageByValue(String search) {
        return languageJpaRepository.findByValue(search)
                .stream()
                .map(mapper::map)
                .toList();
    }

    public List<Language> listLanguageByCode(String code) {
        return languageJpaRepository.findByCode(code)
                .stream()
                .map(mapper::map)
                .toList();
    }

    public List<Language> listLanguageByName(String name) {
        return languageJpaRepository.findByName(name)
                .stream()
                .map(mapper::map)
                .toList();
    }
}

package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.repositories.CurrencyRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.CurrencyRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.CurrencyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRelationalRepository implements CurrencyRepository {
    private final CurrencyJpaRepository currencyJpaRepository;
    private final CurrencyRepositoryMapper mapper = CurrencyRepositoryMapper.INSTANCE;

    public CurrencyRelationalRepository(CurrencyJpaRepository currencyJpaRepository) {
        this.currencyJpaRepository = currencyJpaRepository;
    }

    @Override
    public List<Currency> list(String code, String name) {
        return currencyJpaRepository.findByCodeAndName(code, name)
                .stream()
                .map(mapper::map)
                .toList();
    }
}

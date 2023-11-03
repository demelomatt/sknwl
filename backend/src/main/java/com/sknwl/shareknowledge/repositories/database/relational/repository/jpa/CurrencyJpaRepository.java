package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyJpaRepository extends JpaRepository<CurrencyModel, Long> {
    List<CurrencyModel> findByCodeAndCurrency(String code, String currency);
}

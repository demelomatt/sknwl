package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.repositories.database.relational.model.CurrencyModel;
import org.mapstruct.factory.Mappers;

public interface CurrencyRepositoryMapper {
    CurrencyRepositoryMapper INSTANCE = Mappers.getMapper(CurrencyRepositoryMapper.class);

    CurrencyModel map(Currency currency);
    Currency map(CurrencyModel currencyModel);

}

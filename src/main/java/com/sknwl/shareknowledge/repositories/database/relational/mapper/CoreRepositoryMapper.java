package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.Money;
import com.sknwl.shareknowledge.repositories.database.relational.model.CurrencyModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.LanguageModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.MoneyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoreRepositoryMapper {
    CoreRepositoryMapper INSTANCE = Mappers.getMapper(CoreRepositoryMapper.class);

    CurrencyModel map(Currency currency);
    Currency map(CurrencyModel currencyModel);

    MoneyModel map(Money money);
    Money map(MoneyModel moneyModel);

    LanguageModel map(Language language);
    Language map(LanguageModel languageModel);
}

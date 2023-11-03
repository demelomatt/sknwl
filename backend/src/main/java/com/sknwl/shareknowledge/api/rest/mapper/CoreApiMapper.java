package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.CurrencyPayload;
import com.sknwl.shareknowledge.api.rest.model.LanguagePayload;
import com.sknwl.shareknowledge.domain.entity.Currency;
import com.sknwl.shareknowledge.domain.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoreApiMapper {
    CoreApiMapper INSTANCE = Mappers.getMapper(CoreApiMapper.class);

    Currency map(CurrencyPayload currencyPayload);
    CurrencyPayload map(Currency currency);

    Language map(LanguagePayload languageRequest);
    LanguagePayload map(Language language);
}

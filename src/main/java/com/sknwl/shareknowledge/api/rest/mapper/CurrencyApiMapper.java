package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.CurrencyPayload;
import com.sknwl.shareknowledge.domain.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyApiMapper {
    CurrencyApiMapper INSTANCE = Mappers.getMapper(CurrencyApiMapper.class);

    Currency map(CurrencyPayload currencyPayload);
    CurrencyPayload map(Currency currency);
}

package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.CurrencyApiMapper;
import com.sknwl.shareknowledge.api.rest.model.CurrencyPayload;
import com.sknwl.shareknowledge.domain.usecase.CurrencyUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("currencies")
public class CurrencyController {
    private final CurrencyUseCase currencyUseCase;
    private final CurrencyApiMapper mapper = CurrencyApiMapper.INSTANCE;

    public CurrencyController(CurrencyUseCase currencyUseCase) {
        this.currencyUseCase = currencyUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyPayload>> list(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        var languages = currencyUseCase.list(code, name)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }
}

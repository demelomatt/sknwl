package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.CoreApiMapper;
import com.sknwl.shareknowledge.api.rest.model.CurrencyPayload;
import com.sknwl.shareknowledge.api.rest.model.LanguagePayload;
import com.sknwl.shareknowledge.domain.usecase.CoreUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("core")
public class CoreController {
    private final CoreUseCase coreUseCase;
    private final CoreApiMapper mapper = CoreApiMapper.INSTANCE;

    public CoreController(CoreUseCase coreUseCase) {
        this.coreUseCase = coreUseCase;
    }

    @GetMapping("/languages")
    public ResponseEntity<List<LanguagePayload>> listLanguage(
            @RequestParam(required = false) String search
    ) {
        var languages = coreUseCase.listLanguage(search)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyPayload>> listCurrencies(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        var languages = coreUseCase.listCurrencies(code, name)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }
}

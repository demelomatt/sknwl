package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.SourceApiMapper;
import com.sknwl.shareknowledge.api.rest.model.SourcePayload;
import com.sknwl.shareknowledge.domain.usecase.SourceUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("sources")
public class SourceController {
    private final SourceApiMapper mapper = SourceApiMapper.INSTANCE;
    private final SourceUseCase sourceUseCase;

    public SourceController(SourceUseCase sourceUseCase) {
        this.sourceUseCase = sourceUseCase;
    }

    @PostMapping
    public ResponseEntity<SourcePayload> register(
            @RequestBody SourcePayload sourceRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var source = sourceUseCase.register(mapper.map(sourceRequest));

        var uri = uriBuilder.path("/contents/sources").buildAndExpand(source.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(source));
    }

    @PutMapping
    public ResponseEntity<SourcePayload> update(@RequestBody SourcePayload sourceRequest) {
        var source = sourceUseCase.update(mapper.map(sourceRequest));
        return ResponseEntity.ok(mapper.map(source));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sourceUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourcePayload> get(@PathVariable Long id) {
        var source = sourceUseCase.get(id);
        return ResponseEntity.ok(mapper.map(source));
    }

    @GetMapping()
    public ResponseEntity<List<SourcePayload>> list(
            @RequestParam(required = false) String search
    ) {
        var sources = sourceUseCase.list(search)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(sources);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SourcePayload>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var sources = sourceUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(sources));
    }
}

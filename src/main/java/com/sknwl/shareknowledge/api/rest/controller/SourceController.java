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
    private final SourceUseCase coreUseCase;

    public SourceController(SourceUseCase coreUseCase) {
        this.coreUseCase = coreUseCase;
    }

    @PostMapping
    public ResponseEntity<SourcePayload> createSource(
            @RequestBody SourcePayload sourceRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var source = coreUseCase.register(mapper.map(sourceRequest));

        var uri = uriBuilder.path("/contents/sources").buildAndExpand(source.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(source));
    }

    @PutMapping
    public ResponseEntity<SourcePayload> updateSource(@RequestBody SourcePayload sourceRequest) {
        var source = coreUseCase.update(mapper.map(sourceRequest));
        return ResponseEntity.ok(mapper.map(source));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        coreUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SourcePayload> getSource(@PathVariable Long id) {
        var source = coreUseCase.get(id);
        return ResponseEntity.ok(mapper.map(source));
    }

    @GetMapping
    public ResponseEntity<Page<SourcePayload>> listSource(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var sources = coreUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(sources));
    }

    @GetMapping("/uri/{uri}")
    public ResponseEntity<List<SourcePayload>> listSource(
            @PathVariable String uri
    ) {
        var sources = coreUseCase.list(uri)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(sources);
    }
}

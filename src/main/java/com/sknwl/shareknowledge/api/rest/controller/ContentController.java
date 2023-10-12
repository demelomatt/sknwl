package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.ContentApiMapper;
import com.sknwl.shareknowledge.api.rest.model.*;
import com.sknwl.shareknowledge.domain.usecase.ContentUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("contents")
public class ContentController {
    private final ContentUseCase contentUseCase;
    private final ContentApiMapper mapper = ContentApiMapper.INSTANCE;

    public ContentController(ContentUseCase contentUseCase) {
        this.contentUseCase = contentUseCase;
    }

    @PostMapping
    public ResponseEntity<ContentResponse> create(
            @RequestBody ContentRequestCreate contentRequestCreate,
            UriComponentsBuilder uriBuilder
    ) {
        var content = contentUseCase.create(mapper.map(contentRequestCreate));

        var uri = uriBuilder.path("/contents").buildAndExpand(content.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(content));
    }

    @PutMapping
    public ResponseEntity<ContentResponse> update(@RequestBody ContentRequestUpdate contentRequestUpdate) {
        var content = contentUseCase.update(mapper.map(contentRequestUpdate));
        return ResponseEntity.ok(mapper.map(content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contentUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> get(@PathVariable Long id) {
        var content = contentUseCase.get(id);
        return ResponseEntity.ok(mapper.map(content));
    }

    @GetMapping
    public ResponseEntity<Page<ContentResponse>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var contents = contentUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(contents));
    }

    @PostMapping("/ratings")
    public ResponseEntity<ContentRatingResponse> createRating(
            @RequestBody ContentRatingRequest contentRatingRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var rating = contentUseCase.newRating(mapper.map(contentRatingRequest));

        var uri = uriBuilder.path("/contents/ratings").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(rating));
    }

    @PutMapping("/ratings")
    public ResponseEntity<ContentRatingResponse> updateRating(@RequestBody ContentRatingRequest contentRatingRequest) {
        var rating = contentUseCase.updateRating(mapper.map(contentRatingRequest));
        return ResponseEntity.ok(mapper.map(rating));
    }

    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        contentUseCase.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<ContentRatingResponse> getRating(@PathVariable Long id) {
        var rating = contentUseCase.getRating(id);
        return ResponseEntity.ok(mapper.map(rating));
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<ContentRatingResponse>> list(
    ) {
        var ratings = contentUseCase.listRating()
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(ratings);
    }

    @PostMapping("/sources")
    public ResponseEntity<SourceResponse> createSource(
            @RequestBody SourceRequest sourceRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var source = contentUseCase.newSource(mapper.map(sourceRequest));

        var uri = uriBuilder.path("/contents/sources").buildAndExpand(source.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(source));
    }

    @PutMapping("/sources")
    public ResponseEntity<SourceResponse> updateSource(@RequestBody SourceRequest sourceRequest) {
        var source = contentUseCase.updateSource(mapper.map(sourceRequest));
        return ResponseEntity.ok(mapper.map(source));
    }

    @DeleteMapping("/sources/{id}")
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        contentUseCase.deleteSource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sources/{id}")
    public ResponseEntity<SourceResponse> getSource(@PathVariable Long id) {
        var source = contentUseCase.getSource(id);
        return ResponseEntity.ok(mapper.map(source));
    }

    @GetMapping("/sources")
    public ResponseEntity<Page<SourceResponse>> listSource(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var sources = contentUseCase.listSource(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(sources));
    }

    @GetMapping("/sources/uri/{uri}")
    public ResponseEntity<List<SourceResponse>> listSource(
            @PathVariable String uri
    ) {
        var sources = contentUseCase.listSource(uri)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(sources);
    }

    @GetMapping("/languages/name/{name}")
    public ResponseEntity<List<LanguageResponse>> listLanguageByName(
            @PathVariable String name
    ) {
        var languages = contentUseCase.listLanguageByName(name)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/languages/code/{code}")
    public ResponseEntity<List<LanguageResponse>> listLanguageByCode(
            @PathVariable String code
    ) {
        var languages = contentUseCase.listLanguageByCode(code)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageResponse>> listLanguage(
    ) {
        var languages = contentUseCase.listLanguage()
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(languages);
    }
}

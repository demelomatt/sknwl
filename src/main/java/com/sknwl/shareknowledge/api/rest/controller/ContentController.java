package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.ContentApiMapper;
import com.sknwl.shareknowledge.api.rest.model.*;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
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
        var content = contentUseCase.register(mapper.map(contentRequestCreate));

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

    /*
    @GetMapping
    public ResponseEntity<Page<ContentResponse>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        var contents = contentUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(contents));
    }

     */

    @GetMapping
    public ResponseEntity<Page<ContentResponse>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "RATING_AVG_COUNT_DESC") SortType sort,
            @RequestParam(required = false) String keyphrase,
            @RequestParam(defaultValue = "0") Integer minRatings,
            @RequestParam(required = false) List<ContentType> contentTypes,
            @RequestParam(required = false) Long sourceId,
            @RequestParam(required = false) Long languageId,
            @RequestParam(required = false) Integer minDuration,
            @RequestParam(required = false) Integer maxDuration
            ) {
        var contents = contentUseCase.list(pageNumber, pageSize, sort, keyphrase, minRatings, contentTypes, sourceId, languageId, minDuration, maxDuration)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(contents));
    }


    @PostMapping("/ratings")
    public ResponseEntity<ContentRatingPayload> createRating(
            @RequestBody ContentRatingPayload contentRatingRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var rating = contentUseCase.registerRating(mapper.map(contentRatingRequest));

        var uri = uriBuilder.path("/contents/ratings").buildAndExpand(rating.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(rating));
    }

    @PutMapping("/ratings")
    public ResponseEntity<ContentRatingPayload> updateRating(@RequestBody ContentRatingPayload contentRatingRequest) {
        var rating = contentUseCase.updateRating(mapper.map(contentRatingRequest));
        return ResponseEntity.ok(mapper.map(rating));
    }

    @DeleteMapping("/ratings/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        contentUseCase.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<ContentRatingPayload> getRating(@PathVariable Long id) {
        var rating = contentUseCase.getRating(id);
        return ResponseEntity.ok(mapper.map(rating));
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<ContentRatingPayload>> list(
    ) {
        var ratings = contentUseCase.listRating()
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(ratings);
    }

}

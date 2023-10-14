package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.ContentApiMapper;
import com.sknwl.shareknowledge.api.rest.model.StudyFieldRequest;
import com.sknwl.shareknowledge.api.rest.model.StudyFieldResponse;
import com.sknwl.shareknowledge.domain.usecase.StudyFieldUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("fields")
public class StudyFieldController {
    private final StudyFieldUseCase studyFieldUseCase;
    private final ContentApiMapper mapper = ContentApiMapper.INSTANCE;

    public StudyFieldController(StudyFieldUseCase studyFieldUseCase) {
        this.studyFieldUseCase = studyFieldUseCase;
    }

    @PostMapping()
    public ResponseEntity<StudyFieldResponse> create(
            @RequestBody StudyFieldRequest studyFieldRequest,
            UriComponentsBuilder uriBuilder
    ) {
        var studyField = studyFieldUseCase.create(mapper.map(studyFieldRequest));

        var uri = uriBuilder.path("/fields").buildAndExpand(studyField.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(studyField));
    }

    @PutMapping()
    public ResponseEntity<StudyFieldResponse> update(@RequestBody StudyFieldRequest studyFieldRequest) {
        var studyField = studyFieldUseCase.update(mapper.map(studyFieldRequest));
        return ResponseEntity.ok(mapper.map(studyField));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studyFieldUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyFieldResponse> get(@PathVariable Long id) {
        var studyField = studyFieldUseCase.get(id);
        return ResponseEntity.ok(mapper.map(studyField));
    }

    @GetMapping()
    public ResponseEntity<Page<StudyFieldResponse>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var fields = studyFieldUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(fields));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<StudyFieldResponse>> list(
            @PathVariable String name
    ) {
        var fields = studyFieldUseCase.list(name)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(fields);
    }
}

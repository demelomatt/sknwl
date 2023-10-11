package com.sknwl.shareknowledge.api.rest.controller;

import com.sknwl.shareknowledge.api.rest.mapper.MemberApiMapper;
import com.sknwl.shareknowledge.api.rest.model.MemberRequestCreate;
import com.sknwl.shareknowledge.api.rest.model.MemberRequestUpdate;
import com.sknwl.shareknowledge.api.rest.model.MemberResponse;
import com.sknwl.shareknowledge.domain.usecase.MemberUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberUseCase memberUseCase;
    private final MemberApiMapper mapper = MemberApiMapper.INSTANCE;

    public MemberController(MemberUseCase memberUseCase) {
        this.memberUseCase = memberUseCase;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> create(
            @RequestBody MemberRequestCreate memberRequestCreate,
            UriComponentsBuilder uriBuilder
    ) {
        var member = memberUseCase.create(mapper.map(memberRequestCreate));

        var uri = uriBuilder.path("/members").buildAndExpand(member.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(member));
    }

    @PutMapping
    public ResponseEntity<MemberResponse> update(@RequestBody MemberRequestUpdate memberRequestUpdate) {
        var member = memberUseCase.update(mapper.map(memberRequestUpdate));
        return ResponseEntity.ok(mapper.map(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam(defaultValue = "false") Boolean hardDelete) {
        memberUseCase.delete(id, hardDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> get(@PathVariable Long id) {
        var member = memberUseCase.get(id);
        return ResponseEntity.ok(mapper.map(member));
    }

    @GetMapping
    public ResponseEntity<Page<MemberResponse>> list(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        var members = memberUseCase.list(pageNumber, pageSize)
                .stream()
                .map(mapper::map)
                .toList();
        return ResponseEntity.ok(new PageImpl<>(members));
    }
}

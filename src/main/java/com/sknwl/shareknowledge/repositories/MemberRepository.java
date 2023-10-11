package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MemberRepository {

    Member create(Member member);
    Member update(Member member);
    void softDelete(Long id);
    void hardDelete(Long id);
    Member get(Long id);
    Page<Member> list(PageRequest pageable);
}

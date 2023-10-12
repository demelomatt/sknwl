package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Member;
import com.sknwl.shareknowledge.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberUseCase {
    private final MemberRepository memberRepository;

    public MemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(Member member) {
        member.setActive(true);
        member.setJoinedDateTime(LocalDateTime.now());
        return memberRepository.create(member);
    }

    public Member update(Member member) {
        return memberRepository.update(member);
    }

    public Page<Member> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return memberRepository.list(pageable);
    }

    public void delete(Long id, Boolean hardDelete) {
        if (hardDelete) {
            memberRepository.hardDelete(id);
        } else{
            memberRepository.softDelete(id);
        }
    }

    public Member get(Long id) {
        return memberRepository.get(id);
    }
}

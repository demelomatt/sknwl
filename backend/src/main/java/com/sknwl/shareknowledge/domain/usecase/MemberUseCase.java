package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Member;
import com.sknwl.shareknowledge.domain.exception.AuthenticationException;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class MemberUseCase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberUseCase(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(Member member) {
        member.setActive(true);
        member.setJoinedDateTime(LocalDateTime.now());
        member.setPwd(passwordEncoder.encode(member.getPwd()));
        return memberRepository.register(member);
    }

    public Member update(Member member) {
        return memberRepository.update(member);
    }

    public Page<Member> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return memberRepository.list(pageable);
    }

    public void delete(Long id, Boolean permanent) {
        if (permanent) {
            memberRepository.hardDelete(id);
        } else{
            memberRepository.softDelete(id);
        }
    }

    public Member get(Long id) {
        return memberRepository.get(id);
    }

    public Member authenticate(Member memberDto) {
        try {
            var member =  memberRepository.get(memberDto.getEmail());
            if (passwordEncoder.matches(memberDto.getPwd(), member.getPwd())) {
               return member;
            }
            throw new AuthenticationException("Email ou senha inválidos");
        } catch (NotFoundException e) {
            throw new AuthenticationException("Email ou senha inválidos");
        }
    }
}

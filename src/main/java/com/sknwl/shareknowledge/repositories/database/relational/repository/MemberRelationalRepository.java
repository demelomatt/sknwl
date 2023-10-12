package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Member;
import com.sknwl.shareknowledge.domain.exception.NotFoundException;
import com.sknwl.shareknowledge.repositories.MemberRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.MemberRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.MemberModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SocialMediaModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRelationalRepository implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepositoryMapper mapper = MemberRepositoryMapper.INSTANCE;

    public MemberRelationalRepository(MemberJpaRepository memberJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
    }

    @Transactional
    @Override
    public Member create(Member member) {
        MemberModel memberModel = mapper.map(member);
        for (SocialMediaModel socialMedia : memberModel.getSocialMedias()) {
            socialMedia.setMember(memberModel);
        }
        memberJpaRepository.save(memberModel);
        return mapper.map(memberModel);
    }

    @Transactional
    @Override
    public Member update(Member member) {
        var memberModel = getMember(member.getId());
        mapper.update(member, memberModel);

        for (SocialMediaModel socialMedia : memberModel.getSocialMedias()) {
            socialMedia.setMember(memberModel);
        }
        memberJpaRepository.save(memberModel);
        return mapper.map(memberModel);
    }

    @Transactional
    @Override
    public void softDelete(Long id) {
        var memberModel = getMember(id);
        if (memberModel.getActive()) {
            memberModel.setActive(false);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        getMember(id);
        memberJpaRepository.deleteById(id);
    }

    @Override
    public Member get(Long id) {
        var memberModel = getMember(id);
        return mapper.map(memberModel);
    }

    @Override
    public Page<Member> list(PageRequest pageable) {
        var members = memberJpaRepository.findAllByActiveTrue(pageable)
                .stream()
                .map(mapper::map)
                .toList();
        return new PageImpl<>(members);
    }

    private MemberModel getMember(Long id) {
        return memberJpaRepository.findById(id).orElseThrow(()-> new NotFoundException("Unable to find the specified member"));
    }
}

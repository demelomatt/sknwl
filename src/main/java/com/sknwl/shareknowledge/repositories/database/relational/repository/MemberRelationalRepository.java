package com.sknwl.shareknowledge.repositories.database.relational.repository;

import com.sknwl.shareknowledge.domain.entity.Member;
import com.sknwl.shareknowledge.repositories.MemberRepository;
import com.sknwl.shareknowledge.repositories.database.relational.mapper.MemberRepositoryMapper;
import com.sknwl.shareknowledge.repositories.database.relational.model.MemberModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SocialMediaModel;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.MemberJpaRepository;
import com.sknwl.shareknowledge.repositories.database.relational.repository.jpa.SocialMediaJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRelationalRepository implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;
    private final SocialMediaJpaRepository socialMediaJpaRepository;
    private final MemberRepositoryMapper mapper = MemberRepositoryMapper.INSTANCE;

    public MemberRelationalRepository(MemberJpaRepository memberJpaRepository, SocialMediaJpaRepository socialMediaJpaRepository) {
        this.memberJpaRepository = memberJpaRepository;
        this.socialMediaJpaRepository = socialMediaJpaRepository;
    }

    @Transactional
    @Override
    public Member create(Member member) {
        MemberModel memberModel = mapper.map(member);
        var createdMemberModel = memberJpaRepository.save(memberModel);
        if (createdMemberModel.getSocialMedias() != null) {
            for (SocialMediaModel socialMedia : createdMemberModel.getSocialMedias()) {
                socialMedia.setMember(createdMemberModel);
            }
            socialMediaJpaRepository.saveAll(createdMemberModel.getSocialMedias());
        }
        return mapper.map(createdMemberModel);
    }

    @Override
    public Member update(Member member) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var memberModel = memberJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (memberModel.getActive()) {
            memberModel.setActive(false);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Member get(Long id) {
        var optional = memberJpaRepository.findById(id);
        var memberModel = optional.orElseThrow(EntityNotFoundException::new);

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
}

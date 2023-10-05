package com.sknwl.shareknowledge.repositories.database.relational.mapper;

import com.sknwl.shareknowledge.domain.entity.Member;
import com.sknwl.shareknowledge.domain.entity.SocialMedia;
import com.sknwl.shareknowledge.repositories.database.relational.model.MemberModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.SocialMediaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberRepositoryMapper {
    MemberRepositoryMapper INSTANCE = Mappers.getMapper(MemberRepositoryMapper.class);

    MemberModel map(Member member);

    @Mapping(target = "socialMedias", qualifiedByName = "socialMediaModelToSocialMedia")
    Member map(MemberModel memberModel);

    @Named("socialMediaModelToSocialMedia")
    @Mapping(target = "member", ignore = true)
    SocialMedia map(SocialMediaModel socialMediaModel);
}

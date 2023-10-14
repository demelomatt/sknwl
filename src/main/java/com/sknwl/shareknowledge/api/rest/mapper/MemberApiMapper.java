package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.MemberRequestCreate;
import com.sknwl.shareknowledge.api.rest.model.MemberRequestUpdate;
import com.sknwl.shareknowledge.api.rest.model.MemberResponse;
import com.sknwl.shareknowledge.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContentApiMapper.class})

public interface MemberApiMapper {
    MemberApiMapper INSTANCE = Mappers.getMapper(MemberApiMapper.class);

    Member map(MemberRequestCreate memberRequestCreate);
    Member map (MemberRequestUpdate memberRequestUpdate);

    MemberResponse map(Member member);
}

package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.MemberAuthRequest;
import com.sknwl.shareknowledge.api.rest.model.MemberRequest;
import com.sknwl.shareknowledge.api.rest.model.MemberResponse;
import com.sknwl.shareknowledge.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContentApiMapper.class})

public interface MemberApiMapper {
    MemberApiMapper INSTANCE = Mappers.getMapper(MemberApiMapper.class);

    Member map (MemberRequest memberRequest);
    Member map (MemberAuthRequest memberAuthRequest);

    MemberResponse map(Member member);
}

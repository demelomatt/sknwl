package com.sknwl.shareknowledge.api.rest.mapper;

import com.sknwl.shareknowledge.api.rest.model.ContentRatingPayload;
import com.sknwl.shareknowledge.api.rest.model.ContentRequestCreate;
import com.sknwl.shareknowledge.api.rest.model.ContentRequestUpdate;
import com.sknwl.shareknowledge.api.rest.model.ContentResponse;
import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface ContentApiMapper {
    ContentApiMapper INSTANCE = Mappers.getMapper(ContentApiMapper.class);

    @Mapping(target = "publisher.id", source = "publisherId")
    Content map(ContentRequestCreate contentRequestCreate);
    Content map (ContentRequestUpdate contentRequestUpdate);
    ContentResponse map(Content content);
    List<ContentResponse> map(List<Content> contents);

    @Mapping(target = "member.id", source = "memberId")
    @Mapping(target = "content.id", source = "contentId")
    ContentRating map(ContentRatingPayload contentRatingRequest);

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "contentId", source = "content.id")
    ContentRatingPayload map(ContentRating contentRating);
}

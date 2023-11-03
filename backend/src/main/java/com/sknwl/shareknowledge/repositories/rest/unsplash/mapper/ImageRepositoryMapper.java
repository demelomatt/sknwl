package com.sknwl.shareknowledge.repositories.rest.unsplash.mapper;

import com.sknwl.shareknowledge.domain.entity.CoverImage;
import com.sknwl.shareknowledge.domain.entity.CoverUrl;
import com.sknwl.shareknowledge.domain.entity.ImageAuthor;
import com.sknwl.shareknowledge.repositories.rest.unsplash.model.ImageModel;
import com.sknwl.shareknowledge.repositories.rest.unsplash.model.UrlModel;
import com.sknwl.shareknowledge.repositories.rest.unsplash.model.UserLink;
import com.sknwl.shareknowledge.repositories.rest.unsplash.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageRepositoryMapper {
    ImageRepositoryMapper INSTANCE = Mappers.getMapper(ImageRepositoryMapper.class);

    @Mapping(target = "author",source = "user")
    @Mapping(target = "identifier", source = "id")
    CoverImage map(ImageModel imageModel);

    default Long stringToLong(String id) {
        return null;
    }

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "identifier",source = "id")
    ImageAuthor map(UserModel userModel);

    default String map(UserLink userLink) {
        return userLink.html();
    }

    default List<CoverUrl> map(UrlModel urlModel) {
        var regular = new CoverUrl(null, "regular", urlModel.regular());
        var small = new CoverUrl(null, "small", urlModel.small());

        return List.of(regular, small);
    }
}

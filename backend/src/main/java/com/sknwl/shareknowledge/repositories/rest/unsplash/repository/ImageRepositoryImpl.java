package com.sknwl.shareknowledge.repositories.rest.unsplash.repository;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.CoverImage;
import com.sknwl.shareknowledge.repositories.CoverImageRepository;
import com.sknwl.shareknowledge.repositories.rest.unsplash.mapper.ImageRepositoryMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImpl implements CoverImageRepository {
    private final ImageClient imageClient;
    private final ImageRepositoryMapper mapper = ImageRepositoryMapper.INSTANCE;

    @Value("${sknwl.api.unsplash.client}")
    private String clientId;

    public ImageRepositoryImpl(ImageClient imageClient) {
        this.imageClient = imageClient;
    }

    @Override
    public CoverImage getRandomPhoto(Content content) {
        var response = imageClient.getRandomPhoto(clientId, content.getStudyField().getName(), "landscape", "high");
        return mapper.map(response);
    }
}

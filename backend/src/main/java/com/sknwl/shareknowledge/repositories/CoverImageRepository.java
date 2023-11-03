package com.sknwl.shareknowledge.repositories;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.CoverImage;

public interface CoverImageRepository {
    CoverImage getRandomPhoto(Content content);
}

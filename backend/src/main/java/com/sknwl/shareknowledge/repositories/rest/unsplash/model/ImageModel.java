package com.sknwl.shareknowledge.repositories.rest.unsplash.model;

public record ImageModel(String id,
                         String slug,
                         String created_at,
                         String updated_at,
                         String promoted_at,
                         String alt_description,
                         UserModel user,
                         UrlModel urls
                         ) {
}

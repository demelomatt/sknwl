package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ContentRating {
    private Long id;
    private Member member;
    private Content content;
    private Double rating;
    private LocalDateTime ratingDateTime;
}

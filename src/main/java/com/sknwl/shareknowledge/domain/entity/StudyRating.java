package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRating {
    private Long id;
    private Member member;
    private StudyGuide studyGuide;
    private Double rating;
    private LocalDateTime ratingDateTime;
}

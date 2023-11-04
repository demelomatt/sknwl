package com.sknwl.shareknowledge.domain.entity;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Content {
    private Long id;
    private String title;
    private String description;
    private ContentType contentType;
    private String url;
    private Source source;
    private List<String> authors;
    private List<String> subjects;
    private StudyField studyField;
    private Language language;
    private Long durationMinutes;
    private Member publisher;
    private LocalDateTime publishedDateTime;
    private Long reviewers;
    private Double rating;
    private ContentPrice price;
    private List<Comment> comments;
    private CoverImage coverImage;
}
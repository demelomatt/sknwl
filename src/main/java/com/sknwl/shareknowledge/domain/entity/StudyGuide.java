package com.sknwl.shareknowledge.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class StudyGuide {
    private Long id;
    private Member publisher;
    private List<Member> contributors;
    private String name;
    private String description;
    private Set<String> subjects;
    private Language language;
    private Long durationMinutes;
    private LocalDateTime publishedDateTime;
    private Long rating;
    private Long reviewers;
    private Long subscribers;
    private Long finished;
    private Long favorites;
    private List<Content> contents;
    private List<Comment> comments;
    private boolean verified;
}

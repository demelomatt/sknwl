package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private Long id;
    private Comment parent;
    private List<Comment> children;
    private Member member;
    private LocalDateTime publishedDateTime;
    private String text;
    private Content content;
    private StudyGuide studyGuide;
}

package com.sknwl.shareknowledge.domain.entity;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import lombok.Data;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

@Data
public class Content {
    private Long id;
    private String name;
    private String description;
    private ContentType contentType;
    private String url;
    private Source source;
    private List<String> authors;
    private SortedSet<String> subjects;
    private Language language;
    private Long durationMinutes;
    private Member publisher;
    private LocalDateTime publishedDateTime;
    private Long rating;
    private Long reviewers;
    private List<Comment> comments;
    private InputStream cover;
}
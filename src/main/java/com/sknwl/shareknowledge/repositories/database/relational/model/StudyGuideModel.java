package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="study_guide")
public class StudyGuideModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel publisher;

    @ManyToMany
    @JoinTable(
            name = "study_guide_contributor",
            joinColumns = @JoinColumn(name = "study_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<MemberModel> contributors;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "study_guide_subject", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "name")
    private Set<String> subjects;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private LanguageModel language;

    private Long durationMinutes;
    private LocalDateTime publishedDateTime;
    private Long rating;
    private Long reviewers;
    private Long subscribers;
    private Long finished;
    private Long favorites;

    @ManyToMany
    @JoinTable(
            name = "study_guide_content",
            joinColumns = @JoinColumn(name = "study_guide_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<ContentModel> contents;

    @OneToMany(mappedBy = "studyGuide")
    private List<CommentModel> comments;
    private boolean verified;
}
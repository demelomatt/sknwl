package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommentModel parent;

    @OneToMany(mappedBy = "parent")
    private List<CommentModel> children;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel member;

    private LocalDateTime publishedDateTime;
    private String text;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private ContentModel content;

    @ManyToOne
    @JoinColumn(name = "study_guide_id", referencedColumnName = "id")
    private StudyGuideModel studyGuide;
}
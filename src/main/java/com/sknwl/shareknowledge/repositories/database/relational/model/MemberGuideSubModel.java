package com.sknwl.shareknowledge.repositories.database.relational.model;

import com.sknwl.shareknowledge.domain.entity.enums.StudyGuideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="member_study_guide")
public class MemberGuideSubModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private StudyGuideStatus status;

    @ManyToOne
    @JoinColumn(name = "study_guide_id", referencedColumnName = "id")
    private StudyGuideModel studyGuide;

    private Long rating;
    private LocalDateTime subscribedDateTime;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel member;
}
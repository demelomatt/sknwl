package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="certificate")
public class CertificateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime issuanceDateTime;

    @ManyToOne
    @JoinColumn(name = "study_guide_id", referencedColumnName = "id")
    private StudyGuideModel studyGuide;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel member;
}
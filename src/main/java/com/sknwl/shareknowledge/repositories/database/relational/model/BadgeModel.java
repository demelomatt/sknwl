package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="badge")
public class BadgeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private LocalDateTime issuanceDateTime;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel member;
}

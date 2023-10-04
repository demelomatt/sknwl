package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

@Data
@Entity
@Table(name="member")
public class MemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String biography;

    @OneToMany(mappedBy = "member")
    private List<SocialMediaModel> socialMedias;

    @ManyToMany
    @JoinTable(
            name = "member_follow",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<MemberModel> following;

    @ManyToMany(mappedBy = "following")
    private List<MemberModel> followers;

    @ElementCollection
    @CollectionTable(name = "member_subject", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "name")
    private SortedSet<String> subjects;

    @OneToMany(mappedBy = "member")
    private List<CertificateModel> certificates;

    @OneToMany(mappedBy = "member")
    private List<CommentModel> comments;

    @OneToMany(mappedBy = "member")
    private List<BadgeModel> specializations;

    @OneToMany(mappedBy = "member")
    private List<MemberGuideSubModel> subscriptions;

    @OneToMany(mappedBy = "publisher")
    private List<StudyGuideModel> publishedGuides;

    @OneToMany(mappedBy = "publisher")
    private List<ContentModel> publishedContents;

    private LocalDateTime joinedDateTime;

    @OneToMany(mappedBy = "from")
    private List<DonationModel> sentDonations;

    @OneToMany(mappedBy = "to")
    private List<DonationModel> receivedDonations;

    private Boolean active;
}
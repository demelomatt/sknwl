package com.sknwl.shareknowledge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String biography;
    private List<SocialMedia> socialMedias;
    private List<Member> following;
    private List<Member> followers;
    private List<String> subjects;
    private List<StudyField> studyFields;
    private List<Certificate> certificates;
    private List<Comment> comments;
    private List<Badge> specializations;
    private List<MemberGuideSub> subscriptions;
    private List<StudyGuide> publishedGuides;
    private List<Content> publishedContents;
    private LocalDateTime joinedDateTime;
    private List<Donation> sentDonations;
    private List<Donation> receivedDonations;
    private Boolean active;
    private String email;
    private String pwd;
}

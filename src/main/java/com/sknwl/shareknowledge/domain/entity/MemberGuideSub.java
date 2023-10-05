package com.sknwl.shareknowledge.domain.entity;

import com.sknwl.shareknowledge.domain.entity.enums.StudyGuideStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberGuideSub {
    private Long id;
    private StudyGuideStatus status;
    private StudyGuide studyGuide;
    private Long rating;
    private LocalDateTime subscribedDateTime;
    private Member member;
}

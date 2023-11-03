package com.sknwl.shareknowledge.domain.entity;

import com.sknwl.shareknowledge.domain.entity.enums.StudyGuideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MemberGuideSub {
    private Long id;
    private StudyGuideStatus status;
    private StudyGuide studyGuide;
    private Member member;
    private LocalDateTime subscribedDateTime;
}

package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;
import java.util.List;

public record MemberResponse(Long id,
                             String name,
                             String biography,
                             List<String> subjects,
                             List<StudyFieldPayload> studyFields,
                             LocalDateTime joinedDateTime,
                             Boolean active,
                             List<SocialMediaPayload> socialMedias) {
}

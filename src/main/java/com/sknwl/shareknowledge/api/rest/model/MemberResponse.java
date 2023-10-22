package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public record MemberResponse(Long id,
                             String name,
                             String biography,
                             SortedSet<String> subjects,
                             List<StudyFieldPayload> studyFields,
                             LocalDateTime joinedDateTime,
                             Boolean active,
                             List<SocialMediaPayload> socialMedias) {
}

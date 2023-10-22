package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;
import java.util.SortedSet;

public record MemberRequest(Long id,
                            String name,
                            String biography,
                            List<String> subjects,
                            SortedSet<StudyFieldPayload> studyFields,
                            List<SocialMediaPayload> socialMedias,
                            Boolean active) {
}

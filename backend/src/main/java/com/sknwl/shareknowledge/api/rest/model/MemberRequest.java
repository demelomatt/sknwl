package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;

public record MemberRequest(Long id,
                            String name,
                            String email,
                            String pwd,
                            String biography,
                            List<String> subjects,
                            List<StudyFieldPayload> studyFields,
                            List<SocialMediaPayload> socialMedias,
                            Boolean active) {
}

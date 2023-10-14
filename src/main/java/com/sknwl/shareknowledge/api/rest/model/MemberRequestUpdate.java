package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;
import java.util.SortedSet;

public record MemberRequestUpdate(Long id, String name, String biography, List<String> subjects, SortedSet<StudyFieldRequest> studyFields, List<SocialMediaRequestUpdate> socialMedias, Boolean active) {
}

package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;
import java.util.SortedSet;

public record MemberRequest(String name, String biography, SortedSet<String> subjects, Boolean active, List<SocialMediaRequest> socialMedias) {
}

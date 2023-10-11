package com.sknwl.shareknowledge.api.rest.model;

import java.util.List;
import java.util.SortedSet;

public record MemberRequestCreate(String name, String biography, SortedSet<String> subjects, List<SocialMediaRequestCreate> socialMedias) {
}

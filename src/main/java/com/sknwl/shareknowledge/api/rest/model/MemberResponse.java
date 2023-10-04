package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public record MemberResponse(Long id, String name, String biography, List<String> socialMedia,
                             List<MemberResponse> followers,
                             List<MemberResponse> following, SortedSet<String> subjects,
                             List<CertificateResponse> certificates, List<BadgeResponse> specializations,
                             List<MemberGuideSubResponse> subscriptions,
                             List<StudyGuideResponse> publishedGuides, List<ContentResponse> publishedContents, LocalDateTime joinedDateTime, List<DonationResponse> sentDonations,
                             List<DonationResponse> receivedDonations, Boolean active) {
}

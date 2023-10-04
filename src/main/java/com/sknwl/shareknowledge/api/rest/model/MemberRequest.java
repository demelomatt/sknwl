package com.sknwl.shareknowledge.api.rest.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public record MemberRequest(Long id, String name, String biography, List<String> socialMedia,
                            List<MemberRequest> followers,
                            List<MemberRequest> following, SortedSet<String> subjects,
                            List<CertificateRequest> certificates, List<BadgeRequest> specializations,
                            List<MemberGuideSubRequest> subscriptions,
                            List<StudyGuideRequest> publishedGuides, List<ContentRequest> publishedContents, LocalDateTime joinedDateTime, List<DonationRequest> sentDonations,
                            List<DonationRequest> receivedDonations, Boolean active) {
}

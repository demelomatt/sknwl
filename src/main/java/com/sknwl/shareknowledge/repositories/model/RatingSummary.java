package com.sknwl.shareknowledge.repositories.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingSummary {
    private Long contentId;
    private Long count;
    private Double average;
}

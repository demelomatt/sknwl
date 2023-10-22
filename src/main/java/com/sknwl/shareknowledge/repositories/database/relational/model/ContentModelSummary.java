package com.sknwl.shareknowledge.repositories.database.relational.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentModelSummary {
    private ContentModel content;
    private Long count;
    private Double average;
}

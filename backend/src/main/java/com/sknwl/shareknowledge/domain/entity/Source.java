package com.sknwl.shareknowledge.domain.entity;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Source {
    private Long id;
    private String name;
    private String webSiteUri;
    private List<ContentType> contentTypes;
    private String iconUrl;
}

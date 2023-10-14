package com.sknwl.shareknowledge.repositories.database.relational.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="source")
public class SourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String webSiteUri;

    @ElementCollection
    @CollectionTable(name = "source_content", joinColumns = @JoinColumn(name = "source_id"))
    @Column(name = "name")
    private List<ContentType> contentTypes;

    private String iconUrl;
}

package com.sknwl.shareknowledge.repositories.database.relational.model;

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
@Table(name="cover_image")
public class CoverImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_image_author_id", referencedColumnName = "id")
    private ImageAuthorModel author;

    @OneToMany(mappedBy = "coverImage", cascade = CascadeType.ALL)
    private List<CoverUrlModel> urls;
}

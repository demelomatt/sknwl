package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cover_image_author")
public class ImageAuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;
    private String name;
    private String userName;
    private String profileUrl;
}

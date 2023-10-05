package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.SocialMediaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaJpaRepository extends JpaRepository<SocialMediaModel, Long> {
}

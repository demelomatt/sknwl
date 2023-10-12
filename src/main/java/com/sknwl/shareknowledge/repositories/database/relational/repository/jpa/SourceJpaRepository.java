package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceJpaRepository extends JpaRepository<SourceModel, Long> {
    List<SourceModel> findTop30ByWebSiteUriContainingIgnoreCase(String uri);
}

package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.domain.entity.Source;
import com.sknwl.shareknowledge.repositories.database.relational.model.SourceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SourceJpaRepository extends JpaRepository<SourceModel, Long> {

    List<SourceModel> findFirst30ByOrderByWebSiteUriAsc();
    List<SourceModel> findTop30ByWebSiteUriContainingIgnoreCase(String uri);

    Optional<SourceModel> findFirstByWebSiteUri(String uri);
}

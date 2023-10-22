package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModelSummary;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentRatingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ContentRatingJpaRepository extends JpaRepository<ContentRatingModel, Long> {

    @Query("SELECT new com.sknwl.shareknowledge.repositories.database.relational.model.ContentModelSummary(r.content, COALESCE(COUNT(r), 0), COALESCE(AVG(r.rating), 0.0)) FROM ContentRatingModel r WHERE r.content.id = :contentId GROUP BY r.content.id")
    ContentModelSummary findRatingCountAndAverageByContentId(@Param("contentId") Long contentId);

    @Query("SELECT new com.sknwl.shareknowledge.repositories.database.relational.model.ContentModelSummary(r.content, COALESCE(COUNT(r), 0), COALESCE(AVG(r.rating), 0.0)) FROM ContentRatingModel r WHERE r.content.id IN :contentIds GROUP BY r.content.id")
    Map<Long, ContentModelSummary> findRatingCountAndAverageByContentIds(@Param("contentIds") List<Long> contentIds);
}

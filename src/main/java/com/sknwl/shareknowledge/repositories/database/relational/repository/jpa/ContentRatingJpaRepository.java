package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.repositories.database.relational.model.ContentRatingModel;
import com.sknwl.shareknowledge.repositories.model.RatingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ContentRatingJpaRepository extends JpaRepository<ContentRatingModel, Long> {
    RatingSummary summary = new com.sknwl.shareknowledge.repositories.model.RatingSummary(1L, 0L, 0.0);
    @Query("SELECT new com.sknwl.shareknowledge.repositories.model.RatingSummary(r.content.id, COALESCE(COUNT(r), 0), COALESCE(AVG(r.rating), 0.0)) FROM ContentRatingModel r WHERE r.content.id = :contentId GROUP BY r.content.id")
    RatingSummary findRatingCountAndAverageByContentId(@Param("contentId") Long contentId);

    @Query("SELECT new com.sknwl.shareknowledge.repositories.model.RatingSummary(r.content.id, COALESCE(COUNT(r), 0), COALESCE(AVG(r.rating), 0.0)) FROM ContentRatingModel r WHERE r.content.id IN :contentIds GROUP BY r.content.id")
    Map<Long, RatingSummary> findRatingCountAndAverageByContentIds(@Param("contentIds") List<Long> contentIds);
}

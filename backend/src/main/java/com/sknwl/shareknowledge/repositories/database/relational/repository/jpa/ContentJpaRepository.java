package com.sknwl.shareknowledge.repositories.database.relational.repository.jpa;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModel;
import com.sknwl.shareknowledge.repositories.database.relational.model.ContentModelSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentJpaRepository extends JpaRepository<ContentModel, Long> {

    @Query("SELECT new com.sknwl.shareknowledge.repositories.database.relational.model.ContentModelSummary(c, COALESCE(COUNT(r), 0), COALESCE(AVG(r.rating), 0.0))" +
            "FROM ContentModel c " +
            "LEFT JOIN ContentRatingModel r ON c.id = r.content.id " +
            "WHERE (" +
            "   (:keyphrase IS NULL OR c.title LIKE %:keyphrase%) " +
            "   OR (:keyphrase IS NULL OR c.studyField.name LIKE %:keyphrase%) " +
            "   OR (:keyphrase IS NOT NULL AND EXISTS (SELECT s FROM c.subjects s WHERE s LIKE %:keyphrase%))" +
            ") " +
            "   AND (c.contentType IN :contentTypes) " +
            "   AND (:sourceIds IS NULL OR c.source.id IN :sourceIds) " +
            "   AND (c.language.id IN :languageIds) " +
            "   AND (:minDuration IS NULL OR c.durationMinutes >= :minDuration) " +
            "   AND (:maxDuration IS NULL OR c.durationMinutes <= :maxDuration) " +
            "   AND (:isFree IS NULL OR (:isFree = false AND c.price.price.amount > 0) OR(:isFree = true AND c.price.price.amount = 0)) " +
            "   AND (:fields IS NULL OR (LOWER(c.studyField.name) IN :fields)) " +
            "GROUP BY c.id " +
            "HAVING (:minRatings IS NULL OR COUNT(r) >= :minRatings) " +
            "ORDER BY " +
            "   CASE WHEN :sort = 'LATEST' THEN c.publishedDateTime END DESC, " +
            "   CASE WHEN :sort = 'OLDEST' THEN c.publishedDateTime END ASC, " +
            "   CASE WHEN :sort = 'RATING_AVG' THEN AVG(r.rating) END DESC, " +
            "   CASE WHEN :sort = 'RATING_COUNT' THEN COUNT(r) END DESC, " +
            "   CASE WHEN :sort = 'RATING_AVG_COUNT' THEN COUNT(r) END DESC, AVG(r.rating) DESC")
    Page<ContentModelSummary> findContents(
            @Param("keyphrase") String keyphrase,
            @Param("contentTypes") List<ContentType> contentTypes,
            @Param("isFree") Boolean isFree,
            @Param("sourceIds") List<Long> sourceIds,
            @Param("languageIds") List<Long> languageIds,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration,
            @Param("minRatings") Integer minRatings,
            @Param("fields")  List<String> fields,
            @Param("sort") String sort,
            Pageable pageable
    );
}
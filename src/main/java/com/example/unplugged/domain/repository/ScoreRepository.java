package com.example.unplugged.domain.repository;


import com.example.unplugged.domain.entity.ScoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    // update score_table set score_hits=score_hits+1 where id=?
    @Modifying
    @Query(value = "update ScoreEntity b set b.scoreHits=b.scoreHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

    Page<ScoreEntity> findByScoreTitleContaining(String scoreTitle, Pageable pageable);

    Page<ScoreEntity> findByScoreContentsContaining(String scoreContents, Pageable pageable);

}















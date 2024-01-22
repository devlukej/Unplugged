package com.example.unplugged.domain.repository;


import com.example.unplugged.domain.entity.SuggestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuggestionRepository extends JpaRepository<SuggestionEntity, Long> {
    // update suggestion_table set suggestion_hits=suggestion_hits+1 where id=?
    @Modifying
    @Query(value = "update SuggestionEntity b set b.suggestionHits=b.suggestionHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

    Page<SuggestionEntity> findBySuggestionTitleContaining(String suggestionTitle, Pageable pageable);

    Page<SuggestionEntity> findBySuggestionContentsContaining(String suggestionContents, Pageable pageable);

}















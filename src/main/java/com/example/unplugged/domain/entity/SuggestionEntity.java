package com.example.unplugged.domain.entity;


import com.example.unplugged.dto.SuggestionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "suggestion_table")
public class SuggestionEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String suggestionWriter;

    @Column
    private String suggestionTitle;

    @Column(length = 500)
    private String suggestionContents;

    @Column
    private int suggestionHits;


    public static SuggestionEntity toSaveEntity(SuggestionDTO suggestionDTO) {
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setSuggestionWriter(suggestionDTO.getSuggestionWriter());
        suggestionEntity.setSuggestionTitle(suggestionDTO.getSuggestionTitle());
        suggestionEntity.setSuggestionContents(suggestionDTO.getSuggestionContents());
        suggestionEntity.setSuggestionHits(0);
        return suggestionEntity;
    }

    public static SuggestionEntity toUpdateEntity(SuggestionDTO suggestionDTO) {
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setId(suggestionDTO.getId());
        suggestionEntity.setSuggestionWriter(suggestionDTO.getSuggestionWriter());
        suggestionEntity.setSuggestionTitle(suggestionDTO.getSuggestionTitle());
        suggestionEntity.setSuggestionContents(suggestionDTO.getSuggestionContents());
        suggestionEntity.setSuggestionHits(suggestionDTO.getSuggestionHits());
        return suggestionEntity;
    }
}












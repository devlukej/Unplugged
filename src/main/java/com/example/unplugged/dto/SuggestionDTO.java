package com.example.unplugged.dto;


import com.example.unplugged.domain.entity.SuggestionEntity;
import lombok.*;

import java.time.LocalDateTime;

// DTO(Data Transfer Object), VO, Bean,         Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class SuggestionDTO {
    private Long id;
    private String suggestionWriter;
    private String suggestionTitle;
    private String suggestionContents;
    private int suggestionHits;
    private LocalDateTime suggestionCreatedTime;
    private LocalDateTime suggestionUpdatedTime;

    public SuggestionDTO(Long id, String suggestionWriter, String suggestionTitle, String suggestionContents, int suggestionHits, LocalDateTime suggestionCreatedTime) {
        this.id = id;
        this.suggestionWriter = suggestionWriter;
        this.suggestionTitle = suggestionTitle;
        this.suggestionContents = suggestionContents;
        this.suggestionHits = suggestionHits;
        this.suggestionCreatedTime = suggestionCreatedTime;
    }

    public static SuggestionDTO toSuggestionDTO(SuggestionEntity suggestionEntity) {
        SuggestionDTO suggestionDTO = new SuggestionDTO();
        suggestionDTO.setId(suggestionEntity.getId());
        suggestionDTO.setSuggestionWriter(suggestionEntity.getSuggestionWriter());
        suggestionDTO.setSuggestionTitle(suggestionEntity.getSuggestionTitle());
        suggestionDTO.setSuggestionContents(suggestionEntity.getSuggestionContents());
        suggestionDTO.setSuggestionHits(suggestionEntity.getSuggestionHits());
        suggestionDTO.setSuggestionCreatedTime(suggestionEntity.getCreatedTime());
        suggestionDTO.setSuggestionUpdatedTime(suggestionEntity.getUpdatedTime());

        return suggestionDTO;
    }
}

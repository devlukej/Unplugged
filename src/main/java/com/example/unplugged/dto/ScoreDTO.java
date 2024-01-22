package com.example.unplugged.dto;


import com.example.unplugged.domain.entity.ScoreEntity;
import lombok.*;

import java.time.LocalDateTime;

// DTO(Data Transfer Object), VO, Bean,         Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class ScoreDTO {
    private Long id;
    private String scoreWriter;
    private String scoreTitle;
    private String scoreContents;
    private int scoreHits;
    private LocalDateTime scoreCreatedTime;
    private LocalDateTime scoreUpdatedTime;

    public ScoreDTO(Long id, String scoreWriter, String scoreTitle, String scoreContents, int scoreHits, LocalDateTime scoreCreatedTime) {
        this.id = id;
        this.scoreWriter = scoreWriter;
        this.scoreTitle = scoreTitle;
        this.scoreContents = scoreContents;
        this.scoreHits = scoreHits;
        this.scoreCreatedTime = scoreCreatedTime;
    }

    public static ScoreDTO toScoreDTO(ScoreEntity scoreEntity) {
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setId(scoreEntity.getId());
        scoreDTO.setScoreWriter(scoreEntity.getScoreWriter());
        scoreDTO.setScoreTitle(scoreEntity.getScoreTitle());
        scoreDTO.setScoreContents(scoreEntity.getScoreContents());
        scoreDTO.setScoreHits(scoreEntity.getScoreHits());
        scoreDTO.setScoreCreatedTime(scoreEntity.getCreatedTime());
        scoreDTO.setScoreUpdatedTime(scoreEntity.getUpdatedTime());

        return scoreDTO;
    }
}

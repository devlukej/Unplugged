package com.example.unplugged.domain.entity;


import com.example.unplugged.dto.ScoreDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "score_table")
public class ScoreEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String scoreWriter;

    @Column
    private String scoreTitle;

    @Column(length = 500)
    private String scoreContents;

    @Column
    private int scoreHits;


    public static ScoreEntity toSaveEntity(ScoreDTO scoreDTO) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setScoreWriter(scoreDTO.getScoreWriter());
        scoreEntity.setScoreTitle(scoreDTO.getScoreTitle());
        scoreEntity.setScoreContents(scoreDTO.getScoreContents());
        scoreEntity.setScoreHits(0);
        return scoreEntity;
    }

    public static ScoreEntity toUpdateEntity(ScoreDTO scoreDTO) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setId(scoreDTO.getId());
        scoreEntity.setScoreWriter(scoreDTO.getScoreWriter());
        scoreEntity.setScoreTitle(scoreDTO.getScoreTitle());
        scoreEntity.setScoreContents(scoreDTO.getScoreContents());
        scoreEntity.setScoreHits(scoreDTO.getScoreHits());
        return scoreEntity;
    }
}












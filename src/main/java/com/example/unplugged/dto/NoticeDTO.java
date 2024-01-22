package com.example.unplugged.dto;


import com.example.unplugged.domain.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

// DTO(Data Transfer Object), VO, Bean,         Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class NoticeDTO {
    private Long id;
    private String noticeWriter;
    private String noticeTitle;
    private String noticeContents;
    private int noticeHits;
    private LocalDateTime noticeCreatedTime;
    private LocalDateTime noticeUpdatedTime;

    public NoticeDTO(Long id, String noticeWriter, String noticeTitle, String noticeContents, int noticeHits, LocalDateTime noticeCreatedTime) {
        this.id = id;
        this.noticeWriter = noticeWriter;
        this.noticeTitle = noticeTitle;
        this.noticeContents = noticeContents;
        this.noticeHits = noticeHits;
        this.noticeCreatedTime = noticeCreatedTime;
    }

    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeHits(noticeEntity.getNoticeHits());
        noticeDTO.setNoticeCreatedTime(noticeEntity.getCreatedTime());
        noticeDTO.setNoticeUpdatedTime(noticeEntity.getUpdatedTime());

        return noticeDTO;
    }
}

package com.example.unplugged.domain.repository;


import com.example.unplugged.domain.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    // update notice_table set notice_hits=notice_hits+1 where id=?
    @Modifying
    @Query(value = "update NoticeEntity b set b.noticeHits=b.noticeHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

    Page<NoticeEntity> findByNoticeTitleContaining(String noticeTitle, Pageable pageable);

    Page<NoticeEntity> findByNoticeContentsContaining(String noticeContents, Pageable pageable);

}















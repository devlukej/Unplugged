package com.example.unplugged.service;


import com.example.unplugged.domain.entity.NoticeEntity;
import com.example.unplugged.domain.repository.NoticeRepository;
import com.example.unplugged.dto.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    private ModelMapper modelMapper = new ModelMapper();
    public void save(NoticeDTO noticeDTO, MemberUser user) throws IOException {

            noticeDTO.setNoticeWriter(user.getName());

            NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
            noticeRepository.save(noticeEntity);
    }

    @Transactional
    public List<NoticeDTO> findAll() {
        List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for (NoticeEntity noticeEntity: noticeEntityList) {
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeEntity));
        }
        return noticeDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        noticeRepository.updateHits(id);
    }

    @Transactional
    public NoticeDTO findById(Long id) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if (optionalNoticeEntity.isPresent()) {
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;
        } else {
            return null;
        }
    }

    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
        return findById(noticeDTO.getId());
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public Page<NoticeDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<NoticeEntity> noticeEntities =
                noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록: id, writer, title, hits, createdTime
        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getId(), notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeContents(), notice.getNoticeHits(), notice.getCreatedTime()));
        return noticeDTOS;
    }

    public List<NoticeDTO> findLatestNotices(int count) {
        Page<NoticeEntity> latestNoticeEntities = noticeRepository.findAll(
                PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "createdTime"))
        );
        List<NoticeDTO> latestNotices = convertToNoticeDTOList(latestNoticeEntities.getContent());
        return latestNotices;
    }

    public List<NoticeDTO> convertToNoticeDTOList(List<NoticeEntity> noticeEntities) {
        return noticeEntities.stream()
                .map(noticeEntity -> modelMapper.map(noticeEntity, NoticeDTO.class))
                .collect(Collectors.toList());
    }

    //제목검색
    @Transactional
    public Page<NoticeDTO> searchNoticeTitle(String noticeTitle, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<NoticeEntity> noticeEntities = noticeRepository.findByNoticeTitleContaining(noticeTitle, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getId(), notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeContents(), notice.getNoticeHits(), notice.getCreatedTime()));

        return noticeDTOS;
    }

    @Transactional
    public Page<NoticeDTO> searchNoticeContents(String noticeContents, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<NoticeEntity> noticeEntities = noticeRepository.findByNoticeContentsContaining(noticeContents, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getId(), notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeContents(), notice.getNoticeHits(), notice.getCreatedTime()));

        return noticeDTOS;
    }

}















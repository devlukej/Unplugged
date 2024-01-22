package com.example.unplugged.service;


import com.example.unplugged.domain.entity.ScoreEntity;
import com.example.unplugged.domain.repository.ScoreRepository;
import com.example.unplugged.dto.ScoreDTO;
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
public class ScoreService {
    private final ScoreRepository scoreRepository;

    private ModelMapper modelMapper = new ModelMapper();
    public void save(ScoreDTO scoreDTO, MemberUser user) throws IOException {

            scoreDTO.setScoreWriter(user.getName());

            ScoreEntity scoreEntity = ScoreEntity.toSaveEntity(scoreDTO);
            scoreRepository.save(scoreEntity);
    }

    @Transactional
    public List<ScoreDTO> findAll() {
        List<ScoreEntity> scoreEntityList = scoreRepository.findAll();
        List<ScoreDTO> scoreDTOList = new ArrayList<>();
        for (ScoreEntity scoreEntity: scoreEntityList) {
            scoreDTOList.add(ScoreDTO.toScoreDTO(scoreEntity));
        }
        return scoreDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        scoreRepository.updateHits(id);
    }

    @Transactional
    public ScoreDTO findById(Long id) {
        Optional<ScoreEntity> optionalScoreEntity = scoreRepository.findById(id);
        if (optionalScoreEntity.isPresent()) {
            ScoreEntity scoreEntity = optionalScoreEntity.get();
            ScoreDTO scoreDTO = ScoreDTO.toScoreDTO(scoreEntity);
            return scoreDTO;
        } else {
            return null;
        }
    }

    public ScoreDTO update(ScoreDTO scoreDTO) {
        ScoreEntity scoreEntity = ScoreEntity.toUpdateEntity(scoreDTO);
        scoreRepository.save(scoreEntity);
        return findById(scoreDTO.getId());
    }

    public void delete(Long id) {
        scoreRepository.deleteById(id);
    }

    public Page<ScoreDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<ScoreEntity> scoreEntities =
                scoreRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록: id, writer, title, hits, createdTime
        Page<ScoreDTO> scoreDTOS = scoreEntities.map(score -> new ScoreDTO(score.getId(), score.getScoreWriter(), score.getScoreTitle(), score.getScoreContents(), score.getScoreHits(), score.getCreatedTime()));
        return scoreDTOS;
    }

    public List<ScoreDTO> findLatestScores(int count) {
        Page<ScoreEntity> latestScoreEntities = scoreRepository.findAll(
                PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "createdTime"))
        );
        List<ScoreDTO> latestScores = convertToScoreDTOList(latestScoreEntities.getContent());
        return latestScores;
    }

    public List<ScoreDTO> convertToScoreDTOList(List<ScoreEntity> scoreEntities) {
        return scoreEntities.stream()
                .map(scoreEntity -> modelMapper.map(scoreEntity, ScoreDTO.class))
                .collect(Collectors.toList());
    }

    //제목검색
    @Transactional
    public Page<ScoreDTO> searchScoreTitle(String scoreTitle, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<ScoreEntity> scoreEntities = scoreRepository.findByScoreTitleContaining(scoreTitle, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<ScoreDTO> scoreDTOS = scoreEntities.map(score -> new ScoreDTO(score.getId(), score.getScoreWriter(), score.getScoreTitle(), score.getScoreContents(), score.getScoreHits(), score.getCreatedTime()));

        return scoreDTOS;
    }

    @Transactional
    public Page<ScoreDTO> searchScoreContents(String scoreContents, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<ScoreEntity> scoreEntities = scoreRepository.findByScoreContentsContaining(scoreContents, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<ScoreDTO> scoreDTOS = scoreEntities.map(score -> new ScoreDTO(score.getId(), score.getScoreWriter(), score.getScoreTitle(), score.getScoreContents(), score.getScoreHits(), score.getCreatedTime()));

        return scoreDTOS;
    }

}















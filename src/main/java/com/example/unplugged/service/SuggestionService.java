package com.example.unplugged.service;


import com.example.unplugged.domain.entity.SuggestionEntity;
import com.example.unplugged.domain.repository.SuggestionRepository;
import com.example.unplugged.dto.SuggestionDTO;
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
public class SuggestionService {
    private final SuggestionRepository suggestionRepository;

    private ModelMapper modelMapper = new ModelMapper();
    public void save(SuggestionDTO suggestionDTO, MemberUser user) throws IOException {

            suggestionDTO.setSuggestionWriter(user.getName());

            SuggestionEntity suggestionEntity = SuggestionEntity.toSaveEntity(suggestionDTO);
            suggestionRepository.save(suggestionEntity);
    }

    @Transactional
    public List<SuggestionDTO> findAll() {
        List<SuggestionEntity> suggestionEntityList = suggestionRepository.findAll();
        List<SuggestionDTO> suggestionDTOList = new ArrayList<>();
        for (SuggestionEntity suggestionEntity: suggestionEntityList) {
            suggestionDTOList.add(SuggestionDTO.toSuggestionDTO(suggestionEntity));
        }
        return suggestionDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        suggestionRepository.updateHits(id);
    }

    @Transactional
    public SuggestionDTO findById(Long id) {
        Optional<SuggestionEntity> optionalSuggestionEntity = suggestionRepository.findById(id);
        if (optionalSuggestionEntity.isPresent()) {
            SuggestionEntity suggestionEntity = optionalSuggestionEntity.get();
            SuggestionDTO suggestionDTO = SuggestionDTO.toSuggestionDTO(suggestionEntity);
            return suggestionDTO;
        } else {
            return null;
        }
    }

    public SuggestionDTO update(SuggestionDTO suggestionDTO) {
        SuggestionEntity suggestionEntity = SuggestionEntity.toUpdateEntity(suggestionDTO);
        suggestionRepository.save(suggestionEntity);
        return findById(suggestionDTO.getId());
    }

    public void delete(Long id) {
        suggestionRepository.deleteById(id);
    }

    public Page<SuggestionDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<SuggestionEntity> suggestionEntities =
                suggestionRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록: id, writer, title, hits, createdTime
        Page<SuggestionDTO> suggestionDTOS = suggestionEntities.map(suggestion -> new SuggestionDTO(suggestion.getId(), suggestion.getSuggestionWriter(), suggestion.getSuggestionTitle(), suggestion.getSuggestionContents(), suggestion.getSuggestionHits(), suggestion.getCreatedTime()));
        return suggestionDTOS;
    }

    public List<SuggestionDTO> findLatestSuggestions(int count) {
        Page<SuggestionEntity> latestSuggestionEntities = suggestionRepository.findAll(
                PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "createdTime"))
        );
        List<SuggestionDTO> latestSuggestions = convertToSuggestionDTOList(latestSuggestionEntities.getContent());
        return latestSuggestions;
    }

    public List<SuggestionDTO> convertToSuggestionDTOList(List<SuggestionEntity> suggestionEntities) {
        return suggestionEntities.stream()
                .map(suggestionEntity -> modelMapper.map(suggestionEntity, SuggestionDTO.class))
                .collect(Collectors.toList());
    }

    //제목검색
    @Transactional
    public Page<SuggestionDTO> searchSuggestionTitle(String suggestionTitle, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<SuggestionEntity> suggestionEntities = suggestionRepository.findBySuggestionTitleContaining(suggestionTitle, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<SuggestionDTO> suggestionDTOS = suggestionEntities.map(suggestion -> new SuggestionDTO(suggestion.getId(), suggestion.getSuggestionWriter(), suggestion.getSuggestionTitle(), suggestion.getSuggestionContents(), suggestion.getSuggestionHits(), suggestion.getCreatedTime()));

        return suggestionDTOS;
    }

    @Transactional
    public Page<SuggestionDTO> searchSuggestionContents(String suggestionContents, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수

        Page<SuggestionEntity> suggestionEntities = suggestionRepository.findBySuggestionContentsContaining(suggestionContents, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        Page<SuggestionDTO> suggestionDTOS = suggestionEntities.map(suggestion -> new SuggestionDTO(suggestion.getId(), suggestion.getSuggestionWriter(), suggestion.getSuggestionTitle(), suggestion.getSuggestionContents(), suggestion.getSuggestionHits(), suggestion.getCreatedTime()));

        return suggestionDTOS;
    }

}















package com.example.unplugged.controller;


import com.example.unplugged.dto.ScoreDTO;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("/user/score/save")
    public String saveForm(@AuthenticationPrincipal MemberUser user,Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "board/score/save";
    }

    @PostMapping("/user/score/save")
    public String save(@AuthenticationPrincipal MemberUser user,@ModelAttribute ScoreDTO scoreDTO,Model model) throws IOException {

        if (user != null) {
            // 현재 로그인한 사용자 정보를 이용하여 작성자 필드 설정
            scoreDTO.setScoreWriter(user.getName());
        }

        scoreService.save(scoreDTO, user);

        model.addAttribute("user", user);

       return "redirect:/user/score";

    }

    @GetMapping("/user/score")
    public String findAll(@AuthenticationPrincipal MemberUser user,Model model, @PageableDefault(page = 1) Pageable pageable) {


        if (user != null) {

            model.addAttribute("user", user);
        }

        Page<ScoreDTO> scoreList = scoreService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = Math.min(startPage + blockLimit - 1, scoreList.getTotalPages());


        model.addAttribute("scoreList", scoreList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/score/score";
    }

    @GetMapping("/user/score/{id}")
    public String findById(@AuthenticationPrincipal MemberUser user,
            @PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */

        if (user == null) {

            return "redirect:/login";
        }

        scoreService.updateHits(id);
        ScoreDTO scoreDTO = scoreService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("score", scoreDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board/score/detail";
    }

    @GetMapping("/user/score/update/{id}")
    public String updateForm(@AuthenticationPrincipal MemberUser user,@PathVariable Long id, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        ScoreDTO scoreDTO = scoreService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("scoreUpdate", scoreDTO);
        return "board/score/update";
    }

    @PostMapping("/user/score/update")
    public String update(@ModelAttribute ScoreDTO scoreDTO, Model model) {
        ScoreDTO score = scoreService.update(scoreDTO);
        model.addAttribute("score", score);
//        return "redirect:detail";

        return "redirect:/user/score/" + scoreDTO.getId();
    }

    @GetMapping("/user/score/delete/{id}")
    public String delete(@PathVariable Long id) {
        scoreService.delete(id);

        return "redirect:/user/score";
    }
    //제목검색
    @GetMapping("/user/score/scoreTitle")
    public String searchScoreTitle(@RequestParam(value = "scoreTitle") String scoreTitle, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(scoreTitle, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/score";
        } else {

            Page<ScoreDTO> scoreList = scoreService.searchScoreTitle(scoreTitle, pageable);

            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, scoreList.getTotalPages());

            model.addAttribute("scoreList", scoreList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/score/score";
    }

    //내용검색
    @GetMapping("/user/score/scoreContents")
    public String searchScoreContents(@RequestParam(value = "scoreContents") String scoreContents, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(scoreContents, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/score";
        } else {

            Page<ScoreDTO> scoreList = scoreService.searchScoreContents(scoreContents, pageable);


            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, scoreList.getTotalPages());

            model.addAttribute("scoreList", scoreList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/score/score";
    }
}











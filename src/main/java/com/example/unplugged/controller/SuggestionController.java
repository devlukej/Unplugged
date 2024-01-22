package com.example.unplugged.controller;


import com.example.unplugged.dto.SuggestionDTO;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.SuggestionService;
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
public class SuggestionController {
    private final SuggestionService suggestionService;

    @GetMapping("/user/suggestion/save")
    public String saveForm(@AuthenticationPrincipal MemberUser user,Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "board/suggestion/save";
    }

    @PostMapping("/user/suggestion/save")
    public String save(@AuthenticationPrincipal MemberUser user,@ModelAttribute SuggestionDTO suggestionDTO,Model model) throws IOException {

        if (user != null) {
            // 현재 로그인한 사용자 정보를 이용하여 작성자 필드 설정
            suggestionDTO.setSuggestionWriter(user.getName());
        }

        suggestionService.save(suggestionDTO, user);

        model.addAttribute("user", user);

       return "redirect:/user/suggestion";

    }

    @GetMapping("/user/suggestion")
    public String findAll(@AuthenticationPrincipal MemberUser user,Model model, @PageableDefault(page = 1) Pageable pageable) {


        if (user != null) {

            model.addAttribute("user", user);
        }

        Page<SuggestionDTO> suggestionList = suggestionService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = Math.min(startPage + blockLimit - 1, suggestionList.getTotalPages());


        model.addAttribute("suggestionList", suggestionList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/suggestion/suggestion";
    }

    @GetMapping("/user/suggestion/{id}")
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

        suggestionService.updateHits(id);
        SuggestionDTO suggestionDTO = suggestionService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("suggestion", suggestionDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board/suggestion/detail";
    }

    @GetMapping("/user/suggestion/update/{id}")
    public String updateForm(@AuthenticationPrincipal MemberUser user,@PathVariable Long id, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        SuggestionDTO suggestionDTO = suggestionService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("suggestionUpdate", suggestionDTO);
        return "board/suggestion/update";
    }

    @PostMapping("/user/suggestion/update")
    public String update(@ModelAttribute SuggestionDTO suggestionDTO, Model model) {
        SuggestionDTO suggestion = suggestionService.update(suggestionDTO);
        model.addAttribute("suggestion", suggestion);
//        return "redirect:detail";

        return "redirect:/user/suggestion/" + suggestionDTO.getId();
    }

    @GetMapping("/user/suggestion/delete/{id}")
    public String delete(@PathVariable Long id) {
        suggestionService.delete(id);

        return "redirect:/user/suggestion";
    }
    //제목검색
    @GetMapping("/user/suggestion/suggestionTitle")
    public String searchSuggestionTitle(@RequestParam(value = "suggestionTitle") String suggestionTitle, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(suggestionTitle, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/suggestion";
        } else {

            Page<SuggestionDTO> suggestionList = suggestionService.searchSuggestionTitle(suggestionTitle, pageable);

            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, suggestionList.getTotalPages());

            model.addAttribute("suggestionList", suggestionList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/suggestion/suggestion";
    }

    //내용검색
    @GetMapping("/user/suggestion/suggestionContents")
    public String searchSuggestionContents(@RequestParam(value = "suggestionContents") String suggestionContents, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(suggestionContents, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/suggestion";
        } else {

            Page<SuggestionDTO> suggestionList = suggestionService.searchSuggestionContents(suggestionContents, pageable);


            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, suggestionList.getTotalPages());

            model.addAttribute("suggestionList", suggestionList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/suggestion/suggestion";
    }
}











package com.example.unplugged.controller;


import com.example.unplugged.dto.NoticeDTO;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.NoticeService;
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
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/user/notice/save")
    public String saveForm(@AuthenticationPrincipal MemberUser user,Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "board/notice/save";
    }

    @PostMapping("/user/notice/save")
    public String save(@AuthenticationPrincipal MemberUser user,@ModelAttribute NoticeDTO noticeDTO,Model model) throws IOException {

        if (user != null) {
            // 현재 로그인한 사용자 정보를 이용하여 작성자 필드 설정
            noticeDTO.setNoticeWriter(user.getName());
        }

        noticeService.save(noticeDTO, user);

        model.addAttribute("user", user);

       return "redirect:/user/notice";

    }

    @GetMapping("/user/notice")
    public String findAll(@AuthenticationPrincipal MemberUser user,Model model, @PageableDefault(page = 1) Pageable pageable) {


        if (user != null) {

            model.addAttribute("user", user);
        }

        Page<NoticeDTO> noticeList = noticeService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = Math.min(startPage + blockLimit - 1, noticeList.getTotalPages());


        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/notice/notice";
    }

    @GetMapping("/user/notice/{id}")
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

        noticeService.updateHits(id);
        NoticeDTO noticeDTO = noticeService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("notice", noticeDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "board/notice/detail";
    }

    @GetMapping("/user/notice/update/{id}")
    public String updateForm(@AuthenticationPrincipal MemberUser user,@PathVariable Long id, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("noticeUpdate", noticeDTO);
        return "board/notice/update";
    }

    @PostMapping("/user/notice/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
//        return "redirect:detail";

        return "redirect:/user/notice/" + noticeDTO.getId();
    }

    @GetMapping("/user/notice/delete/{id}")
    public String delete(@PathVariable Long id) {
        noticeService.delete(id);

        return "redirect:/user/notice";
    }
    //제목검색
    @GetMapping("/user/notice/noticeTitle")
    public String searchNoticeTitle(@RequestParam(value = "noticeTitle") String noticeTitle, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(noticeTitle, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/notice";
        } else {

            Page<NoticeDTO> noticeList = noticeService.searchNoticeTitle(noticeTitle, pageable);

            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, noticeList.getTotalPages());

            model.addAttribute("noticeList", noticeList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/notice/notice";
    }

    //내용검색
    @GetMapping("/user/notice/noticeContents")
    public String searchNoticeContents(@RequestParam(value = "noticeContents") String noticeContents, Model model, @AuthenticationPrincipal MemberUser user, @PageableDefault(page = 1) Pageable pageable) {

        model.addAttribute("user", user);

        if (Objects.equals(noticeContents, "")) {
            // 검색어가 비어있을 경우 기본 페이지로 리다이렉트
            return "redirect:/user/notice";
        } else {

            Page<NoticeDTO> noticeList = noticeService.searchNoticeContents(noticeContents, pageable);


            int blockLimit = 3;
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
            int endPage = Math.min(startPage + blockLimit - 1, noticeList.getTotalPages());

            model.addAttribute("noticeList", noticeList);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        }

        return "board/notice/notice";
    }
}











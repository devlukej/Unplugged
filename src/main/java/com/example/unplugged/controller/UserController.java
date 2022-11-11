package com.example.unplugged.controller;

import com.example.unplugged.dto.UserDto;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String list() {

        return "board/main";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String dispSignup() {
        return "user/signup";
    }


    // 회원가입 처리
    @PostMapping("/signup")
    public String execSignup(UserDto userDto) {


        userService.joinUser(userDto);

        return "redirect:/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String dispLogin() {

        return "user/login";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String dispDenied() {

        return "user/denied";
    }

    // 내 정보 페이지
    @GetMapping("/myinfo")
    public String dispMyInfo(@AuthenticationPrincipal MemberUser user, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "user/myinfo";
    }

    @GetMapping("/user/event")
    public String dispEvent() {

        return "user/event";
    }

    @GetMapping("/user/notice")
    public String dispNotice() {

        return "user/notice";
    }

    @GetMapping("/admin/userJoin")
    public String dispUserJoin(@AuthenticationPrincipal MemberUser user, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "admin/userJoin";
    }

    @GetMapping("/admin/userList")
    public String dispUserList() {

        return "admin/userList";
    }
}

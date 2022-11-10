package com.example.unplugged.controller;

import com.example.unplugged.dto.UserDto;
import com.example.unplugged.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
    public String dispMyInfo() {
        return "user/myinfo";
    }

    @GetMapping("/event")
    public String dispEvent() {
        return "board/event";
    }

    @GetMapping("/notice")
    public String dispNotice() {
        return "board/notice";
    }

    @GetMapping("/admin/userJoin")
    public String dispUserJoin() {
        return "board/userJoin";
    }
    @GetMapping("/admin/userList")
    public String dispUserList() {
        return "board/userList";
    }
}

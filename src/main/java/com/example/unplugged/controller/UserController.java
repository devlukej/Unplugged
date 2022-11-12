package com.example.unplugged.controller;

import com.example.unplugged.dto.UserDto;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


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
    public String dispEvent(@AuthenticationPrincipal MemberUser user) {

        if (user == null) {

            return "redirect:/login";
        }
        return "user/event";
    }

    @GetMapping("/user/notice")
    public String dispNotice(@AuthenticationPrincipal MemberUser user) {

        if (user == null) {

            return "redirect:/login";
        }

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
    public String dispUserList(@AuthenticationPrincipal MemberUser user, Model model,@RequestParam(value="page", defaultValue = "1") Integer pageNum){

//        if (user == null) {
//
//            return "redirect:/login";
//        }

        List<UserDto> userList = userService.getUserlist(pageNum);
        Integer[] pageList = userService.getPageList(pageNum);

        model.addAttribute("userList",userList);
        model.addAttribute("pageList", pageList);

        return "admin/userList";
    }

    //이름검색
    @GetMapping("/admin/userList/nameKeyword")
    public String searchUserName(@RequestParam(value="nameKeyword") String nameKeyword , Model model) {

        List<UserDto> userDtoList = userService.searchUserName(nameKeyword);

        model.addAttribute("userList", userDtoList);
        return "/admin/userList";
    }
    //기수검색
    @GetMapping("/admin/userList/yearKeyword")
    public String searchUserYear(@RequestParam(value="yearKeyword") String yearKeyword , Model model) {

        List<UserDto> userDtoList = userService.searchUserYear(yearKeyword);

        model.addAttribute("userList", userDtoList);
        return "/admin/userList";
    }

    //세션검색
    @GetMapping("/admin/userList/sessionKeyword")
    public String searchUserSession(@RequestParam(value="sessionKeyword") String sessionKeyword , Model model) {

        List<UserDto> userDtoList = userService.searchUserSession(sessionKeyword);

        model.addAttribute("userList", userDtoList);
        return "/admin/userList";
    }

    //직급검색
    @GetMapping("/admin/userList/positionKeyword")
    public String searchUserPosition(@RequestParam(value="positionKeyword") String positionKeyword , Model model) {

        List<UserDto> userDtoList = userService.searchUserPosition(positionKeyword);

        model.addAttribute("userList", userDtoList);
        return "/admin/userList";
    }
    
}

package com.example.unplugged.controller;

import com.example.unplugged.dto.UserDto;
import com.example.unplugged.service.MemberUser;
import com.example.unplugged.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String list(@AuthenticationPrincipal MemberUser user, Model model) {

        model.addAttribute("user", user);
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
    public String dispEvent(@AuthenticationPrincipal MemberUser user, Model model) {

        /* 임시로 해제했어요 - 호준
        if (user == null) {
            return "redirect:/login";
        }
        */

        model.addAttribute("user", user);
        return "board/event";
    }

    @GetMapping("/user/event/add")
    public String dispEventWrite(@AuthenticationPrincipal MemberUser user, Model model) {

        /* 임시로 해제했어요 - 호준
        if (user == null) {
            return "redirect:/login";
        }
        */

        model.addAttribute("user", user);
        return "board/eventadd";
    }


    @GetMapping("/user/notice")
    public String dispNotice(@AuthenticationPrincipal MemberUser user, Model model) {

        if (user == null) {

            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "user/notice";
    }

    @GetMapping("/admin/userJoin")
    public String dispUserJoin(@AuthenticationPrincipal MemberUser user, Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

//        if (user == null) {
//
//            return "redirect:/login";
//        }

        List<UserDto> userList = userService.getUserlist(pageNum);
        Integer[] pageList = userService.getPageList(pageNum);
        model.addAttribute("user", user);

        model.addAttribute("userList", userList);
        model.addAttribute("pageList", pageList);

        return "admin/userJoin";
    }

    @GetMapping("/admin/userList")
    public String dispUserList(@AuthenticationPrincipal MemberUser user, Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

//        if (user == null) {
//
//            return "redirect:/login";
//        }

        List<UserDto> userList = userService.getUserlist(pageNum);
        Integer[] pageList = userService.getPageList(pageNum);

        model.addAttribute("user", user);

        model.addAttribute("userList", userList);
        model.addAttribute("pageList", pageList);

        return "admin/userList";
    }

    //이름검색
    @GetMapping("/admin/userList/nameKeyword")
    public String searchUserName(@RequestParam(value = "nameKeyword") String nameKeyword, Model model, @AuthenticationPrincipal MemberUser user) {

        model.addAttribute("user", user);

        if (Objects.equals(nameKeyword, "")) {

            return "redirect:/admin/userList";

        } else {

            List<UserDto> userDtoList = userService.searchUserName(nameKeyword);
            model.addAttribute("userList", userDtoList);
        }

        return "/admin/userList";
    }

    //기수검색
    @GetMapping("/admin/userList/yearKeyword")
    public String searchUserYear(@RequestParam(value = "yearKeyword") String yearKeyword, Model model, @AuthenticationPrincipal MemberUser user) {

        model.addAttribute("user", user);
        if (Objects.equals(yearKeyword, "")) {

            return "redirect:/admin/userList";

        } else {
            List<UserDto> userDtoList = userService.searchUserYear(yearKeyword);
            model.addAttribute("userList", userDtoList);
        }
        return "/admin/userList";
    }

    //세션검색
    @GetMapping("/admin/userList/sessionKeyword")
    public String searchUserSession(@RequestParam(value = "sessionKeyword") String sessionKeyword, Model model, @AuthenticationPrincipal MemberUser user) {

        model.addAttribute("user", user);
        if (Objects.equals(sessionKeyword, "")) {

            return "redirect:/admin/userList";

        } else {
            List<UserDto> userDtoList = userService.searchUserSession(sessionKeyword);
            model.addAttribute("userList", userDtoList);
        }
        return "/admin/userList";
    }

    //직급검색
    @GetMapping("/admin/userList/positionKeyword")
    public String searchUserPosition(@RequestParam(value = "positionKeyword") String positionKeyword, Model model, @AuthenticationPrincipal MemberUser user) {

        model.addAttribute("user", user);
        if (Objects.equals(positionKeyword, "")){

            return "redirect:/admin/userList";

        } else {
            List<UserDto> userDtoList = userService.searchUserPosition(positionKeyword);
            model.addAttribute("userList", userDtoList);
        }
        return "/admin/userList";
    }

}

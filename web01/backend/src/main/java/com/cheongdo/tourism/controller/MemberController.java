package com.cheongdo.tourism.controller;

import com.cheongdo.tourism.entity.Member;
import com.cheongdo.tourism.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String redirectURL,
                            Model model) {
        if (error != null) model.addAttribute("errorMsg", "이메일 또는 비밀번호가 올바르지 않습니다.");
        model.addAttribute("redirectURL", redirectURL);
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam(required = false) String redirectURL,
                        HttpSession session,
                        Model model) {
        try {
            Member member = memberService.login(email, password);
            session.setAttribute("loginMember", member);
            return "redirect:" + (redirectURL != null && !redirectURL.isBlank() ? redirectURL : "/");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "member/login";
        }
    }

    @GetMapping("/signup")
    public String signupForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) model.addAttribute("errorMsg", "이미 사용 중인 이메일입니다.");
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String phone,
                         Model model) {
        try {
            memberService.join(name, email, password, phone);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "member/signup";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

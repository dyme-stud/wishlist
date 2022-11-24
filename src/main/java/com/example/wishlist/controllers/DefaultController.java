package com.example.wishlist.controllers;

import com.example.wishlist.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class DefaultController {

    private final UserService userService;

    @GetMapping("/")
    public String getMain(Model model, HttpServletResponse response) {
        var currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        var currentUserId = userService.get(currentUserName).getId();
        var cookie = new Cookie("user_id", currentUserId.toString());
        response.addCookie(cookie);
        return "redirect:/wishlist";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}

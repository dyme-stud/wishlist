package com.example.wishlist.controllers;

import com.example.wishlist.exceptions.UserExistException;
import com.example.wishlist.exceptions.UserNotFoundException;
import com.example.wishlist.models.User;
import com.example.wishlist.services.user.UserService;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}

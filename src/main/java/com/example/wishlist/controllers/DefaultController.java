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

@RequiredArgsConstructor
@Controller
public class DefaultController {
    @GetMapping("/")
    public String getMain(Model model) {
        var currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return "redirect:/wishlist/1";
    }
}

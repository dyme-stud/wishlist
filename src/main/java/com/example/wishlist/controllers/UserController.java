package com.example.wishlist.controllers;

import com.example.wishlist.exceptions.UserExistException;
import com.example.wishlist.exceptions.UserNotFoundException;
import com.example.wishlist.models.User;
import com.example.wishlist.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public String create(User user, Model model) {
        try {
            userService.create(user);
            return "redirect:/login";
        } catch (UserExistException ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}

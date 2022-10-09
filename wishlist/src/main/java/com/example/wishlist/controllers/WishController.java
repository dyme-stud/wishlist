package com.example.wishlist.controllers;

import com.example.wishlist.services.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @GetMapping("/create")
    public String create(){
        return "OK";
    }
}

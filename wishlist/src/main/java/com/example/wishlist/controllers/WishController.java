package com.example.wishlist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    @GetMapping("/create")
    public String create(){
        return "OK";
    }
}

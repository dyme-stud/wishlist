package com.example.wishlist.controllers;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/create")
    public User create() {
        return userRepository.save(
                User.builder()
                        .firstName("Matvey")
                        .lastName("Ilichev")
                        .wishlists(Arrays.asList(
                                Wishlist
                                        .builder()
                                        .name("first_list")
                                        .wishes(Arrays.asList(
                                                Wish
                                                        .builder()
                                                        .name("123")
                                                        .build()
                                        ))
                                        .build()))
                        .build());
    }
}

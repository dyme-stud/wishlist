package com.example.wishlist.controllers;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/{userId}")
    public List<Wishlist> getAllWishlist(@PathVariable Long userId) {
        return userService.getAllWishlists(userId);
    }

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

package com.example.wishlist.controllers;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.services.user.UserService;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;


    @GetMapping("/getAllWishlist/{userId}")
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

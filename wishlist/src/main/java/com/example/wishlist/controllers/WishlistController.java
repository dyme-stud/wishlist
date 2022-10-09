package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final IWishlistService wishlistService;

    @GetMapping("/{wishlistId}")
    public Wishlist getWishlist(@PathVariable Long wishlistId){
        return wishlistService.get(wishlistId);
    }
}

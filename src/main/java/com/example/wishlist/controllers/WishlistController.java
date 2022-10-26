package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("/{userId}")
    public List<Wishlist> get(@PathVariable Long userId) {
        return wishlistService.getWishlists(userId);
    }

    @PostMapping("/{userId}")
    public Wishlist create(@PathVariable Long userId, @RequestBody Wishlist wishlist) {
        return wishlistService.createWishlist(wishlist, userId);
    }

    @DeleteMapping("/{wishlistId}")
    public void delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

}

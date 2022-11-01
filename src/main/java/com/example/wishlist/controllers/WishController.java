package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wish.WishService;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@Controller
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/{userId}/{wishlistId}")
    public String create(@PathVariable Long userId, @PathVariable Long wishlistId, Wish wish) {
        wishService.createWish(wish, wishlistId);
        return MessageFormat.format("redirect:/wishlist/{0}/{1}", userId, wishlistId);
    }

    @PatchMapping("/{wishId}")
    public Wish update(@PathVariable Long wishId, @RequestBody Wish wish) {
        return wishService.updateWish(wish, wishId);
    }

    @DeleteMapping("/{wishId}")
    public void delete(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
    }
}

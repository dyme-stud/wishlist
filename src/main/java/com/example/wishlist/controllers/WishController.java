package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.services.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/{wishlistId}")
    public Wish create(@PathVariable Long wishlistId, @RequestBody Wish wish) {
        return wishService.createWish(wish, wishlistId);
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

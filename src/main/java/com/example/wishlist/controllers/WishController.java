package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.services.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

@Controller
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/{wishlistId}")
    public String create(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishlistId, Wish wish, @RequestParam("file") MultipartFile file) throws IOException {
        wish.setImage(file.getBytes());
        wishService.createWish(wish, wishlistId);

        return MessageFormat.format("redirect:/wishlist/{0}", wishlistId);
    }

    @PatchMapping("/{wishlistId}/{wishId}")
    public String update(@PathVariable Long wishlistId, @PathVariable Long wishId, Wish wish) {
        wishService.updateWish(wish, wishId);
        return MessageFormat.format("redirect:/wishlist/{0}", wishlistId);
    }

    @DeleteMapping("/{wishId}")
    public void delete(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
    }
}

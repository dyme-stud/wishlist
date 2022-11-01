package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("")
    public String get() {
        return "add-wishlist";
    }

    @GetMapping("/addWish")
    public String getAddWish() {
        return "add-wish";
    }

    @GetMapping("/{userId}")
    public List<Wishlist> get(@PathVariable Long userId) {
        return wishlistService.getWishlists(userId);
    }

    @PostMapping("/{userId}")
    public String create(@PathVariable Long userId, Wishlist wishlist, Model model) {
        wishlistService.createWishlist(wishlist, userId);
        model.addAttribute("name", wishlist.getName());
        model.addAttribute("wishes", wishlist.getWishes());
        return "wish-list";
    }

    @DeleteMapping("/{wishlistId}")
    public void delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

}

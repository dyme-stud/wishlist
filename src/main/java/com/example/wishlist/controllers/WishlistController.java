package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("")
    public String getMainPage(@CookieValue(value = "user_id") Long userId, Model model) {
        model.addAttribute("wishlists", wishlistService.getWishlists(userId));
        return "main-page";
    }

    @GetMapping("/addWishlist")
    public String getAddWishListPage(@CookieValue(value = "user_id") Long userId, Model model) {
        model.addAttribute("wishlists", wishlistService.getWishlists(userId));
        return "add-wishlist";
    }

    @PostMapping("")
    public String createWishList(@CookieValue(value = "user_id") Long userId, Wishlist wishlist) {
        wishlistService.createWishlist(wishlist, userId);
        return MessageFormat.format("redirect:/wishlist/{0}", wishlist.getId());
    }

    @GetMapping("/{wishListId}")
    public String getWishList(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, Model model) {
        var wishlists = wishlistService.getWishlists(userId);
        model.addAttribute("wishlists", wishlists);
        var wishList = wishlists.stream()
                .filter(wl -> wl.getId().equals(wishListId))
                .findFirst()
                .orElse(null);
        model.addAttribute("name", wishList != null ? wishList.getName() : null);
        model.addAttribute("wishes", wishList != null ? wishList.getWishes() : null);
        model.addAttribute("wishListId", wishListId);
        return "wish-list";
    }

    @GetMapping("/{wishListId}/addWish")
    public String getAddWishPage(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, Model model) {
        var wishlists = wishlistService.getWishlists(userId);
        model.addAttribute("wishlists", wishlists);
        var wishList = wishlists.stream()
                .filter(wl -> wl.getId().equals(wishListId))
                .findFirst()
                .orElse(null);
        model.addAttribute("name", wishList != null ? wishList.getName() : null);
        model.addAttribute("wishes", wishList != null ? wishList.getWishes() : null);
        model.addAttribute("wishListId", wishListId);
        return "add-wish";
    }

    @DeleteMapping("/{wishlistId}")
    public void delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

}

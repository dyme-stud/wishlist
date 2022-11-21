package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.HashMap;

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
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, null);
        modelAttributes.forEach(model::addAttribute);
        return "wish-list";
    }

    @GetMapping("/{wishListId}/addWish")
    public String getAddWishPage(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, null);
        modelAttributes.forEach(model::addAttribute);
        return "add-wish";
    }

    @GetMapping("/{wishListId}/editWish/{wishId}")
    public String getEditWishPage(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, @PathVariable Long wishId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, wishId);
        modelAttributes.forEach(model::addAttribute);
        return "edit-wish";
    }

    @DeleteMapping("/{wishlistId}")
    public void delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }
    

    private HashMap<String, Object> getWishlistModelAttributes(Long userId, Long wishListId, Long wishId){
        var attributes = new HashMap<String, Object>();
        
        var wishlists = wishlistService.getWishlists(userId);
        attributes.put("wishlists", wishlists);
        
        var wishList = wishlists.stream()
                .filter(wl -> wl.getId().equals(wishListId))
                .findFirst()
                .orElse(null);
        var wishes = wishList != null ? wishList.getWishes() : null;

        attributes.put("wishes", wishes);
        attributes.put("name", wishList != null ? wishList.getName() : null);
        attributes.put("wishListId", wishListId);

        if (wishId != null){
            var wish = wishes != null ?
                    wishes.stream()
                            .filter(w -> w.getId().equals(wishId))
                            .findFirst()
                            .orElse(null) :
                    null;
            attributes.put("wish", wish);
        }

        return attributes;
    }
}

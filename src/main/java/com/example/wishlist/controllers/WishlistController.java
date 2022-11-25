package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.user.UserService;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;

@Controller
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;

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
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, null, false);
        modelAttributes.forEach(model::addAttribute);
        return "wish-list";
    }

    @GetMapping("/{wishListId}/addWish")
    public String getAddWishPage(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, null, false);
        modelAttributes.forEach(model::addAttribute);
        return "add-wish";
    }

    @GetMapping("/{wishListId}/editWish/{wishId}")
    public String getEditWishPage(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, @PathVariable Long wishId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, wishId, false);
        modelAttributes.forEach(model::addAttribute);
        return "edit-wish";
    }

    @GetMapping("/{wishlistId}/share")
    @ResponseBody
    public String getWishlistLink(@PathVariable Long wishlistId) {
        return String.format("http://localhost:8080/wishlist/present/%d", wishlistId);
    }

    @GetMapping("/present/{wishlistId}")
    public String getPresentWishlistPage(@PathVariable Long wishlistId, Model model) {
        var wishlist = wishlistService.getWishlist(wishlistId);
        var wishes = wishlist != null ? wishlist.getWishes() : null;

        model.addAttribute("wishes", wishes);
        model.addAttribute("name", wishlist != null ? wishlist.getName() : null);
        model.addAttribute("wishListId", wishlist != null ? wishlist.getId() : null);

        return "other-user-wishlist";
    }

    @PostMapping("/present/{wishlistId}")
    public String addPresentWishlist(@PathVariable Long wishlistId, @CookieValue(value = "user_id") Long userId, Model model) {
        var wishlist = wishlistService.getWishlist(wishlistId);
        userService.addPresentWishlist(wishlist, userId);
        return "redirect:/wishlist/willGive";
    }

    @GetMapping("/willGive")
    public String getPresentMainPage(@CookieValue(value = "user_id") Long userId, Model model) {
        var wishlists = wishlistService.getWishListsToPresent(userId);
        model.addAttribute("wishlists", wishlists);
        return "will-give";
    }

    @GetMapping("/willGive/{wishListId}")
    public String getPresentWishList(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, null, true);
        modelAttributes.forEach(model::addAttribute);
        return "will-give-wishlist";
    }

    @GetMapping("/willGive/{wishListId}/{wishId}")
    public String getWillGiveWishDescription(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishListId, @PathVariable Long wishId, Model model) {
        var modelAttributes = getWishlistModelAttributes(userId, wishListId, wishId, true);
        modelAttributes.forEach(model::addAttribute);
        return "will-give-wish-description";
    }

    @DeleteMapping("/{wishlistId}")
    public void delete(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }


    private HashMap<String, Object> getWishlistModelAttributes(Long userId, Long wishListId, Long wishId, boolean isPresent){
        var attributes = new HashMap<String, Object>();

        var wishlists = isPresent ? wishlistService.getWishListsToPresent(userId) : wishlistService.getWishlists(userId);
        attributes.put("wishlists", wishlists);

        var wishList = wishlists.stream()
                .filter(wl -> wl.getId().equals(wishListId))
                .findFirst()
                .orElse(null);
        var wishes = wishList != null ? wishList.getWishes() : null;

        attributes.put("wishes", wishes);
        attributes.put("name", wishList != null ? wishList.getName() : null);
        attributes.put("wishListId", wishListId);
        if (wishes != null) {
            for (var wish : wishes) {
                if (wish.getImage() != null) {
                    byte[] encodeBase64 = Base64.encode(wish.getImage());
                    String base64Encoded = null;
                    try {
                        base64Encoded = new String(encodeBase64, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    wish.setBase64Image(String.format("data:image/gif;base64,%s", base64Encoded));
                }
            }
        }

        if (wishId != null) {
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

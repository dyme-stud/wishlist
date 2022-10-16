package com.example.wishlist.controllers;

import com.example.wishlist.models.Wishlist;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/create/{userId}")
    public int create(@PathVariable Long userId, @RequestBody Wishlist wishlist)
    {
        wishlistService.createWishlist(wishlist, userId);
        return Response.SC_OK;
    }

    @DeleteMapping("/delete/{wishlistId}")
    public int delete(@PathVariable Long wishlistId)
    {
        wishlistService.deleteWishlist(wishlistId);
        return Response.SC_OK;
    }

}

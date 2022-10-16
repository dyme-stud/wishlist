package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.services.wish.WishService;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/create/{wishlistId}")
    public int create(@PathVariable Long wishlistId, @RequestBody Wish wish)
    {
        wishService.createWish(wish, wishlistId);
        return Response.SC_OK;

    }

    @PostMapping("/update/{wishId}")
    public int update(@PathVariable Long wishId, @RequestBody Wish wish)
    {
        wishService.updateWish(wish, wishId);
        return Response.SC_OK;
    }

    @DeleteMapping("/delete/{wishId}")
    public int delete(@PathVariable Long wishId)
    {
        wishService.deleteWish(wishId);
        return Response.SC_OK;
    }
}

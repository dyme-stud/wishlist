package com.example.wishlist.services.wishlist;

import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist createWishlist(Wishlist wishlist, Long userId);

    void deleteWishlist(Long wishlistId);

    void addWish(Wish wish, Long wishlistId);

    Wishlist getWishlist(Long id);

    List<Wishlist> getWishlists(Long userId);

    List<Wishlist> getWishListsToPresent(Long userId);
}

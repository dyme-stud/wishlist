package com.example.wishlist.services.wishlist;

import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(Wishlist wishlist, Long userId);
    void deleteWishlist(Long wishlistId);

    void addWish (Wish wish, Long wishlistId);
}

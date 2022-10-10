package com.example.wishlist.services.wish;

import com.example.wishlist.models.Wish;

public interface WishService {
    Wish createWish(Wish wish, Long wishlistId);
    void updateWish(Wish wish, Long wishId);
    void deleteWish(Long wishId);
}

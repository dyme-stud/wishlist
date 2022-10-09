package com.example.wishlist.services.wishlist;

import com.example.wishlist.models.Wishlist;

public interface IWishlistService {
    Wishlist get(Long id);
    Wishlist create();
}

package com.example.wishlist.services.user;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;

import java.util.List;

public interface UserService {
    User updateInformation();
    Wishlist addWishlist(Wishlist wishlist);
    List<Wishlist> getAllWishlists(Long userId);
}

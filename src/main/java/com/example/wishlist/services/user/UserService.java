package com.example.wishlist.services.user;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;

import java.util.List;

public interface UserService {
    User updateInformation();

    void addWishlist(Wishlist wishlist, Long userId);
    void addPresentWishlist(Wishlist wishlist, Long userId);

    User get(Long userId);

    User get(String email);

    User create(User user);

    void delete(Long userId);
}

package com.example.wishlist.services.wishlist;

import com.example.wishlist.exceptions.WishlistNotFoundException;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(Wishlist wishlist, Long userId) {
        return null;
    }

    @Override
    public void deleteWishlist(Long wishlistId) {

    }
}

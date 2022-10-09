package com.example.wishlist.services.wishlist;

import com.example.wishlist.exceptions.WishlistNotFoundException;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.WishlistRepository;
import com.example.wishlist.services.wishlist.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WishlistService implements IWishlistService {

    private final WishlistRepository wishlistRepository;
    @Override
    public Wishlist get(Long id) {
        return wishlistRepository.findById(id).orElseThrow(WishlistNotFoundException::new);
    }

    @Override
    public Wishlist create() {
        return null;
    }
}

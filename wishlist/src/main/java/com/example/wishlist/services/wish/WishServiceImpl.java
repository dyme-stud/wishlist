package com.example.wishlist.services.wish;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repositories.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

    @Override
    public Wish createWish(Wish wish, Long wishlistId) {
        return null;
    }

    @Override
    public void updateWish(Wish wish, Long wishId) {

    }

    @Override
    public void deleteWish(Long wishId) {

    }
}

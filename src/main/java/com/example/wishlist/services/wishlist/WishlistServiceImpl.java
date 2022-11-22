package com.example.wishlist.services.wishlist;

import com.example.wishlist.exceptions.WishlistNotFoundException;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.WishlistRepository;
import com.example.wishlist.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    private final UserService userService;

    @Override
    @Transactional
    public Wishlist createWishlist(Wishlist wishlist, Long userId) {
        var savedWishlist = wishlistRepository.save(wishlist);
        userService.addWishlist(savedWishlist, userId);
        return savedWishlist;
    }

    @Override
    public void deleteWishlist(Long wishlistId) {
        try {
            wishlistRepository.deleteById(wishlistId);
        } catch (Exception ignored) {
        }
    }

    @Override
    @Transactional
    public void addWish(Wish wish, Long wishlistId) {
        var wishlist = wishlistRepository.findById(wishlistId).orElseThrow(WishlistNotFoundException::new);
        wishlist.getWishes().add(wish);
        wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlist(Long id) {
        return wishlistRepository.findById(id).orElseThrow(WishlistNotFoundException::new);
    }

    @Override
    public List<Wishlist> getWishlists(Long userId) {
        return userService.get(userId).getWishlists();
    }
}

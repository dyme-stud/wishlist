package com.example.wishlist.services.wishlist;

import com.example.wishlist.exceptions.WishlistNotFoundException;
import com.example.wishlist.models.Wish;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import com.example.wishlist.repositories.WishlistRepository;
import com.example.wishlist.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    private final UserService userService;

    @Override
    public Wishlist createWishlist(Wishlist wishlist, Long userId)
    {
        var savedWishlist =  wishlistRepository.save(wishlist);
        userService.addWishlist(savedWishlist, userId);
        return savedWishlist;
    }

    @Override
    public void deleteWishlist(Long wishlistId)
    {
        wishlistRepository.deleteById(wishlistId);
    }

    @Override
    public void addWish(Wish wish, Long wishlistId)
    {
       var wishlist =  wishlistRepository.getReferenceById(wishlistId);
       var wishes = wishlist.getWishes();
       wishes.add(wish);
       wishlist.setWishes(wishes);
       wishlistRepository.save(wishlist);

    }
}

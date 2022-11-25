package com.example.wishlist.services.wish;

import com.example.wishlist.enums.WishPriority;
import com.example.wishlist.enums.WishStatus;
import com.example.wishlist.exceptions.WishNotFoundException;
import com.example.wishlist.models.Wish;
import com.example.wishlist.repositories.WishRepository;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final WishlistService wishlistService;

    @Override
    @Transactional
    public Wish createWish(Wish wish, Long wishlistId) {
        var savedWish = wishRepository.save(wish);
        wishlistService.addWish(wish, wishlistId);
        return savedWish;
    }

    @Override
    @Transactional
    public Wish updateWish(Wish wish, Long wishId) {
        var wishToUpdate = wishRepository.findById(wishId).orElseThrow(WishNotFoundException::new);
        changeWishFields(wishToUpdate, wish);
        return wishRepository.save(wishToUpdate);
    }

    @Override
    public Wish updateWishStatus(Wish wish, Long wishId) {
        var wishToUpdate = wishRepository.findById(wishId).orElseThrow(WishNotFoundException::new);
        if (Arrays.stream(WishStatus.values()).noneMatch((t) -> t.equals(wish.getStatus())))
            return wish;
        wishToUpdate.setStatus(wish.getStatus());
        return wishRepository.save(wishToUpdate);
    }

    @Override
    public void deleteWish(Long wishId) {
        try {
            wishRepository.deleteById(wishId);
        } catch (Exception ignored) {
        }
    }

    private void changeWishFields(Wish wishToUpdate, Wish newWish) {
        wishToUpdate.setName(newWish.getName());
        wishToUpdate.setDescription(newWish.getDescription());
        wishToUpdate.setLink(newWish.getLink());
        wishToUpdate.setPrice(newWish.getPrice());
        if (Arrays.stream(WishPriority.values()).anyMatch((t) -> t.equals(newWish.getPriority())))
            wishToUpdate.setPriority(newWish.getPriority());
        if (Arrays.stream(WishStatus.values()).anyMatch((t) -> t.equals(newWish.getStatus())))
            wishToUpdate.setStatus(newWish.getStatus());
    }
}

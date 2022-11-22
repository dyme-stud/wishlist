package com.example.wishlist.services.wish;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repositories.WishRepository;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var wishToUpdate = wishRepository.getReferenceById(wishId);
        changeWishFields(wishToUpdate, wish);
        return wishRepository.save(wishToUpdate);
    }

    @Override
    public Wish updateWishStatus(Wish wish, Long wishId) {
        var wishToUpdate = wishRepository.getReferenceById(wishId);
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
        wishToUpdate.setPriority(newWish.getPriority());
        wishToUpdate.setStatus(newWish.getStatus());
    }
}

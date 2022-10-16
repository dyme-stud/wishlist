package com.example.wishlist.services.wish;

import com.example.wishlist.models.Wish;
import com.example.wishlist.repositories.WishRepository;
import com.example.wishlist.services.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
    private final WishlistService wishlistService;

    @Override
    public Wish createWish(Wish wish, Long wishlistId)
    {
       var savedWish =  wishRepository.save(wish);
       wishlistService.addWish(wish, wishlistId);
       return savedWish;

    }

    @Override
    public void updateWish(Wish wish, Long wishId)
    {
        var wishFind = wishRepository.getReferenceById(wishId);
        wishFind.setName(wish.getName());
        wishRepository.save(wishFind);

    }

    @Override
    public void deleteWish(Long wishId)
    {
        wishRepository.deleteById(wishId);

    }
}

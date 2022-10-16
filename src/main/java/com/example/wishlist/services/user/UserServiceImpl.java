package com.example.wishlist.services.user;

import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User updateInformation()
    {

        return null;
    }

    @Override
    public void addWishlist(Wishlist wishlist, Long userId)
    {
        var user =  userRepository.getReferenceById(userId);
        var wishlists = user.getWishlists();
        wishlists.add(wishlist);
        user.setWishlists(wishlists);
        userRepository.save(user);
    }


    @Override
    public List<Wishlist> getAllWishlists(Long userId)
    {
        var user = userRepository.getReferenceById(userId);
        return user.getWishlists();
    }
}

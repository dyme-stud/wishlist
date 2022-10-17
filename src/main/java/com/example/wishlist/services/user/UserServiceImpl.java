package com.example.wishlist.services.user;

import com.example.wishlist.exceptions.UserNotFoundException;
import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User updateInformation() {
        return null;
    }

    @Override
    @Transactional
    public void addWishlist(Wishlist wishlist, Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.getWishlists().add(wishlist);
        userRepository.save(user);
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}

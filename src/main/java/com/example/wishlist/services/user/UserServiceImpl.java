package com.example.wishlist.services.user;

import com.example.wishlist.enums.Role;
import com.example.wishlist.exceptions.UserExistException;
import com.example.wishlist.exceptions.UserNotFoundException;
import com.example.wishlist.models.User;
import com.example.wishlist.models.Wishlist;
import com.example.wishlist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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
        try {
            userRepository.save(user);
        } catch (Exception ignored) {

        }
    }

    @Override
    @Transactional
    public void addPresentWishlist(Wishlist wishlist, Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getWishlists().stream().anyMatch(x -> x.getId().equals(wishlist.getId())))
            return;
        user.getWishlistsToPresent().add(wishlist);
        try {
            userRepository.save(user);
        } catch (Exception ignored) {

        }
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) throw new UserExistException();
        user.setActive(true);
        user.getRoles().add(Role.User);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception ignored) {

        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User myUser = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(myUser.getEmail(), myUser.getPassword(), mapRolesToAthorities(myUser.getRoles()));
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }
}

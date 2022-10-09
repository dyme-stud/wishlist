package com.example.wishlist.repositories;

import com.example.wishlist.models.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}

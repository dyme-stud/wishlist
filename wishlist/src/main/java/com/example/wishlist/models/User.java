package com.example.wishlist.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "wishlists")
    private List<Wishlist> wishlists;
}

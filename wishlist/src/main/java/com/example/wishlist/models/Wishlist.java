package com.example.wishlist.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String name;

    @OneToMany
    @Column
    private List<Wish> wishes;
}

package com.example.wishlist.models;

import com.example.wishlist.enums.WishPriority;
import com.example.wishlist.enums.WishStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wishes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String link;

    @Column
    private Long price;

    @Column
    private WishStatus status;

    @Column
    private WishPriority priority;

    @Column(length = 1000)
    private String description;

    @Column
    private byte[] image;

    private String base64Image;
}

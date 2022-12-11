package com.example.wishlist.controllers;

import com.example.wishlist.models.Wish;
import com.example.wishlist.services.wish.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Objects;

@Controller
@RequestMapping(path = "/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/{wishlistId}")
    public String create(@CookieValue(value = "user_id") Long userId, @PathVariable Long wishlistId, Wish wish, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == "")
        {
            BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/img/gift-default.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            byte [] data = bos.toByteArray();
            wish.setImage(data);
        }
        else
        {
            wish.setImage(file.getBytes());
        }
        wishService.createWish(wish, wishlistId);
        return MessageFormat.format("redirect:/wishlist/{0}", wishlistId);
    }

    @PatchMapping("/{wishlistId}/{wishId}")
    public String update(@PathVariable Long wishlistId, @PathVariable Long wishId, Wish wish, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == "")
        {
            BufferedImage bImage = ImageIO.read(new File("src/main/resources/static/img/gift-default.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            byte [] data = bos.toByteArray();
            wish.setImage(data);
        }
        if (!Objects.equals(file.getOriginalFilename(), ""))
        {
            wish.setImage(file.getBytes());
        }
        wishService.updateWish(wish, wishId);
        return MessageFormat.format("redirect:/wishlist/{0}", wishlistId);
    }

    @PostMapping("/delete/{wishId}")
    public void delete(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
    }
}

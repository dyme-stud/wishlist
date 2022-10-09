package com.example.wishlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Wishlist not found")
public class WishlistNotFoundException extends RuntimeException{
}

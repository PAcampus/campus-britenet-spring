package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCart(@PathVariable int id) {
        return this.cartService.getCart(id);
    }

    @GetMapping
    public List<Cart> getCarts() {
        return this.cartService.getCarts();
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart) {
        this.cartService.insertCart(cart);
    }

    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        this.cartService.updateCart(cart);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable int id) {
        this.cartService.deleteCart(id);
    }
}

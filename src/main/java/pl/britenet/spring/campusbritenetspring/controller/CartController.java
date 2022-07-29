package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.services.CartService;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartController(CartService cartService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCart(@PathVariable int id) {
        return this.cartService.getCart(id);
    }

    @GetMapping
    public List<Cart> getCarts() {
        return this.cartService.getCarts();
    }

    @GetMapping("/cartId")
    public int getCartId() {
        List<Cart> carts = this.cartService.getCarts();
        return carts.get(carts.size() - 1).getId();
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart, @RequestHeader("Authorization") String userToken) {
//        System.out.println("Token: " + userToken);
        int user_id = authenticationService.getUserId(userToken);
//        System.out.println("Id Usera: " + user_id);
        cart.setUserId(user_id);
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

package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.CartProduct;
import pl.britenet.campus.services.CartProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cartproduct")
public class CartProductController {
    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping("/{id}")
    public Optional<CartProduct> getCartProduct(@PathVariable int id) {
        return this.cartProductService.getCartProduct(id);
    }

    @GetMapping
    public List<CartProduct> getCartProducts() {
        return this.cartProductService.getCartProducts();
    }

    @PostMapping
    public void createCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.insertCartProduct(cartProduct);
    }

    @PutMapping
    public void updateCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.updateCartProduct(cartProduct);
    }

    @GetMapping("/delete/{id}")
    public void deleteCartProduct(@PathVariable int id) {
        this.cartProductService.deleteCartProduct(id);
    }
}

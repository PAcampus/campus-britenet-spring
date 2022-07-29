package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.CartProduct;
import pl.britenet.campus.services.CartProductService;
import pl.britenet.campus.services.CartService;
import pl.britenet.spring.campusbritenetspring.model.CartWithProducts;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartWithProducts")
public class CartWithProductsController {
    private final CartService cartService;
    private final CartProductService cartProductService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartWithProductsController(CartService cartService, CartProductService cartProductService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.cartProductService = cartProductService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/buy")
    public void buyCart(@RequestBody CartWithProducts cartWithProducts, @RequestHeader("Authorization") String user_token) {
        System.out.println("Token: " + user_token);
        int user_id =authenticationService.getUserId(user_token);
        System.out.println("Id Usera: " + user_id);
        Cart cart = cartWithProducts.getCart();
        cart.setUserId(user_id);
        List<CartProduct> cartProducts = cartWithProducts.getCartProducts();
        System.out.println(cart.toString());
        cartProducts.forEach(cartProduct -> System.out.println(cartProduct.toString()));
//        System.out.println("PRODUKTY:");
//        System.out.println(cartWithProducts.getCartProducts().size());
    }

}

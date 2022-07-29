package pl.britenet.spring.campusbritenetspring.model;

import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.CartProduct;

import java.util.List;

public class CartWithProducts {
    private Cart cart;
    private List<CartProduct> cartProducts;

    public CartWithProducts(Cart cart, List<CartProduct> cartProducts) {
        this.cart = cart;
        this.cartProducts = cartProducts;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

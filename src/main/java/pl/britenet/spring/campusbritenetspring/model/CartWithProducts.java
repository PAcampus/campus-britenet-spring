package pl.britenet.spring.campusbritenetspring.model;

import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.OrderProduct;

import java.util.List;

public class CartWithProducts {
    private Cart cart;
    private List<OrderProduct> orderProducts;

    public CartWithProducts(Cart cart, List<OrderProduct> orderProducts) {
        this.cart = cart;
        this.orderProducts = orderProducts;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

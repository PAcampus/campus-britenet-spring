package pl.britenet.spring.campusbritenetspring.model;

import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.OrderProduct;

import java.util.List;

public class CartWithProducts {
    private Cart cart;
    private List<OrderProduct> orderProducts;
    private String address;

    public CartWithProducts(Cart cart, List<OrderProduct> orderProducts, String address) {
        this.cart = cart;
        this.orderProducts = orderProducts;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

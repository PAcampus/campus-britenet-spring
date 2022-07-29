package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Cart;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.services.CartService;
import pl.britenet.campus.services.OrderProductService;
import pl.britenet.campus.services.OrderService;
import pl.britenet.spring.campusbritenetspring.model.CartWithProducts;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartWithProducts")
public class CartWithProductsController {
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartWithProductsController(CartService cartService, OrderProductService orderProductService,
                                      OrderService orderService ,AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.orderProductService = orderProductService;
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public void buyCart(@RequestBody CartWithProducts cartWithProducts, @RequestHeader("Authorization") String user_token) {
        System.out.println("Token: " + user_token);
        int user_id =authenticationService.getUserId(user_token);
        System.out.println("Id Usera: " + user_id);
        Cart cart = cartWithProducts.getCart();
        cart.setUserId(user_id);
        List<OrderProduct> orderProducts = cartWithProducts.getOrderProducts();

        System.out.println(cart.toString());
        System.out.println("PRODUKTY:");
        orderProducts.forEach(orderProduct -> System.out.println(orderProduct.getId() + " " +
                orderProduct.getOrderId()+ " " +  orderProduct.getProductId()));
    }

    public int getNewOrderId() {
        List<Order> orderList = this.orderService.getOrders();
        return orderList.get(orderList.size() - 1).getId();
    }

    public int getNewCartId() {
        List<Cart> cartList = this.cartService.getCarts();
        return cartList.get(cartList.size() - 1).getId();
    }
}

package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.models.User;
import pl.britenet.campus.services.CartService;
import pl.britenet.campus.services.OrderProductService;
import pl.britenet.campus.services.OrderService;
import pl.britenet.campus.services.UserService;
import pl.britenet.spring.campusbritenetspring.model.CartWithProducts;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartWithProducts")
public class CartWithProductsController {
    private final CartService cartService;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderProductService orderProductService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartWithProductsController(CartService cartService, OrderProductService orderProductService,
                                      OrderService orderService , UserService userService, AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.orderProductService = orderProductService;
        this.orderService = orderService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping
    public void buyCart(@RequestBody CartWithProducts cartWithProducts, @RequestHeader("Authorization") String user_token) {
        int userId =authenticationService.getUserId(user_token);
        var cart = cartWithProducts.getCart();
        cart.setUserId(userId);

        this.cartService.insertCart(cart);
        int cartId = this.cartService.getNewCartId();
        var order = new Order(-1, cartId, userId, new Date());
        String address = cartWithProducts.getAddress();
        if(Objects.equals(address, "")) {
            User user = this.userService.getUser(userId).get();
            order.setAddress(user.getAddress());
        }
        else {
            order.setAddress(address);
        }
        this.orderService.insertOrder(order);
        int orderId = this.orderService.getNewOrderId();
        this.orderProductService.insertOrderProducts(cartWithProducts.getOrderProducts(), orderId);
    }
}

package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.services.OrderProductService;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/orderproduct")
public class OrderProductController {
    private final OrderProductService orderProductService;
    private final AuthenticationService authenticationService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService, AuthenticationService authenticationService) {
        this.orderProductService = orderProductService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/{id}")
    public Optional<OrderProduct> getOrderProduct(@PathVariable int id) {
        return this.orderProductService.getOrderProduct(id);
    }

    @GetMapping("/user")
    public List<OrderProduct> getOrderProductsOfUser(@RequestHeader("Authorization") String userToken) {
        int userId = authenticationService.getUserId(userToken);
        return this.orderProductService.getOrderProductsOfUser(userId);
    }

    @GetMapping
    public List<OrderProduct> getOrderProducts() {
        return this.orderProductService.getOrderProducts();
    }

    @PostMapping
    public void createOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.insertOrderProduct(orderProduct);
    }

    @PostMapping("/all")
    public void createOrderProducts(@RequestBody List<OrderProduct> orderProductList) {
        for (OrderProduct orderProduct : orderProductList) {
            this.orderProductService.insertOrderProduct(orderProduct);
        }
    }

    @PutMapping
    public void updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.updateOrderProduct(orderProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderProduct(@PathVariable int id) {
        this.orderProductService.deleteOrderProduct(id);
    }
}

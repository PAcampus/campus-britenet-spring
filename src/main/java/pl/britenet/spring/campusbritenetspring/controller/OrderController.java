package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.services.OrderService;
import pl.britenet.spring.campusbritenetspring.service.AuthenticationService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final AuthenticationService authenticationService;

    @Autowired
    public OrderController(OrderService orderService, AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable int id) {
        return this.orderService.getOrder(id);
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @GetMapping("/orderId")
    public int GetOrderId() {
        List<Order> orderList = this.orderService.getOrders();
        return orderList.get(orderList.size() - 1).getId();
    }

    @PostMapping
    public void createOrder(@RequestBody Order order, @RequestHeader("Authorization") String userToken) {
        int user_id = authenticationService.getUserId(userToken);
        order.setUserId(user_id);
        this.orderService.insertOrder(order);
    }

    @PutMapping
    public void updateOrder(@RequestBody Order order) {
        this.orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable int id) {
        this.orderService.deleteOrder(id);
    }
}

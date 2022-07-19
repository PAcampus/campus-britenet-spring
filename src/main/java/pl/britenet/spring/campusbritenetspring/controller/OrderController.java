package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Order;
import pl.britenet.campus.models.Product;
import pl.britenet.campus.services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable int id) {
        return this.orderService.getOrder(id);
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
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

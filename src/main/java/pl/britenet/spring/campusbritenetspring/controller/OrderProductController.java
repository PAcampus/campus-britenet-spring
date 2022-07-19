package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.OrderProduct;
import pl.britenet.campus.services.OrderProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orderproduct")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("/{id}")
    public Optional<OrderProduct> getOrderProduct(@PathVariable int id) {
        return this.orderProductService.getOrderProduct(id);
    }

    @GetMapping
    public List<OrderProduct> getOrderProducts() {
        return this.orderProductService.getOrderProducts();
    }

    @PostMapping
    public void createOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.insertOrderProduct(orderProduct);
    }

    @PutMapping
    public void updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.updateOrderProduct(orderProduct);
    }

    @GetMapping("/delete/{id}")
    public void deleteOrderProduct(@PathVariable int id) {
        this.orderProductService.deleteOrderProduct(id);
    }
}

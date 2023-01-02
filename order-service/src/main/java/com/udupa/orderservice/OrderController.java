package com.udupa.orderservice;

import common.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("order")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        Integer newOrderId = orderService.createOrder(order);
        if(newOrderId != null) {
            return ResponseEntity.ok("Order created with id " + newOrderId);
        }
        return ResponseEntity.ok("Could not create order");
    }

    @GetMapping("order/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getOrderDetails(id));
    }
}

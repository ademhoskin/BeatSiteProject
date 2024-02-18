package com.bdv.demo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bdv.demo.beats.Beat;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<BeatOrder>> getAllOrders() {
        List<BeatOrder> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<BeatOrder> getOrderById(@PathVariable Long orderId) {
        BeatOrder order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<BeatOrder> createOrder(@RequestBody BeatOrder order) {
        BeatOrder createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Update an existing order status by ID
    @PutMapping("/{orderId}/status")
    public ResponseEntity<BeatOrder> updateOrderStatus(@PathVariable Long orderId, @RequestBody BeatOrder.OrderStatus newStatus) {
        BeatOrder updatedOrder = orderService.updateOrderStatus(orderId, newStatus);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // Delete an order by ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get orders by customer email
    @GetMapping("/customer/{customerEmail}")
    public ResponseEntity<List<BeatOrder>> getOrdersByCustomerEmail(@PathVariable String customerEmail) {
        List<BeatOrder> orders = orderService.getOrdersByCustomerEmail(customerEmail);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Get all beats in an order by order ID
    @GetMapping("/{orderId}/beats")
    public ResponseEntity<List<Beat>> getBeatsInOrder(@PathVariable Long orderId) {
        List<Beat> beats = orderService.getBeatsInOrder(orderId);
        return new ResponseEntity<>(beats, HttpStatus.OK);
    }
}

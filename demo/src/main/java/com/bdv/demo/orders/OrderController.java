package com.bdv.demo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import com.bdv.demo.beats.Beat;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<BeatOrder>> getAllOrders() {
        List<BeatOrder> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<BeatOrder> getOrderById(@PathVariable Long orderId) {
        BeatOrder order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BeatOrder> createOrder(@RequestBody BeatOrder order) {
        BeatOrder createdOrder = orderService.createOrder(order);
        for (Beat beat : order.getBeats()) {
            if (beat.getBeatId() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
        if (createdOrder != null) {
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<BeatOrder> updateOrder(@PathVariable Long orderId, @RequestBody BeatOrder order) {
        BeatOrder updatedOrder = orderService.updateOrder(orderId, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long orderId) {
        boolean deleted = orderService.deleteOrder(orderId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/calculateTotalCost")
    public ResponseEntity<Double> calculateTotalCost(@RequestBody List<Beat> beats) {
        Double totalCost = orderService.calcTotalCost(beats);
        return new ResponseEntity<>(totalCost, HttpStatus.OK);
    }
}
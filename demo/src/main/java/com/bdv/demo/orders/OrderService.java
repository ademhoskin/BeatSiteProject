package com.bdv.demo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.bdv.demo.beats.Beat;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<BeatOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public BeatOrder getOrderById(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        return orderRepository.findById(orderId).orElse(null);
    }

    public BeatOrder createOrder(BeatOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object cannot be null");
        }
        // Business logic to create a order
        return orderRepository.save(order);
    }

    public BeatOrder updateOrder(Long orderId, BeatOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order object cannot be null");
        }
        // Business logic to update a order
        return orderRepository.save(order);
    }

    public boolean deleteOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
        // Business logic to delete a order
        orderRepository.deleteById(orderId);
        return true;
    }

    public Double calcTotalCost(List<Beat> beats) {
        Double totalCost = 0.0;
        for (Beat beat : beats) {
            switch(beat.getLicenseType()) {
                case STANDARD:
                    totalCost += 14.99;
                    break;
                case PREMIUM:
                    totalCost += 39.99;
                    break;
                case TRACKOUT:
                    totalCost += 199.99;
                    break;
            }
        }
        return totalCost;
    }
}
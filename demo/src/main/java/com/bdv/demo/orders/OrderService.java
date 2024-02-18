package com.bdv.demo.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.bdv.demo.beats.Beat;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BeatOrder createOrder(BeatOrder order) {

        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderStatus(BeatOrder.OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public List<BeatOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public BeatOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("BeatOrder not found with ID: " + orderId));
    }

    public List<BeatOrder> getOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    public BeatOrder updateOrderStatus(Long orderId, BeatOrder.OrderStatus newStatus) {
        BeatOrder order = getOrderById(orderId);
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Double calculateOrderTotal(Long orderId) {
        BeatOrder order = getOrderById(orderId);
        return order.getBeats().stream().mapToDouble(Beat::getPrice).sum();
    }


    public List<Beat> getBeatsInOrder(Long orderId) {
        BeatOrder order = getOrderById(orderId);
        return order.getBeats();
    }
}


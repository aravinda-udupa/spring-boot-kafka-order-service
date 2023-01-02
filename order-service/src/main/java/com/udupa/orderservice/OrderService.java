package com.udupa.orderservice;

import common.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    KafkaTemplate<String, Order> kafkaTemplate;

    public Integer createOrder(Order order) {
        if(order != null) {
            order.setStatus("Pending");
            OrderEntity orderEntity = orderRepository.save(Utils.getOrderEntityFromOrder(order));
            kafkaTemplate.send("order-create-service-topic",Utils.getOrderFromOrderEntity(orderEntity));
            return orderEntity.getId();
        }
        return null;
    }

    public Order getOrderDetails(Integer id) {
            if(id != null) {
                Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);
                if(optionalOrderEntity.isPresent()) {
                    return Utils.getOrderFromOrderEntity(orderRepository.findById(id).get());
                }
            }
            return null;
    }


    @KafkaListener(topics = "order-confirm-service-topic", groupId = "order-confirm-group", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void orderConfirmationListener(Order order) {
        if(order != null) {
            orderRepository.save(Utils.getOrderEntityFromOrder(order));
        }
    }

}

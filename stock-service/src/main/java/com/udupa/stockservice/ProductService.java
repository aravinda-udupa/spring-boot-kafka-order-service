package com.udupa.stockservice;

import common.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    ProductRepository productRepository;

    @KafkaListener(topics = "order-create-service-topic", groupId = "order-create-group", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void confirmOrder(Order order) {
        if(order != null) {
            Optional<ProductEntity> productEntityOptional = productRepository.findById(order.getProductId());
            if(productEntityOptional.isPresent()) {
                ProductEntity productEntity = productEntityOptional.get();
                if(order.getQty() <= productEntity.getAvailableStock()) {
                    order.setStatus("Success");
                    order.setUnitPrice(productEntity.getUnitPrice());
                    productEntity.setAvailableStock(productEntity.getAvailableStock() - order.getQty());
                    productRepository.save(productEntity);
                    kafkaTemplate.send("order-confirm-service-topic", order);
                }
            }
        }
    }

    public Integer createProduct(ProductEntity productEntity) {
        if(productEntity != null) {
            return productRepository.save(productEntity).getId();
        }
        return null;
    }
}

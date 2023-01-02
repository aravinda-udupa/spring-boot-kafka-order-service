package com.udupa.orderservice;

import common.Order;

public class Utils {
    public static OrderEntity getOrderEntityFromOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setOrderName(order.getName());
        orderEntity.setProductId(order.getProductId());
        orderEntity.setQty(order.getQty());
        orderEntity.setStatus(order.getStatus());

        return orderEntity;
    }

    public static Order getOrderFromOrderEntity(OrderEntity orderEntity) {
        Order order = new Order(orderEntity.getId(), orderEntity.getOrderName(), orderEntity.getProductId(), orderEntity.getQty(), 0,orderEntity.getStatus());
        return order;
    }
}

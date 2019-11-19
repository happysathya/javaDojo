package com.happysathya.marketplace;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.util.UUID;

public class Order {

    private UUID orderId;
    private String userId;
    private double quantityInKilograms;
    private double price;
    private OrderType orderType;

    private Order(OrderBuilder orderBuilder) {
        this.orderId = UUID.randomUUID();
        this.userId = orderBuilder.userId;
        this.quantityInKilograms = orderBuilder.quantityInKilograms;
        this.price = orderBuilder.price;
        this.orderType = orderBuilder.orderType;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public Double getQuantityInKilograms() {
        return quantityInKilograms;
    }

    public Double getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    enum OrderType {
        BUY, SELL
    }

    public static class OrderBuilder {

        private double quantityInKilograms;
        private double price;
        private OrderType orderType;
        private String userId;

        public OrderBuilder setQuantityInKilograms(double quantityInKilograms) {
            this.quantityInKilograms = quantityInKilograms;
            return this;
        }

        public OrderBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public OrderBuilder setOrderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        public OrderBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Order build() {
            Order order = new Order(this);
            Validation<Seq<String>, Order> validation = OrderValidator.validateOrder(order);
            if (validation.isInvalid()) {
                throw new IllegalStateException(validation.getError().mkString(", "));
            }
            return order;
        }
    }
}

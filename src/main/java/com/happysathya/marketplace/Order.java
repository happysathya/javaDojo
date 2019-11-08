package com.happysathya.marketplace;

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

    private static void validate(Order order) {
        if (order.userId == null || order.userId.length() == 0)
            throw new IllegalStateException("UserId cannot null or empty");
        if (order.quantityInKilograms <= 0)
            throw new IllegalStateException("Quantity cannot be less than or equal to zero");
        if (order.price <= 0) throw new IllegalStateException("Price cannot be less than or equal to zero");
        if (order.orderType == null) throw new IllegalStateException("OrderType has to be either BUY or SELL");
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
            validate(order);
            return order;
        }
    }
}

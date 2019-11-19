package com.happysathya.marketplace;

import com.happysathya.marketplace.Order.OrderType;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class OrderValidator {

    public static Validation<Seq<String>, Order> validateOrder(Order order) {
        return Validation.combine(validateUserId(order.getUserId()),
                validateQuantity(order.getQuantityInKilograms()),
                validatePrice(order.getPrice()),
                validateOrderType(order.getOrderType())
        ).ap((userId, kilograms, price, orderType) -> order);
    }

    private static Validation<String, String> validateUserId(String userId) {
        return userId == null || userId.length() == 0
                ? Validation.invalid("UserId cannot be null or empty")
                : Validation.valid(userId);
    }

    private static Validation<String, Double> validateQuantity(Double kilograms) {
        return kilograms <= 0
                ? Validation.invalid("Quantity cannot be less than or equal to zero")
                : Validation.valid(kilograms);
    }

    private static Validation<String, Double> validatePrice(Double price) {
        return price <= 0
                ? Validation.invalid("Price cannot be less than or equal to zero")
                : Validation.valid(price);
    }

    private static Validation<String, OrderType> validateOrderType(OrderType orderType) {
        return orderType == null
                ? Validation.invalid("OrderType has to be either BUY or SELL")
                : Validation.valid(orderType);
    }
}
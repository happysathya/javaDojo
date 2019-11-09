package com.happysathya.marketplace;

import com.happysathya.marketplace.Order.OrderBuilder;
import org.junit.jupiter.api.Test;

import static com.happysathya.marketplace.Order.OrderType.BUY;
import static com.happysathya.marketplace.Order.OrderType.SELL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Test
    public void shouldValidateAndThrowException_ifUserIdIsNotSet() {
        IllegalStateException exception1 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setPrice(23.0)
                        .setQuantityInKilograms(10)
                        .setOrderType(BUY)
                        .build());
        assertEquals("UserId cannot be null or empty", exception1.getMessage());

        IllegalStateException exception2 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("")
                        .setPrice(23.0)
                        .setQuantityInKilograms(10)
                        .setOrderType(BUY)
                        .build());
        assertEquals("UserId cannot be null or empty", exception2.getMessage());
    }

    @Test
    public void shouldValidateAndThrowException_ifQuantityIsLessThanOrEqualToZero() {
        IllegalStateException exception1 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("user1")
                        .setPrice(10)
                        .setQuantityInKilograms(0)
                        .setOrderType(BUY)
                        .build());
        assertEquals("Quantity cannot be less than or equal to zero", exception1.getMessage());

        IllegalStateException exception2 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("user1")
                        .setPrice(10)
                        .setQuantityInKilograms(-1.0)
                        .setOrderType(SELL)
                        .build());
        assertEquals("Quantity cannot be less than or equal to zero", exception2.getMessage());
    }

    @Test
    public void shouldValidateAndThrowException_ifPriceIsLessThanOrEqualToZero() {
        IllegalStateException exception1 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("user1")
                        .setPrice(0)
                        .setQuantityInKilograms(10)
                        .setOrderType(BUY)
                        .build());
        assertEquals("Price cannot be less than or equal to zero", exception1.getMessage());

        IllegalStateException exception2 = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("user1")
                        .setPrice(-1.0)
                        .setQuantityInKilograms(10)
                        .setOrderType(BUY)
                        .build());
        assertEquals("Price cannot be less than or equal to zero", exception2.getMessage());
    }

    @Test
    public void shouldValidateAndThrowException_ifOrderTypeIsNotSet() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                new OrderBuilder()
                        .setUserId("user1")
                        .setPrice(23.0)
                        .setQuantityInKilograms(10)
                        .build());
        assertEquals("OrderType has to be either BUY or SELL", exception.getMessage());
    }

}

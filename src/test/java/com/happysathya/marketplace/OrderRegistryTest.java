package com.happysathya.marketplace;

import com.happysathya.marketplace.Order.OrderBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.happysathya.marketplace.Order.OrderType.BUY;
import static com.happysathya.marketplace.Order.OrderType.SELL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderRegistryTest {

    @Test
    public void shouldReturnEmptySummary_ifThereAreNoOrders() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.getSummary(BUY).isEmpty());
        assertTrue(silverBarOrderRegistry.getSummary(SELL).isEmpty());
    }

    @Test
    public void shouldReturnFalse_ifOrderToBeCancelledIsNotFound() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertFalse(silverBarOrderRegistry.cancelOrder(UUID.randomUUID()));
    }

    @Test
    public void shouldAddOrder_andMustReflectInSummary_basedOnOrderType() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user1")
                .setPrice(306.00)
                .setQuantityInKilograms(3.5)
                .setOrderType(BUY)
                .build()));

        List<String> summary = silverBarOrderRegistry.getSummary(BUY);
        assertEquals(1, summary.size());
        assertEquals("3.50 kg for £306.00", summary.get(0));

        assertTrue(silverBarOrderRegistry.getSummary(SELL).isEmpty());
    }

    @Test
    public void shouldMergeOrders_andMustReflectInSummary_basedOnOrderType() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user1")
                .setPrice(306.00)
                .setQuantityInKilograms(3.5)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user2")
                .setPrice(306.00)
                .setQuantityInKilograms(2)
                .setOrderType(SELL)
                .build()));

        List<String> summary = silverBarOrderRegistry.getSummary(SELL);
        assertEquals(1, summary.size());
        assertEquals("5.50 kg for £306.00", summary.get(0));

        assertTrue(silverBarOrderRegistry.getSummary(BUY).isEmpty());
    }

    @Test
    public void shouldMergeOrders_andTheOrdersMustBeSortedInAscendingOrderAccordingToPrice_ifOrderTypeIsSELL() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user1")
                .setPrice(306.00)
                .setQuantityInKilograms(3.5)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user4")
                .setPrice(306.00)
                .setQuantityInKilograms(2.0)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user2")
                .setPrice(310.00)
                .setQuantityInKilograms(1.2)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user3")
                .setPrice(307.00)
                .setQuantityInKilograms(1.5)
                .setOrderType(SELL)
                .build()));

        List<String> summary = silverBarOrderRegistry.getSummary(SELL);
        assertEquals(3, summary.size());
        assertEquals("5.50 kg for £306.00", summary.get(0));
        assertEquals("1.50 kg for £307.00", summary.get(1));
        assertEquals("1.20 kg for £310.00", summary.get(2));

        assertTrue(silverBarOrderRegistry.getSummary(BUY).isEmpty());
    }

    @Test
    public void shouldMergeOrders_andTheOrdersMustBeSortedInDescendingOrderAccordingToPrice_ifOrderTypeIsBUY() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user1")
                .setPrice(306.00)
                .setQuantityInKilograms(3.5)
                .setOrderType(BUY)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user4")
                .setPrice(306.00)
                .setQuantityInKilograms(2.0)
                .setOrderType(BUY)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user2")
                .setPrice(310.00)
                .setQuantityInKilograms(1.2)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user3")
                .setPrice(307.00)
                .setQuantityInKilograms(1.5)
                .setOrderType(BUY)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user5")
                .setPrice(322.00)
                .setQuantityInKilograms(4.0)
                .setOrderType(SELL)
                .build()));

        List<String> buySummary = silverBarOrderRegistry.getSummary(BUY);
        assertEquals(2, buySummary.size());
        assertEquals("1.50 kg for £307.00", buySummary.get(0));
        assertEquals("5.50 kg for £306.00", buySummary.get(1));

        List<String> sellSummary = silverBarOrderRegistry.getSummary(SELL);
        assertEquals(2, sellSummary.size());
        assertEquals("1.20 kg for £310.00", sellSummary.get(0));
        assertEquals("4.00 kg for £322.00", sellSummary.get(1));
    }

    @Test
    public void shouldRemoveTheOrder_ifItWasCancelled_andReflectInSummary() {
        OrderRegistry silverBarOrderRegistry = new OrderRegistry();

        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user1")
                .setPrice(306.00)
                .setQuantityInKilograms(3.5)
                .setOrderType(SELL)
                .build()));
        Order orderToBeCancelled = new OrderBuilder()
                .setUserId("user3")
                .setPrice(307.00)
                .setQuantityInKilograms(1.5)
                .setOrderType(SELL)
                .build();
        assertTrue(silverBarOrderRegistry.registerOrder(orderToBeCancelled));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user4")
                .setPrice(306.00)
                .setQuantityInKilograms(2.0)
                .setOrderType(SELL)
                .build()));
        assertTrue(silverBarOrderRegistry.registerOrder(new OrderBuilder()
                .setUserId("user2")
                .setPrice(310.00)
                .setQuantityInKilograms(1.2)
                .setOrderType(SELL)
                .build()));

        assertTrue(silverBarOrderRegistry.cancelOrder(orderToBeCancelled.getOrderId()));

        List<String> summary = silverBarOrderRegistry.getSummary(SELL);
        assertEquals(2, summary.size());
        assertEquals("5.50 kg for £306.00", summary.get(0));
        assertEquals("1.20 kg for £310.00", summary.get(1));

        assertTrue(silverBarOrderRegistry.getSummary(BUY).isEmpty());
    }

}

package com.happysathya.marketplace;

import com.happysathya.marketplace.Order.OrderType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderRegistry {

    private List<Order> orderList = new ArrayList<>();

    private static Collector<Order, ?, Map<Double, Double>> aggregateByQuantityAndGroupByPrice() {
        return Collectors.groupingBy(Order::getPrice,
                Collectors.summingDouble(Order::getQuantityInKilograms));
    }

    private static Comparator<Order> priceComparator(OrderType orderType) {
        Comparator<Order> orderComparator = Comparator.comparingDouble(Order::getPrice);
        if (orderType == OrderType.SELL)
            return orderComparator.reversed();
        return orderComparator;
    }

    public boolean registerOrder(Order order) {
        return orderList.add(order);
    }

    public boolean cancelOrder(UUID orderId) {
        return orderList.removeIf(order -> order.getOrderId() == orderId);
    }

    public List<String> getSummary(OrderType orderType) {
        Map<Double, Double> orderSummary = orderList.stream()
                .filter(order -> order.getOrderType() == orderType)
                .sorted(priceComparator(orderType))
                .collect(aggregateByQuantityAndGroupByPrice());
        return orderSummary.entrySet().stream()
                .map(entry -> String.format("%.2f kg for £%.2f", entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());
    }
}

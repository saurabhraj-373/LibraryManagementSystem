package com.library.IMS;

import java.util.PriorityQueue;

public class OrderQueue {

    private final PriorityQueue<Order> orders = new PriorityQueue<>(
            (first, second) -> Boolean.compare(second.isExpress(), first.isExpress()));

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order processOrder() {
        return orders.poll();
    }
}
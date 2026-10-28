package com.library.IMS;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Inventory<T extends Item> {

    private final HashMap<String, T> items = new HashMap<>();

    public boolean addItem(T item) {
        if (items.containsKey(item.getId())) {
            return false;
        }
        items.put(item.getId(), item);
        return true;
    }

    public T removeItem(String id) {
        return items.remove(id);
    }

    public T getItem(String id) {
        return items.get(id);
    }

    public Collection<T> getAllItems() {
        return items.values();
    }

    public List<T> filterByPriceRange(double minPrice, double maxPrice) {
        return items.values().stream()
                .filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
                .toList();
    }

    public List<T> filterByAvailability() {
        return items.values().stream()
                .filter(item -> item.getQuantity() > 0)
                .toList();
    }
}
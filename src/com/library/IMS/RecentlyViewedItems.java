package com.library.IMS;

import java.util.LinkedList;
import java.util.List;

public class RecentlyViewedItems {

    private static final int MAX_SIZE = 10;
    private final LinkedList<Item> items = new LinkedList<>();

    public void addRecentlyViewedItem(Item item) {
        items.addLast(item);
        if (items.size() > MAX_SIZE) {
            items.removeFirst();
        }
    }

    public List<Item> getRecentlyViewedItems() {
        return List.copyOf(items);
    }
}
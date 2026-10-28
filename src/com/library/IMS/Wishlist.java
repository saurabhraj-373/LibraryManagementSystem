package com.library.IMS;

import java.util.HashSet;
import java.util.Set;

public class Wishlist {

    private final Set<Item> items = new HashSet<>();

    public void addToWishlist(Item item) {
        items.add(item);
    }

    public void removeFromWishlist(Item item) {
        items.remove(item);
    }

    public Set<Item> getItems() {
        return Set.copyOf(items);
    }
}
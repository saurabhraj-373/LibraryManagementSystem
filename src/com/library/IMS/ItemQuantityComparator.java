package com.library.IMS;

import java.util.Comparator;

public class ItemQuantityComparator implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        return Integer.compare(first.getQuantity(), second.getQuantity());
    }
}
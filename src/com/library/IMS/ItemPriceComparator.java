package com.library.IMS;

import java.util.Comparator;

public class ItemPriceComparator implements Comparator<Item> {

    @Override
    public int compare(Item first, Item second) {
        return Double.compare(first.getPrice(), second.getPrice());
    }
}
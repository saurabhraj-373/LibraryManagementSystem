package com.library.IMS;

import java.util.ArrayList;
import java.util.List;

public class InventoryManagementSystemTest {

    public static void main(String[] args) {
        testItemHierarchyAndSorting();
        testInventory();
        testRecentlyViewedItems();
        testOrderQueue();
        testSortingAndFiltering();
        testWishlist();
        System.out.println("All inventory milestone tests passed.");
    }

    private static void testItemHierarchyAndSorting() {
        List<Item> items = new ArrayList<>(List.of(
                new Electronics("E1", "Tablet", 300.0, 5, 24),
                new Clothing("C1", "Jacket", 80.0, 10, "M"),
                new Book("B1", "Atlas", 20.0, 3, "A. Writer")));

        items.sort(null);
        check(items.get(0).getName().equals("Atlas"), "Items should sort by name.");
        check(items.get(1).getName().equals("Jacket"), "Items should sort by name.");
        check(items.get(2).getName().equals("Tablet"), "Items should sort by name.");
    }

    private static void testInventory() {
        Inventory<Item> inventory = new Inventory<>();
        Item book = new Book("B1", "Atlas", 20.0, 3, "A. Writer");

        check(inventory.addItem(book), "New item should be added.");
        check(inventory.getItem("B1") == book, "Item should be retrievable by id.");
        check(!inventory.addItem(new Book("B1", "Duplicate", 10.0, 1, "B. Writer")),
                "Duplicate ids should not be added.");
        check(inventory.getAllItems().size() == 1, "Duplicate id should not replace the item.");
        check(inventory.removeItem("B1") == book, "Item should be removable by id.");
        check(inventory.getItem("B1") == null, "Removed item should not be retrievable.");
    }

    private static void testRecentlyViewedItems() {
        RecentlyViewedItems recentlyViewed = new RecentlyViewedItems();
        for (int index = 0; index < 12; index++) {
            recentlyViewed.addRecentlyViewedItem(
                    new Item("I" + index, "Item " + index, index, 1));
        }

        List<Item> viewed = recentlyViewed.getRecentlyViewedItems();
        check(viewed.size() == 10, "Recently viewed list should have at most 10 items.");
        check(viewed.get(0).getId().equals("I2"), "Oldest items should be removed first.");
        check(viewed.get(9).getId().equals("I11"), "Newest item should remain last.");
    }

    private static void testOrderQueue() {
        OrderQueue queue = new OrderQueue();
        queue.addOrder(new Order("regular", false));
        queue.addOrder(new Order("express", true));

        check(queue.processOrder().getOrderId().equals("express"),
                "Express orders should be processed first.");
        check(queue.processOrder().getOrderId().equals("regular"),
                "Regular order should be processed after express orders.");
    }

    private static void testSortingAndFiltering() {
        Inventory<Item> inventory = new Inventory<>();
        Item lowStock = new Item("I1", "First", 10.0, 0);
        Item highStock = new Item("I2", "Second", 30.0, 5);
        Item midStock = new Item("I3", "Third", 20.0, 2);
        inventory.addItem(lowStock);
        inventory.addItem(highStock);
        inventory.addItem(midStock);

        List<Item> byPrice = new ArrayList<>(inventory.getAllItems());
        byPrice.sort(new ItemPriceComparator());
        check(byPrice.get(0) == lowStock && byPrice.get(2) == highStock,
                "Items should sort by price.");

        List<Item> byQuantity = new ArrayList<>(inventory.getAllItems());
        byQuantity.sort(new ItemQuantityComparator());
        check(byQuantity.get(0) == lowStock && byQuantity.get(2) == highStock,
                "Items should sort by quantity.");
        check(inventory.filterByPriceRange(15.0, 25.0).equals(List.of(midStock)),
                "Price filter should return items in the inclusive range.");
        check(inventory.filterByAvailability().size() == 2,
                "Availability filter should return items with quantity above zero.");
    }

    private static void testWishlist() {
        Wishlist wishlist = new Wishlist();
        Item first = new Item("I1", "First", 10.0, 1);
        Item duplicate = new Item("I1", "First duplicate", 10.0, 1);

        wishlist.addToWishlist(first);
        wishlist.addToWishlist(duplicate);
        check(wishlist.getItems().size() == 1, "Wishlist should contain unique item ids.");
        wishlist.removeFromWishlist(duplicate);
        check(wishlist.getItems().isEmpty(), "Item should be removable from wishlist.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
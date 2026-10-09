package com.library;

public abstract class User {

    private static int totalUsers = 0;

    // never decremented, so ids stay unique even if a user is removed
    private static int idCounter = 0;

    private String userId;
    private String name;
    private String contactInfo;

    public User() {
        totalUsers++;
        this.userId = generateUniqueId();
    }

    public User(String name, String contactInfo) {
        totalUsers++;
        this.userId = generateUniqueId();
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public User(User other) {
        totalUsers++;
        this.userId = generateUniqueId();
        this.name = other.name;
        this.contactInfo = other.contactInfo;
    }

    public abstract void displayDashboard();

    public abstract boolean canBorrowBooks();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    private static String generateUniqueId() {
        idCounter++;
        return "U" + idCounter;
    }

    public static int getTotalUsers() {
        return totalUsers;
    }

    public String getUserId() {
        return userId;
    }
}

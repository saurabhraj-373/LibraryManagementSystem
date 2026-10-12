package com.library.LMS;

public class Member extends User {

    private static final int MAX_BORROW_LIMIT = 5; // private static final - constant in JAVA

    private int borrowedBooksCount;

    public Member() {
        super();
    }

    public Member(String name, String contactInfo) {
        super(name, contactInfo);
    }

    public Member(Member other) {
        super(other);
        // copy gets its own userId, so it is a different person and owns no loans
        this.borrowedBooksCount = 0;
    }

    @Override
    public void displayDashboard() {
        System.out.println("Member Dashboard");
        System.out.println("Name: " + getName());
        System.out.println("Books Borrowed: " + borrowedBooksCount);
    }

    @Override
    public boolean canBorrowBooks() {
        return borrowedBooksCount < MAX_BORROW_LIMIT;
    }

    public void incrementBorrowCount() {
        borrowedBooksCount++;
    }

    public void decrementBorrowCount() {
        borrowedBooksCount--;
    }
}

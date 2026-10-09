package com.library;

public class UserTest {

    private static class DummyBook implements Lendable {

        private boolean available = true;

        @Override
        public boolean lend(User user) {
            if (available && user.canBorrowBooks()) {
                available = false;
                return true;
            }
            return false;
        }

        @Override
        public void returnItem(User user) {
            available = true;
        }

        @Override
        public boolean isAvailable() {
            return available;
        }
    }

    public static void main(String[] args) {
        Member m1 = new Member("Rehber", "rehber@gmail.com");
        Member m2 = new Member();
        m2.setName("Saurabh");
        m2.setContactInfo("saurabh@gmail.com");
        Member m3 = new Member(m1);

        Librarian l1 = new Librarian("Rohit", "rohit@gmail.com", "EMP-101");
        Librarian l2 = new Librarian("Saurabh", "saurabh@gmail.com", "EMP-102");

        m1.displayDashboard();
        System.out.println("-----");
        m2.displayDashboard();
        System.out.println("-----");
        m3.displayDashboard();
        System.out.println("-----");    
        l1.displayDashboard();
        System.out.println("-----");
        l2.displayDashboard();
        System.out.println("-----");

        System.out.println("Total users: " + User.getTotalUsers());
        System.out.println("m1 id: " + m1.getUserId());
        System.out.println("m2 id: " + m2.getUserId());
        System.out.println("m3 id: " + m3.getUserId());
        System.out.println("l1 id: " + l1.getUserId());
        System.out.println("l2 id: " + l2.getUserId());
        System.out.println("-----");

        Lendable dummy = new DummyBook();
        System.out.println("Available before lend: " + dummy.isAvailable());
        System.out.println("Lend successful: " + dummy.lend(m1));
        System.out.println("Available after lend: " + dummy.isAvailable());
        dummy.returnItem(m1);
        System.out.println("Available after return: " + dummy.isAvailable());
        System.out.println("-----");

        TextBook t1 = new TextBook("978-0131103627", "JAVA Programming Language",
                "Saurabh Raj", "Programming", 2);
        NovelBook n1 = new NovelBook("978-0439708180", "Harry Potter",
                "J.K. Rowling", "Fantasy");

        t1.displayBookDetails();
        System.out.println("-----");
        n1.displayBookDetails();
        System.out.println("-----");
    }
}

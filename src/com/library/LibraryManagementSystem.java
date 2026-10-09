package com.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {

    private List<User> registeredUsers = new ArrayList<>();
    private List<Book> bookInventory = new ArrayList<>();

    public void registerUser(User user) {
        registeredUsers.add(user);
    }

    public void addBook(Book book) {
        bookInventory.add(book);
    }

    public boolean lendBook(Member member, Book book) {
        boolean lent = book.lend(member);
        if (lent) {
            member.incrementBorrowCount();
        }
        return lent;
    }

    public void returnBook(Member member, Book book) {
        book.returnItem(member);
        member.decrementBorrowCount();
    }

    public List<Book> searchBooks(String criteria) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookInventory) {
            if (book.getTitle().contains(criteria) || book.getAuthor().contains(criteria)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooks(String criteria, String type) {
        List<Book> result = new ArrayList<>();
        for (Book book : searchBooks(criteria)) {
            if (type.equals("TextBook") && book instanceof TextBook) {
                result.add(book);
            } else if (type.equals("NovelBook") && book instanceof NovelBook) {
                result.add(book);
            }
        }
        return result;
    }

    public void displayAllBooks() {
        for (Book book : bookInventory) {
            book.displayBookDetails();
        }
    }

    public void displayRegisteredUsers() {
        for (User user : registeredUsers) {
            user.displayDashboard();
        }
    }
}

package com.library.LMS;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();

        TextBook tb1 = new TextBook("111", "Data Structures", "Mark Weiss", "Computer Science", 3);
        TextBook tb2 = new TextBook("222", "Linear Algebra", "Gilbert Strang", "Mathematics", 5);
        NovelBook nb1 = new NovelBook("333", "The Hobbit", "J.R.R. Tolkien", "Fantasy");
        NovelBook nb2 = new NovelBook("444", "Dune", "Frank Herbert", "Sci-Fi");
        NovelBook nb3 = new NovelBook("555", "It", "Stephen King", "Horror");
        NovelBook nb4 = new NovelBook("666", "Carrie", "Stephen King", "Horror");

        lms.addBook(tb1);
        lms.addBook(tb2);
        lms.addBook(nb1);
        lms.addBook(nb2);
        lms.addBook(nb3);
        lms.addBook(nb4);

        Member priya = new Member("Priya Shah", "priya@example.com");
        Member rohan = new Member("Rohan Das", "rohan@example.com");
        Librarian amit = new Librarian("Amit Rao", "amit@example.com", "EMP-200");

        lms.registerUser(priya);
        lms.registerUser(rohan);
        lms.registerUser(amit);

        System.out.println("===== All Books =====");
        lms.displayAllBooks();

        System.out.println("===== Registered Users =====");
        lms.displayRegisteredUsers();

        System.out.println("===== Search =====");
        List<Book> byAuthor = lms.searchBooks("Stephen King");
        System.out.println("searchBooks(\"Stephen King\") -> " + byAuthor.size());

        List<Book> byTitle = lms.searchBooks("Hobbit");
        System.out.println("searchBooks(\"Hobbit\") -> " + byTitle.size());

        List<Book> novels = lms.searchBooks("Stephen King", "NovelBook");
        System.out.println("searchBooks(\"Stephen King\", \"NovelBook\") -> " + novels.size());

        List<Book> texts = lms.searchBooks("Stephen King", "TextBook");
        System.out.println("searchBooks(\"Stephen King\", \"TextBook\") -> " + texts.size());

        System.out.println("===== Lending =====");
        tryLend(lms, priya, tb1);
        tryLend(lms, priya, tb1);
        tryLend(lms, priya, tb2);
        tryLend(lms, priya, nb1);
        tryLend(lms, priya, nb2);
        tryLend(lms, priya, nb3);
        tryLend(lms, priya, nb4);
        priya.displayDashboard();

        System.out.println("===== Returning =====");
        System.out.println("Data Structures available before return: " + tb1.isAvailable());
        lms.returnBook(priya, tb1);
        System.out.println("Data Structures available after return: " + tb1.isAvailable());
        tryLend(lms, rohan, tb1);
        priya.displayDashboard();
    }

    private static void tryLend(LibraryManagementSystem lms, Member member, Book book) {
        if (lms.lendBook(member, book)) {
            System.out.println("Book lent successfully: " + book.getTitle() + " to " + member.getName());
        } else {
            System.out.println("Could not lend: " + book.getTitle() + " to " + member.getName());
        }
    }
}

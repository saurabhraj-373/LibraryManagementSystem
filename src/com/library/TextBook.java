package com.library;

public class TextBook extends Book {

    private String subject;
    private int edition;

    public TextBook(String isbn, String title, String author, String subject, int edition) {
        super(isbn, title, author);
        this.subject = subject;
        this.edition = edition;
    }

    @Override
    public void displayBookDetails() {
        System.out.println("TextBook Details");
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Subject: " + subject);
        System.out.println("Edition: " + edition);
        System.out.println("Available: " + isAvailable());
    }
}

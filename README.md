# Library Management System

A console-based Library Management System written in Java as a practice project for core object-oriented programming concepts.

It models library users (members and librarians), a catalogue of lendable books, and the borrowing/returning workflow — including per-member borrowing limits and availability tracking.

## OOP concepts covered

| Concept | Where |
|---|---|
| Encapsulation | All fields are `private`, exposed through getters/setters |
| Abstraction | `User` and `Book` are abstract base classes |
| Inheritance | `Member`/`Librarian` extend `User`; `TextBook`/`NovelBook` extend `Book` |
| Interfaces | `Lendable` defines the borrowing contract |
| Runtime polymorphism | `displayDashboard()` and `displayBookDetails()` dispatch to the subclass |
| Compile-time polymorphism | Overloaded `searchBooks(criteria)` / `searchBooks(criteria, type)` |
| Constructors | Default, parameterized and copy constructors on `User` and `Book` |
| `static` / `final` | `totalUsers` counter, `generateUniqueId()`, `MAX_BORROW_LIMIT` |
| Collections | `List<Book>` and `List<User>` in `LibraryManagementSystem` |

## Project structure

```
src/com/library/
  User.java                      abstract base for all users
  Member.java                    borrows books, limit of 5
  Librarian.java                 manages the catalogue
  Lendable.java                  lend / returnItem / isAvailable
  Book.java                      abstract, implements Lendable
  TextBook.java                  adds subject and edition
  NovelBook.java                 adds genre
  LibraryManagementSystem.java   collections, search, lending, returns
  Main.java                      demo client
  UserTest.java                  tests for users, lending and books
```

## Features

- Auto-generated unique user IDs and a static count of registered users
- Members limited to 5 concurrent loans; librarians manage the catalogue
- Lending blocked when a book is already out or the borrower is at their limit
- Returning a book makes it available to other members again
- Search by title or author, with an overload that filters by book type

## Running it

Requires a JDK — built and tested with JDK 21, though the code uses no version-specific features.

```bash
javac -d bin src/com/library/*.java

java -cp bin com.library.Main       # demo client
java -cp bin com.library.UserTest   # tests
```

## Sample output

```
===== Lending =====
Book lent successfully: Data Structures to Priya Shah
Could not lend: Data Structures to Priya Shah
Book lent successfully: Linear Algebra to Priya Shah
...
Could not lend: Carrie to Priya Shah
Member Dashboard
Name: Priya Shah
Books Borrowed: 5

===== Returning =====
Data Structures available before return: false
Data Structures available after return: true
Book lent successfully: Data Structures to Rohan Das
```

The two `Could not lend` lines show the rules working: the first is a book already on loan, the second is a member who has hit the borrowing limit.

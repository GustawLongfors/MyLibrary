package com.app.mylibrary.data;

import com.app.mylibrary.domain.Book;

public class BookDB {
    private final String id;
    private final String userId;
    private final String title;
    private final String author;
    private final String publishingHouse;
    private final int yearOfPublishing;
    private final String location;
    private final String language;
    private final String bookcaseLocation;

    public BookDB() {
        this.id = "";
        this.userId = "";
        this.title = "";
        this.author = "";
        this.publishingHouse = "";
        this.yearOfPublishing = 0;
        this.location = "";
        this.language = "";
        this.bookcaseLocation = "";
    }

    public BookDB(String id, String userId, String title, String author, String publishingHouse, int yearOfPublishing, String location, String language, String bookcaseLocation) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.yearOfPublishing = yearOfPublishing;
        this.location = location;
        this.language = language;
        this.bookcaseLocation = bookcaseLocation;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }

    public String getBookcaseLocation() {
        return bookcaseLocation;
    }

    public String getUserId() {
        return userId;
    }


    static BookDB fromBook(Book book) {
        return new BookDB(
                book.getId(),
                book.getUserId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingHouse(),
                book.getYearOfPublishing(),
                book.getLocation(),
                book.getLanguage(),
                book.getBookcaseLocation());
    }

    static BookDB fromBook(String id, Book book) {
        return new BookDB(
                id,
                book.getUserId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingHouse(),
                book.getYearOfPublishing(),
                book.getLocation(),
                book.getLanguage(),
                book.getBookcaseLocation());
    }

    static Book fromBookDB(BookDB book) {
        return new Book(
                book.getId(),
                book.getUserId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishingHouse(),
                book.getYearOfPublishing(),
                book.getLocation(),
                book.getLanguage(),
                book.getBookcaseLocation());
    }


}

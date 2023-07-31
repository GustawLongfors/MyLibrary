package com.app.mylibrary.domain;

public class Book {
    private final String id;

    private final String userId;
    private final String title;
    private final String author;
    private final String publishingHouse;
    private final int yearOfPublishing;
    private final String location;
    private final String language;
    private final String bookcaseLocation;

    public Book(String id, String userId, String title, String author, String publishingHouse, int yearOfPublishing, String location, String language, String bookcaseLocation) {
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

}

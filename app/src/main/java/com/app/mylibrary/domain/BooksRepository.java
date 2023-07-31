package com.app.mylibrary.domain;

import java.util.List;

public interface BooksRepository {

    void getUserBooks(String userId, ResultCallback<List<Book>> callback);

    void getBook(String bookId, ResultCallback<Book> callback);

    void addBook(Book book, ResultCallback<Boolean> callback);

    void editBook(Book book, ResultCallback<Boolean> callback);

    void deleteBook(String bookId, ResultCallback<Boolean> callback);

    void getBooksCount(String userId, ResultCallback<Long> callback);
}

package com.app.mylibrary.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.mylibrary.domain.Book;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

public class BooksRepositoryImpl implements BooksRepository {
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    final DatabaseReference booksTable = database.child("books");

    @Inject
    public BooksRepositoryImpl() {
    }

    @Override
    public void getUserBooks(String userId, ResultCallback<List<Book>> callback) {
        booksTable.orderByChild("userId").equalTo(userId).get().addOnCompleteListener(Executors.newSingleThreadExecutor(), task -> {
            if (task.isSuccessful()) {
                List<Book> items = asStream(task.getResult()
                        .getChildren()).map(v -> v.getValue(BookDB.class))
                        .map(BookDB::fromBookDB).collect(Collectors.toList());
                callback.success(items);
            } else {
                callback.failure(task.getException());
            }
        });
    }

    @Override
    public void getBook(String bookId, ResultCallback<Book> callback) {
        booksTable.child(bookId).get().addOnCompleteListener(Executors.newSingleThreadExecutor(), task -> {
            if (task.isSuccessful()) {
                callback.success(BookDB.fromBookDB(task.getResult().getValue(BookDB.class)));
            } else {
                callback.failure(task.getException());
            }
        });
    }

    @Override
    public void addBook(Book book, ResultCallback<Boolean> callback) {
        DatabaseReference ref = booksTable.push();
        ref.setValue(BookDB.fromBook(ref.getKey(), book)).addOnCompleteListener(Executors.newSingleThreadExecutor(), task -> {
            if (task.isSuccessful()) {
                callback.success(true);
            } else {
                callback.failure(task.getException());
            }
        });
    }

    @Override
    public void editBook(Book book, ResultCallback<Boolean> callback) {
        booksTable.child(book.getId()).setValue(BookDB.fromBook(book)).addOnCompleteListener(Executors.newSingleThreadExecutor(), task -> {
            if (task.isSuccessful()) {
                callback.success(true);
            } else {
                callback.failure(task.getException());
            }
        });
    }

    @Override
    public void deleteBook(String bookId, ResultCallback<Boolean> callback) {
        callback.success(true);
        booksTable.child(bookId).removeValue((error, ref) -> {
            if (error == null) {
                callback.success(true);
            } else {
                callback.failure(error.toException());
            }
        });
    }

    @Override
    public void getBooksCount(String userId, ResultCallback<Long> callback) {
        booksTable.get().addOnCompleteListener(Executors.newSingleThreadExecutor(), task -> {
            if (task.isSuccessful()) {
                callback.success(task.getResult()
                        .getChildrenCount());
            } else {
                callback.failure(task.getException());
            }
        });
    }

    public static <T> Stream<T> asStream(Iterable<T> sourceIterator) {
        return StreamSupport.stream(sourceIterator.spliterator(), true);
    }
}

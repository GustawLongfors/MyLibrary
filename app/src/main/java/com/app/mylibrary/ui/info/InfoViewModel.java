package com.app.mylibrary.ui.info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.mylibrary.domain.Book;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.utils.SingleLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InfoViewModel extends ViewModel {

    SingleLiveData<String> message = new SingleLiveData<>();
    SingleLiveData<Boolean> goBack = new SingleLiveData<>();

    MutableLiveData<Book> bookData = new MutableLiveData<>();

    private final BooksRepository booksRepository;

    @Inject
    public InfoViewModel(
            BooksRepository booksRepository
    ) {
        this.booksRepository = booksRepository;
    }

    public void deleteBookPressed() {
        booksRepository.deleteBook(bookData.getValue().getId(), new ResultCallback<Boolean>() {
            @Override
            public void success(Boolean aBoolean) {
                goBack.setValue(true);
            }

            @Override
            public void failure(Throwable t) {
                message.setValue("Error: " + t.getMessage());
            }
        });
    }

    public void savePressed(String author,
                            String title,
                            String publishingHouse,
                            String yearOfPublishing,
                            String location,
                            String language,
                            String bookCaseLocation) {

        Book book = new Book(bookData.getValue().getId(),
                bookData.getValue().getUserId(),
                title,
                author,
                publishingHouse,
                Integer.parseInt(yearOfPublishing),
                location,
                language, bookCaseLocation);

        booksRepository.editBook(book, new ResultCallback<Boolean>() {
            @Override
            public void success(Boolean aBoolean) {
                goBack.postValue(true);
            }

            @Override
            public void failure(Throwable t) {
                message.postValue("Error: " + t.getMessage());
            }
        });
    }

    public void getBookWithId(String bookId) {
        booksRepository.getBook(bookId, new ResultCallback<Book>() {
            @Override
            public void success(Book book) {
                bookData.postValue(book);
            }

            @Override
            public void failure(Throwable t) {
                message.postValue("Error: " + t.getMessage());
            }
        });
    }
}

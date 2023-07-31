package com.app.mylibrary.ui.addbook;

import androidx.lifecycle.ViewModel;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.Book;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.utils.SingleLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddBookViewModel extends ViewModel {

    SingleLiveData<String> message = new SingleLiveData<>();
    SingleLiveData<Boolean> goBack = new SingleLiveData<>();

    private final BooksRepository booksRepository;
    private final AuthRepository authRepository;

    @Inject
    public AddBookViewModel(BooksRepository booksRepository,
                            AuthRepository authRepository) {
        this.booksRepository = booksRepository;
        this.authRepository = authRepository;
    }

    public void savePressed(String author,
                            String title,
                            String publishingHouse,
                            String yearOfPublishing,
                            String location,
                            String language,
                            String bookCaseLocation) {

        Book book = new Book("",
                authRepository.getUserId(),
                title,
                author,
                publishingHouse,
                Integer.parseInt(yearOfPublishing),
                location,
                language, bookCaseLocation);

        booksRepository.addBook(book, new ResultCallback<Boolean>() {
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


}

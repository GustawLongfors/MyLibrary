package com.app.mylibrary.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.Book;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.utils.SingleLiveData;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    SingleLiveData<String> message = new SingleLiveData<>();
    MutableLiveData<List<BookUi>> booksData = new MutableLiveData<>();

    private final BooksRepository booksRepository;
    private final AuthRepository authRepository;


    @Inject
    public MainViewModel(
            BooksRepository booksRepository,
            AuthRepository authRepository
    ) {
        this.booksRepository = booksRepository;
        this.authRepository = authRepository;
    }

    public void refresh() {
        this.booksRepository.getUserBooks(authRepository.getUserId(), new ResultCallback<List<Book>>() {
            @Override
            public void success(List<Book> books) {
                booksData.postValue(books.stream().map(v -> new BookUi(v.getId(),
                                v.getTitle(),
                                v.getAuthor()))
                        .collect(Collectors.toList()));
            }

            @Override
            public void failure(Throwable t) {
                message.postValue("Error: " + t.getMessage());
            }
        });
    }
}

package com.app.mylibrary.ui.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.utils.SingleLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel extends ViewModel {


    private final AuthRepository authRepository;
    private final BooksRepository booksRepository;

    SingleLiveData<String> message = new SingleLiveData<>();
    SingleLiveData<Integer> navigation = new SingleLiveData<>();

    MutableLiveData<ProfileUi> profileData = new MutableLiveData<>();
    SingleLiveData<Boolean> logout = new SingleLiveData<>();

    @Inject
    public ProfileViewModel(AuthRepository authRepository,
                            BooksRepository booksRepository) {
        this.authRepository = authRepository;
        this.booksRepository = booksRepository;
    }

    public void logoutPressed() {
        authRepository.logoutUser();
        logout.postValue(true);
    }

    public void viewCreated() {
        booksRepository.getBooksCount(authRepository.getUserId(), new ResultCallback<Long>() {
            @Override
            public void success(Long booksCount) {
                profileData.postValue(new ProfileUi(authRepository.getUserEmail(), booksCount, authRepository.getUserProfileUrl()));
            }

            @Override
            public void failure(Throwable t) {
                message.postValue("Error: " + t.getMessage());
            }
        });
    }
}

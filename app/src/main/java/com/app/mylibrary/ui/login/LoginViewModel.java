package com.app.mylibrary.ui.login;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.app.mylibrary.R;
import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.utils.SingleLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;

    SingleLiveData<String> message = new SingleLiveData<>();
    SingleLiveData<Integer> navigation = new SingleLiveData<>();

    SingleLiveData<Intent> loginIntent = new SingleLiveData<>();


    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void signInPressed() {
        loginIntent.setValue(authRepository.createLoginIntent());
    }

    public void loginResult(Intent data) {
        authRepository.loginResult(data, new ResultCallback<Boolean>() {
            @Override
            public void success(Boolean aBoolean) {
                navigation.postValue(R.id.mainFragment);
            }

            @Override
            public void failure(Throwable t) {
                message.postValue("Error: " + t.getMessage());
            }
        });
    }

    public void viewCreated() {
        if (authRepository.isUserLogged()) {
            navigation.postValue(R.id.mainFragment);
        }
    }
}

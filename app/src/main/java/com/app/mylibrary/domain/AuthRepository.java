package com.app.mylibrary.domain;

import android.content.Intent;

public interface AuthRepository {

    String getUserId();

    Intent createLoginIntent();

    void loginResult(Intent data,ResultCallback<Boolean> callback);

    void logoutUser();
    String getUserEmail();

    String getUserProfileUrl();

    boolean isUserLogged();

}

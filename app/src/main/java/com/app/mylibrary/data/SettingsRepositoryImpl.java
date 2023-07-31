package com.app.mylibrary.data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.EFontSize;
import com.app.mylibrary.domain.ResultCallback;
import com.app.mylibrary.domain.SettingsRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class SettingsRepositoryImpl implements SettingsRepository {

    private final SharedPreferences sharedPref;

    @Inject
    public SettingsRepositoryImpl(
            @ApplicationContext Context context
    ) {
        sharedPref = context.getSharedPreferences("mySettingsPref", Context.MODE_PRIVATE);

    }

    @Override
    public void setDarkMode(boolean darkMode) {
        if (isDarkMode() != darkMode) {
            sharedPref.edit().putBoolean("darkMode", darkMode).apply();
        }
    }

    @Override
    public void setFontSize(EFontSize fontScale) {
        if (fontScale != getFontSize()) {
            sharedPref.edit().putString("fontScale", fontScale.name()).apply();
        }
    }

    @Override
    public boolean isDarkMode() {
        return sharedPref.getBoolean("darkMode", false);
    }

    @Override
    public EFontSize getFontSize() {
        return EFontSize.valueOf(sharedPref.getString("fontScale", EFontSize.NORMAL.name()));
    }
}

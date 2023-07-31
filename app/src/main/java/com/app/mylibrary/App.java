package com.app.mylibrary;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.app.mylibrary.domain.SettingsRepository;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    @Inject
    public SettingsRepository settingsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

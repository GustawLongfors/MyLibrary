package com.app.mylibrary.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.mylibrary.App;
import com.app.mylibrary.R;
import com.app.mylibrary.databinding.ActivityMainBinding;
import com.app.mylibrary.domain.EFontSize;
import com.app.mylibrary.domain.SettingsRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();
        NavigationUI.setupWithNavController(
                binding.toolbar, navController, appBarConfiguration);

        updateMode(((App) getApplicationContext()).settingsRepository.isDarkMode());
    }

    private void updateMode(boolean darkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context ctx = adjustFontScale(newBase, newBase.getResources().getConfiguration(), ((App) newBase.getApplicationContext()).settingsRepository.getFontSize().getScale());
        super.attachBaseContext(ctx);
    }

    public Context adjustFontScale(Context newBase, Configuration configuration, float scale) {
        configuration.fontScale = scale;
        DisplayMetrics metrics = newBase.getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) newBase.getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        return newBase.createConfigurationContext(configuration);
    }
}

package com.app.mylibrary.ui.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.mylibrary.domain.Book;
import com.app.mylibrary.domain.EFontSize;
import com.app.mylibrary.domain.SettingsRepository;
import com.app.mylibrary.ui.main.BookUi;
import com.app.mylibrary.utils.SingleLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends ViewModel {

    private final SettingsRepository settingsRepository;

    MutableLiveData<SettingsUi> settingsData = new MutableLiveData<>();

    SingleLiveData<Boolean> recreate = new SingleLiveData<>();


    @Inject
    public SettingsViewModel(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    void setFontSize(EFontSize fontSize) {
        if (settingsRepository.getFontSize() != fontSize) {
            settingsRepository.setFontSize(fontSize);
            recreate.setValue(true);
        }
    }

    void setMode(boolean darkMode) {
        settingsRepository.setDarkMode(darkMode);
    }

    public void viewCreated() {
        EFontSize fontSize = settingsRepository.getFontSize();
        boolean darkMode = settingsRepository.isDarkMode();
        settingsData.postValue(new SettingsUi(darkMode, fontSize));
    }
}

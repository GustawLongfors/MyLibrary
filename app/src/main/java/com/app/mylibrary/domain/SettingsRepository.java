package com.app.mylibrary.domain;

public interface SettingsRepository {

    void setDarkMode(boolean darkMode);

    void setFontSize(EFontSize fontScale);

    boolean isDarkMode();

    EFontSize getFontSize();

}

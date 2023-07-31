package com.app.mylibrary.ui.settings;

import com.app.mylibrary.domain.EFontSize;

public class SettingsUi {
    private final boolean darkModeEnabled;
    private final EFontSize fontScale;

    public SettingsUi(boolean darkModeEnabled, EFontSize fontScale) {
        this.darkModeEnabled = darkModeEnabled;
        this.fontScale = fontScale;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public EFontSize getFontScale() {
        return fontScale;
    }
}

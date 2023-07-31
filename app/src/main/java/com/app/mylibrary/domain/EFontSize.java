package com.app.mylibrary.domain;

public enum EFontSize {

    SMALL(0.7f),
    NORMAL(1.0f),
    LARGE(1.3f);

    private final float scale;

    EFontSize(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }
}

package com.app.mylibrary.ui.profile;

public class ProfileUi {
    private final String email;
    private final long booksCount;
    private final String profilePictureUrl;

    public ProfileUi(String email, long booksCount, String profilePictureUrl) {
        this.email = email;
        this.booksCount = booksCount;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public long getBooksCount() {
        return booksCount;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}

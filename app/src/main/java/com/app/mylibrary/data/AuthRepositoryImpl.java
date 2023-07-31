package com.app.mylibrary.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.ResultCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class AuthRepositoryImpl implements AuthRepository {

    private final String serverClientId = "419736773766-p9q6er91gf8j07go5eihq36bhl683t7p.apps.googleusercontent.com";
    private final FirebaseAuth mAuth;
    private final GoogleSignInClient googleSignInClient;
    private final Context context;

    @Inject
    public AuthRepositoryImpl(
            @ApplicationContext Context context
    ) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientId)
                .requestEmail()
                .requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
    }

    @Override
    public String getUserId() {
        return mAuth.getCurrentUser().getUid();
    }

    @Override
    public Intent createLoginIntent() {
        return googleSignInClient.getSignInIntent();
    }

    @Override
    public void loginResult(Intent data, ResultCallback<Boolean> callback) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnSuccessListener(Executors.newSingleThreadExecutor(), authResult -> {
                        callback.success(authResult.getUser() != null);
                    })
                    .addOnFailureListener(Executors.newSingleThreadExecutor(), callback::failure);
        } catch (ApiException e) {
            Log.w("LoginFragment", "Google sign in failed", e);
            callback.failure(e);
        }
    }

    @Override
    public void logoutUser() {
        mAuth.signOut();
    }

    @Override
    public String getUserEmail() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            return acct.getEmail();
        } else {
            return "";
        }
    }

    @Override
    public String getUserProfileUrl() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            return acct.getPhotoUrl().toString();
        } else {
            return "";
        }
    }

    @Override
    public boolean isUserLogged() {
        return mAuth.getCurrentUser() != null;
    }

}

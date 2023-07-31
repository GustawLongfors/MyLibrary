package com.app.mylibrary.data;

import com.app.mylibrary.domain.AuthRepository;
import com.app.mylibrary.domain.BooksRepository;
import com.app.mylibrary.domain.SettingsRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract class RepositoryModules {

    @Singleton
    @Binds
    abstract AuthRepository bindAuthRepository(AuthRepositoryImpl repository);

    @Singleton
    @Binds
    abstract BooksRepository bindBooksRepository(BooksRepositoryImpl repository);

    @Singleton
    @Binds
    abstract SettingsRepository bindSettingsRepository(SettingsRepositoryImpl repository);
}

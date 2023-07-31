package com.app.mylibrary.domain;

public interface ResultCallback<T> {

    void success(T t);

    void failure(Throwable t);
}

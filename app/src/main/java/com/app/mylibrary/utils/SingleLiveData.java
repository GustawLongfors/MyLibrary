package com.app.mylibrary.utils;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveData<T> extends MutableLiveData<T> {

    private final LiveData<T> liveDataToObserve;
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    public SingleLiveData() {
        final MediatorLiveData<T> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(this, currentValue -> {
            outputLiveData.setValue(currentValue);
            mPending.set(false);
        });
        liveDataToObserve = outputLiveData;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        liveDataToObserve.observe(owner, t -> {
            if (mPending.get()) {
                observer.onChanged(t);
            }
        });
    }

    @MainThread
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
    }
}
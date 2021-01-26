package com.asgeirr.grupoasesorestest.ui.base;

import android.content.Context;

import androidx.annotation.StringRes;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    Context getmContext();
}

package com.asgeirr.grupoasesorestest.ui.main;

import com.asgeirr.grupoasesorestest.ui.base.MvpPresenter;
import com.asgeirr.grupoasesorestest.ui.base.MvpView;

public interface MainMvpPresenter<V extends MvpView> extends MvpPresenter<V>, ItemAdapter.ItemAdapterListener {
    void onQuerySearchChange(String query);

    void onPrepareOptionMenu();

    void onHistorySearchClicked(int position);
}

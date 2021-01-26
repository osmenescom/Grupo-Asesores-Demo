package com.asgeirr.grupoasesorestest.ui.main;

import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.asgeirr.grupoasesorestest.ui.base.MvpView;

public interface MainMvpView extends MvpView {
    void setAdapter(RecyclerView.Adapter adapter);

    void setEmptyListVisible(boolean show);

    void setSuggestionList(ArrayAdapter adapter);

    void setQuery(String query);
}

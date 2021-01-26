package com.asgeirr.grupoasesorestest.data;

import com.asgeirr.grupoasesorestest.data.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public interface DataManager {
    void search(String query, Callback<Result> callback);

    void detach();

    void saveQuery(String query);

    ArrayList<String> getQuerySaved();
}

package com.asgeirr.grupoasesorestest.data.remote;

import com.asgeirr.grupoasesorestest.data.model.Result;

import retrofit2.Call;
import retrofit2.Callback;

public interface ApiHelper {
    Call<Result> search(String query);
    void onDetach();
}

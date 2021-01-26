package com.asgeirr.grupoasesorestest.data.remote;

import com.asgeirr.grupoasesorestest.BuildConfig;
import com.asgeirr.grupoasesorestest.data.model.Result;
import com.asgeirr.grupoasesorestest.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GapsiChannel {

    @Headers("X-IBM-Client-Id: "+ BuildConfig.IBM_API_KEY)
    @GET(Constants.PathCommunication+"search")
    Call<Result> search(@Query("query")String query);
}

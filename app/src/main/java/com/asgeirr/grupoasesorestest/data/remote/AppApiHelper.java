package com.asgeirr.grupoasesorestest.data.remote;

import com.asgeirr.grupoasesorestest.data.model.Result;

import retrofit2.Call;
import retrofit2.Callback;

public class AppApiHelper implements ApiHelper{

    private GapsiChannel gapsiService;

    public AppApiHelper() {
        gapsiService=API.createService(GapsiChannel.class);
    }

    public Call<Result> search(String query){
        return gapsiService.search(query);
    }

    @Override
    public void onDetach() {
        gapsiService=null;
    }
}

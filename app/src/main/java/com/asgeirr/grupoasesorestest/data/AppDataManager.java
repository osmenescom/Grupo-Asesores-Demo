package com.asgeirr.grupoasesorestest.data;

import android.content.Context;

import com.asgeirr.grupoasesorestest.data.model.Result;
import com.asgeirr.grupoasesorestest.data.preferences.AppPreferencesHelper;
import com.asgeirr.grupoasesorestest.data.preferences.PreferencesHelper;
import com.asgeirr.grupoasesorestest.data.remote.ApiHelper;
import com.asgeirr.grupoasesorestest.data.remote.AppApiHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class AppDataManager implements DataManager{

    private PreferencesHelper mPreferencesHelper;
    private ApiHelper mApiHelper;

    public AppDataManager(Context context){
        mPreferencesHelper=new AppPreferencesHelper(context);
        mApiHelper= new AppApiHelper();
    }

    @Override
    public void search(String query, Callback<Result> callback){
        mApiHelper.search(query).enqueue(callback);
    }

    @Override
    public void detach(){
        mPreferencesHelper.detach();
        mPreferencesHelper=null;
        mApiHelper=null;
    }

    @Override
    public void saveQuery(String query) {
        mPreferencesHelper.saveQuery(query);
    }

    @Override
    public ArrayList<String> getQuerySaved() {
        return mPreferencesHelper.getQueriesSaved();
    }
}

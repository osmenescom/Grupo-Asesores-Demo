package com.asgeirr.grupoasesorestest.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.asgeirr.grupoasesorestest.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppPreferencesHelper implements PreferencesHelper{

    private static final String PREF_KEY_QUERY_HIST = "PREF_KEY_QUERY_HIST";
    private final String prefFileName = "BCPrefs";
    private SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void saveQuery(String query) {
        ArrayList<String> queriesList=getQueriesSaved();
        if(queriesList.contains(query))
            return;
        queriesList.add(0, query);
        if(queriesList.size()>5)
            queriesList.remove(queriesList.size()-1);
        mPrefs.edit().putString(PREF_KEY_QUERY_HIST, CommonUtils.toJson(queriesList.toArray())).apply();
    }

    @Override
    public ArrayList<String> getQueriesSaved() {
        ArrayList<String> list=new ArrayList<>();
        String[] queriesArray=CommonUtils.toObject(mPrefs.getString(PREF_KEY_QUERY_HIST, null), String[].class);
        if(queriesArray==null)
            return list;
        list= new ArrayList(Arrays.asList(queriesArray));
        return list;
    }

    @Override
    public void detach(){
        mPrefs=null;
    }
}

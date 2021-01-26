package com.asgeirr.grupoasesorestest.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.asgeirr.grupoasesorestest.R;
import com.asgeirr.grupoasesorestest.data.model.Result;
import com.asgeirr.grupoasesorestest.ui.base.BasePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private ItemAdapter adapter=new ItemAdapter(this);
    private String query=null;
    private SuggestionAdapter simpleCursorAdapter;


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().setAdapter(adapter);
        simpleCursorAdapter=new SuggestionAdapter<String>(getMvpView().getmContext(), R.layout.search_item);
        simpleCursorAdapter.addAll(getDataManager().getQuerySaved());
        requestItems();
    }

    private void requestItems() {
        getMvpView().showLoading();
        getDataManager().search(query, new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                getMvpView().hideLoading();
                if(response.body()!=null && response.code()==200){
                    adapter.addAll(response.body().getItems());
                    getMvpView().setEmptyListVisible(adapter.getItemCount()==0);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("","");
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public RequestManager getGlide() {
        return Glide.with(getMvpView().getmContext());
    }

    @Override
    public void onQuerySearchChange(String query) {
        if(this.query!=null && this.query.equals(query))
            return;
        this.query=query;
        requestItems();
        if(!TextUtils.isEmpty(query)){
            getDataManager().saveQuery(query);
            simpleCursorAdapter.addAll(getDataManager().getQuerySaved());
        }
    }

    @Override
    public void onPrepareOptionMenu() {
        getMvpView().setSuggestionList(simpleCursorAdapter);
    }

    @Override
    public void onHistorySearchClicked(int position) {
        String query=getDataManager().getQuerySaved().get(position);
        getMvpView().setQuery(query);
        onQuerySearchChange(query);
    }
}

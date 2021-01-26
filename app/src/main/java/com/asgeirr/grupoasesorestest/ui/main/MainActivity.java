package com.asgeirr.grupoasesorestest.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.asgeirr.grupoasesorestest.R;
import com.asgeirr.grupoasesorestest.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainMvpPresenter> implements MainMvpView {

    private RecyclerView rvList;
    private View tvEmptyList;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter();
        presenter.onAttach(this);
    }

    @Override
    protected void setUp() {
        rvList=findViewById(R.id.Main_rvList);
        tvEmptyList=findViewById(R.id.Main_tvEmptyList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchAutoComplete=searchView.findViewById(R.id.search_src_text);
        presenter.onPrepareOptionMenu();
        searchAutoComplete.setThreshold(0);
        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery(null, true);
                presenter.onQuerySearchChange(searchAutoComplete.getText().toString());
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                presenter.onQuerySearchChange(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onHistorySearchClicked(position);
                return;
            }
        });
        return true;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter){
        rvList.setAdapter(adapter);
    }

    @Override
    public void setEmptyListVisible(boolean show){
        tvEmptyList.setVisibility(show?View.VISIBLE:View.GONE);
    }

    @Override
    public void setSuggestionList(ArrayAdapter adapter){
        searchAutoComplete.setAdapter(adapter);
    }

    @Override
    public void setQuery(String query) {
        searchAutoComplete.setText(query);
        searchAutoComplete.setSelection(searchAutoComplete.getText().length());
    }
}
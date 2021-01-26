package com.asgeirr.grupoasesorestest.ui.main;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.asgeirr.grupoasesorestest.R;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<T> items=new ArrayList<>();
    private List<T> filteredItems=new ArrayList<>();
    private ArrayFilter mFilter;

    public SuggestionAdapter(Context context, @LayoutRes int resource) {
        super(context, resource, R.id.text1, new ArrayList<>());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    public int getCount() {
        //todo: change to pattern-size
        return items.size();
    }

    public void addAll(ArrayList<T> list){
        items.clear();
        if(items==null){
            notifyDataSetChanged();
            return;
        }
        items.addAll(list);
        notifyDataSetChanged();
    }

    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            //custom-filtering of results
            results.values = items;
            results.count = items.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItems = (List<T>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}

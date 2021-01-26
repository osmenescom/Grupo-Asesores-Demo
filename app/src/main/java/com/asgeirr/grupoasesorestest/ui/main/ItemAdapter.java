package com.asgeirr.grupoasesorestest.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asgeirr.grupoasesorestest.R;
import com.asgeirr.grupoasesorestest.data.model.Item;
import com.asgeirr.grupoasesorestest.utils.CommonUtils;
import com.asgeirr.grupoasesorestest.utils.ScreenUtils;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ItemAdapterListener listener;
    private ArrayList<Item> list = new ArrayList<>();

    public interface ItemAdapterListener{
        RequestManager getGlide();
    }

    public ItemAdapter (ItemAdapterListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<Item> items) {
        list.clear();
        if(items==null){
            notifyDataSetChanged();
            return;
        }
        list.addAll(items);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivImage;
        private TextView tvTitle, tvPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage=itemView.findViewById(R.id.Item_ivImage);
            tvTitle=itemView.findViewById(R.id.Item_tvTitle);
            tvPrice=itemView.findViewById(R.id.Item_tvPrice);
        }

        public void bindView(Item item) {
            listener.getGlide().load(item.getImage()).override((int)ScreenUtils.convertDpToPx(ivImage.getContext(), 100)).into(ivImage);
            tvTitle.setText(item.getTitle());
            tvPrice.setText(String.format("Precio: $%s", CommonUtils.formatNumber(item.getPrice(), 2)));
        }
    }
}

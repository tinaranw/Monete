package com.uc.monete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.monete.R;
import com.uc.monete.model.History;

import java.util.ArrayList;

public class CardHistory extends RecyclerView.Adapter<CardHistory.HistoryViewHolder> {

    private Context context;
    private ArrayList<History> listHistory = new ArrayList<>();

    public CardHistory(Context context){
        this.context = context;
    }

    public void setListHistory(ArrayList<History> list){
        listHistory.clear();
        listHistory.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = listHistory.get(position);
        holder.id.setText(history.getId());
        holder.money.setText(history.getAmount());
        holder.memo.setText(history.getMemo());
        holder.type.setText(history.getType());

        if(history.getTag().equalsIgnoreCase("fnb")){
            holder.tagimage.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("transport")){
            holder.tagimage.setImageResource(R.drawable.ic_directions_car_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("entertainment")){
            holder.tagimage.setImageResource(R.drawable.ic_local_play_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("groceries")){
            holder.tagimage.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("shopping")){
            holder.tagimage.setImageResource(R.drawable.ic_card_giftcard_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("debt")){
            holder.tagimage.setImageResource(R.drawable.ic_credit_card_black_24dp);
        } else if(history.getTag().equalsIgnoreCase("earnings")){
            holder.tagimage.setImageResource(R.drawable.ic_attach_money_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, money, memo,type;
        ImageView tagimage;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.hist_id);
            money = itemView.findViewById(R.id.hist_money);
            memo = itemView.findViewById(R.id.hist_memo);
            type = itemView.findViewById(R.id.hist_type);
            tagimage = itemView.findViewById(R.id.tag_img);
        }
    }
}

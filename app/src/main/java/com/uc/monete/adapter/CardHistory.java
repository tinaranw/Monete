package com.uc.monete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, money, memo,type;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.hist_id);
            money = itemView.findViewById(R.id.hist_money);
            memo = itemView.findViewById(R.id.hist_memo);
            type = itemView.findViewById(R.id.hist_type);
        }
    }
}

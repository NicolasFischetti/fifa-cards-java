package com.example.fifa_cards.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fifa_cards.R;
import com.example.fifa_cards.entity.DeckList;

import java.util.List;

public class DeckViewAdapter extends RecyclerView.Adapter<DeckViewAdapter.MyViewHolder> {

    List<DeckList> deckList;
    Context context;
    DeckList decks;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView countCards;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.deck_name);
            countCards = itemView.findViewById(R.id.count_cards);
        }
    }

    public DeckViewAdapter( Context context, List<DeckList> deckList) {
        this.context = context;
        this.deckList = deckList;
    }

    @NonNull
    @Override
    public DeckViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_view_decks, parent, false);
        return new DeckViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        decks = deckList.get(position);
        holder.name.setText(decks.getName());
    }

    @Override
    public int getItemCount() {
        return deckList.size();
    }

    public void setDecksList(List<DeckList> decks){
        deckList = decks;
        notifyDataSetChanged();
    }
}

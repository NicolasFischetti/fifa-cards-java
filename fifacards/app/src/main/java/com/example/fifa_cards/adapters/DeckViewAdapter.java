package com.example.fifa_cards.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fifa_cards.R;
import com.example.fifa_cards.entity.CardList;
import com.example.fifa_cards.entity.DeckList;
import com.example.fifa_cards.utils.Constants;
import com.example.fifa_cards.views.DeckFragmentDirections;

import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class DeckViewAdapter extends RecyclerView.Adapter<DeckViewAdapter.MyViewHolder> {

    List<DeckList> deckList;
    Context context;
    DeckList decks;
    Integer playerSizeArray;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView countCards;
        CardView cardView;

        DeckList mDeckList;
        int pos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.deck_name);
            countCards = itemView.findViewById(R.id.count_cards);
            cardView = itemView.findViewById(R.id.card_view_deck);

            cardView.setOnClickListener(v -> {

                if(!mDeckList.isSelected()) {
                    findNavController(v).navigate(DeckFragmentDirections.toDetailFragment(mDeckList.getId(), Constants.VIEW_DETAIL));
                    notifyItemChanged(pos);
                }
            });
        }

        void retrivePosition(int position, DeckList deckList) {
            this.pos = position;
            this.mDeckList = deckList;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        decks = deckList.get(position);
        holder.retrivePosition(position, deckList.get(position));
        holder.name.setText(decks.getName());
        playerSizeArray = decks.cardLists.size();
        holder.countCards.setText("Cantidad de cartas: " + playerSizeArray.toString());
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

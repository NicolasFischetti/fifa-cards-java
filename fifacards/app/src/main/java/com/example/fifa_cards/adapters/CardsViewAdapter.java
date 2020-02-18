package com.example.fifa_cards.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.fifa_cards.entity.CardList;
import com.example.fifa_cards.R;
import com.example.fifa_cards.entity.DeckList;

import java.util.ArrayList;
import java.util.List;

public class CardsViewAdapter extends RecyclerView.Adapter<CardsViewAdapter.MyViewHolder>   {
    private List<CardList> mDataset;
    private CardList players;
    private List<CardList> cardListsSelected = new ArrayList<>();
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView position;
        ImageView getPlayerImage;
        TextView pac;
        TextView sho;
        TextView pas;
        TextView dri;
        TextView def;
        TextView phy;
        CardView cardView;

        CardList cardPlayers;
        int pos;

        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.card_view);
            name = v.findViewById(R.id.player_name);
            position = v.findViewById(R.id.position);
            getPlayerImage = v.findViewById(R.id.imageView);
            pac = v.findViewById(R.id.pac);
            sho = v.findViewById(R.id.sho);
            pas = v.findViewById(R.id.pas);
            dri = v.findViewById(R.id.dri);
            def = v.findViewById(R.id.def);
            phy = v.findViewById(R.id.phy);

            cardView.setOnClickListener(view -> {

                if(cardPlayers.isSelected()) {
                    cardPlayers.setSelected(false);
                    cardListsSelected.remove(cardPlayers);
                } else {
                    cardPlayers.setSelected(true);
                    cardListsSelected.add(cardPlayers);
                }
                Log.d("array", cardListsSelected.toString());
                notifyItemChanged(pos);
            });
        }

        void retrivePosition(int position, CardList cardPlayers) {
            this.pos = position;
            this.cardPlayers = cardPlayers;
        }
    }

    public CardsViewAdapter(Context context, List<CardList> myDataset) {
        mDataset = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public CardsViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_view, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        players = mDataset.get(i);
        holder.retrivePosition(i, mDataset.get(i));
        String name = players.getName();
        String position = players.getPosition();
        String getPlayerImage = players.getPlayerImage();
        Integer pac = players.getPac();
        Integer sho = players.getSho();
        Integer pas = players.getPas();
        Integer dri = players.getDri();
        Integer def = players.getDef();
        Integer phy = players.getPhy();

        holder.name.setText(name);
        holder.position.setText(position);
        Glide
                .with(holder.getPlayerImage.getContext())
                .load(getPlayerImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CenterCrop(), new RoundedCorners(15))
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.getPlayerImage);

        holder.pac.setText("PAC: " + pac.toString());
        holder.sho.setText("SHO: " + sho.toString());
        holder.pas.setText("PAS: " + pas.toString());
        holder.dri.setText("DRI: " + dri.toString());
        holder.def.setText("DEF: " + def.toString());
        holder.phy.setText("PHY: " + phy.toString());

        if(mDataset.get(i).isSelected()) {
            holder.cardView.setCardBackgroundColor(Color.GRAY);
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public List<CardList> getSelectedCards() {
        return cardListsSelected;
    }

    public void setCardList(List<CardList> players){
        mDataset = players;
        notifyDataSetChanged();
    }
}

package com.example.fifa_cards;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlayerViewAdapter extends RecyclerView.Adapter<PlayerViewAdapter.MyViewHolder>   {
    private ArrayList<ListPlayers> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
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
        }
    }

    PlayerViewAdapter(ArrayList<ListPlayers> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public PlayerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_players, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        ListPlayers players = mDataset.get(i);
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
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.getPlayerImage);

        holder.pac.setText(pac.toString());
        holder.sho.setText(sho.toString());
        holder.pas.setText(pas.toString());
        holder.dri.setText(dri.toString());
        holder.def.setText(def.toString());
        holder.phy.setText(phy.toString());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

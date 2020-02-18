package com.example.fifa_cards.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifa_cards.R;
import com.example.fifa_cards.adapters.DeckViewAdapter;
import com.example.fifa_cards.viewmodel.DeckViewModel;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class DeckFragment extends Fragment {

    RecyclerView recyclerDeck;
    private DeckViewModel deckViewModel;
    private DeckViewAdapter deckViewAdapter;

    public DeckFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deck, container, false);

        deckViewModel = ViewModelProviders.of(this).get(DeckViewModel.class);
        deckViewModel.getDecksCard().observe(getViewLifecycleOwner(), listDecks ->{
            recyclerDeck = view.findViewById(R.id.my_recycler_view_decks);
            if(deckViewAdapter == null) {
                recyclerDeck.setLayoutManager(new GridLayoutManager(getContext(), 2));
                deckViewAdapter = new DeckViewAdapter(getContext(), listDecks);
                recyclerDeck.setAdapter(deckViewAdapter);
            } else {
                deckViewAdapter.setDecksList(listDecks);
            }
        });

        return view;
    }

}

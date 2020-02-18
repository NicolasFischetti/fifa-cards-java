package com.example.fifa_cards.views;

import android.app.AlertDialog;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fifa_cards.R;
import com.example.fifa_cards.adapters.CardsViewAdapter;
import com.example.fifa_cards.entity.CardList;
import com.example.fifa_cards.entity.DeckList;
import com.example.fifa_cards.viewmodel.CardsViewModel;

import java.util.List;
import java.util.Random;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class CardsFragment extends Fragment {

    private Button createButton;
    private CardsViewModel viewModel;
    private CardsViewAdapter playerViewAdapter;
    public DeckList deckList;

    public CardsFragment newInstance() {
            return new CardsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

        createButton =  rootView.findViewById(R.id.create_deck_btn);
        viewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
        viewModel.getPlayersCards().observe(getViewLifecycleOwner(), listPlayers -> {
            RecyclerView recyclerView = rootView.findViewById(R.id.my_recycler_view);
            if(playerViewAdapter == null) {
                playerViewAdapter = new CardsViewAdapter(getContext(), listPlayers);
                recyclerView.setAdapter(playerViewAdapter);
            } else {
                playerViewAdapter.setCardList(listPlayers);
            }
            recyclerView.setSelected(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
        });

        alertDialog();

        return rootView;
    }

    private void alertDialog() {
        createButton.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            deckList = new DeckList(randomId());
            deckList.setName(taskEditText.getText().toString());

            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Create your deck")
                    .setCancelable(false)
                    .setMessage("Please enter a name")
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialog1, which) -> {
                        Navigation.findNavController(v).navigate(R.id.to_deck_fragment, null);
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        });
    }

    private Integer randomId() {
        Random random = new Random();
        return random.nextInt() & Integer.MAX_VALUE;
    }

}

package com.example.fifa_cards;

import android.app.AlertDialog;
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

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class CardsFragment extends Fragment {

    private Button createButton;
    private PlayersViewModel viewModel;
    public NavController navController;
    private PlayerViewAdapter playerViewAdapter;

    public CardsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);

        createButton =  rootView.findViewById(R.id.create_deck_btn);
        viewModel = ViewModelProviders.of(this).get(PlayersViewModel.class);
        viewModel.getPlayersCards().observe(getViewLifecycleOwner(), listPlayers -> {
            RecyclerView recyclerView = rootView.findViewById(R.id.my_recycler_view);
            if(playerViewAdapter == null) {
                playerViewAdapter = new PlayerViewAdapter(listPlayers);
                recyclerView.setAdapter(playerViewAdapter);
            }
            recyclerView.setSelected(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), VERTICAL, false));
        });

        alertDialog();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void alertDialog() {
        createButton.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(getActivity());
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Create your deck")
                    .setCancelable(false)
                    .setMessage("Please enter a name")
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialog1, which) -> {
                        navController.navigate(R.id.to_deck_fragment, null);
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        });
    }

}

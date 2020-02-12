package com.example.fifa_cards;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private Button createButton;
    private PlayersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

       createButton = findViewById(R.id.create_deck_btn);
       viewModel = ViewModelProviders.of(this).get(PlayersViewModel.class);

        viewModel.getPlayersCards().observe(this, listPlayers -> {
           RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
           recyclerView.setSelected(true);
           recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), VERTICAL, false));
           PlayerViewAdapter mAdapter = new PlayerViewAdapter(listPlayers);
           recyclerView.setAdapter(mAdapter);
       });

       alertDialog();
    }

    private void alertDialog() {
        createButton.setOnClickListener(v -> {
            final EditText taskEditText = new EditText(MainActivity.this);
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Create your deck")
                    .setCancelable(false)
                    .setMessage("Please enter a name")
                    .setView(taskEditText)
                    .setPositiveButton("Add", (dialog1, which) -> {

                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

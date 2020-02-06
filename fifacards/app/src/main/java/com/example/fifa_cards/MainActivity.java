package com.example.fifa_cards;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListPlayers> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_cards);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       RecyclerView recyclerView =  findViewById(R.id.my_recycler_view);
       recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
       recyclerView.setLayoutManager(layoutManager);
       RecyclerView.Adapter mAdapter = new PlayerViewAdapter(playerList);
       recyclerView.setAdapter(mAdapter);

        getJsonFileFromLocally();
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("cards.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return json;
    }

    private void getJsonFileFromLocally() {
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Integer id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String playerImage = jsonObject.getString("playerImage");
                String position = jsonObject.getString("position");
                Integer pac = jsonObject.getInt("pac");
                Integer sho = jsonObject.getInt("sho");
                Integer pas = jsonObject.getInt("pas");
                Integer dri = jsonObject.getInt("dri");
                Integer def = jsonObject.getInt("def");
                Integer phy = jsonObject.getInt("phy");

                ListPlayers model = new ListPlayers();
                model.setId(id);
                model.setName(name);
                model.setPlayerImage(playerImage);
                model.setPosition(position);
                model.setPac(pac);
                model.setSho(sho);
                model.setPas(pas);
                model.setDri(dri);
                model.setDef(def);
                model.setPhy(phy);
                playerList.add(model);
            }
        } catch (JSONException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
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

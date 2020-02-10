package com.example.fifa_cards;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CardsRepository {

    private ArrayList<CardList> playerList = new ArrayList<>();
    private MutableLiveData<ArrayList<CardList>> mutableLiveDataPlayers = new MutableLiveData<>();
    private Application application;

    public CardsRepository(Application application) {
        this.application = application;
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputStream = application.getAssets().open("cards.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.d("error", e.toString());
        }
        return json;
    }

    public MutableLiveData<ArrayList<CardList>> getJsonFileFromLocally() {
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

                CardList model = new CardList();
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
                mutableLiveDataPlayers.setValue(playerList);
            }
        } catch (JSONException e) {
            Log.d("error2", e.toString());
        }

        return mutableLiveDataPlayers;
    }

}

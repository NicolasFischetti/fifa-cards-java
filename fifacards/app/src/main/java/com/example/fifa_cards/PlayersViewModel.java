package com.example.fifa_cards;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PlayersViewModel extends AndroidViewModel {
    private CardsRepository cardRepository;

    public PlayersViewModel(@NonNull Application application) {
        super(application);
        cardRepository = new CardsRepository(application);
    }

    LiveData<ArrayList<CardList>> getPlayersCards() {
        return cardRepository.getJsonFileFromLocally();
    }

}

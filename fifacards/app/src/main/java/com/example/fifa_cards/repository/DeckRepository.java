package com.example.fifa_cards.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fifa_cards.entity.DeckList;
import com.example.fifa_cards.database.CardsRoomDataBase;
import com.example.fifa_cards.database.DeckDao;

import java.util.List;

public class DeckRepository {

    private DeckDao deckDao;
    private LiveData<List<DeckList>> liveDataDecks;

    public DeckRepository(Application application) {
        CardsRoomDataBase db = CardsRoomDataBase.getInstance(application.getApplicationContext());
        deckDao = db.deckDao();
        liveDataDecks = deckDao.getAllDecks();
    }

    public LiveData<List<DeckList>> getAllDecks() {
        return liveDataDecks;
    }

     public LiveData<DeckList> getDeck(int id) {
        return deckDao.getDeckId(id);
    }

     public void deleteDeck(int id) {
        CardsRoomDataBase.databaseWriteExecutor.execute(() -> {
            deckDao.deleteDeck(id);
        });
    }

     public void insert(DeckList deck) {
        CardsRoomDataBase.databaseWriteExecutor.execute(() -> {
            deckDao.insert(deck);
        });
    }

     public void update(DeckList deck) {
        CardsRoomDataBase.databaseWriteExecutor.execute(() -> {
            deckDao.update(deck);
        });

    }
}

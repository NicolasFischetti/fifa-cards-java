package com.example.fifa_cards.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.fifa_cards.entity.DeckList;
import com.example.fifa_cards.database.CardsRoomDataBase;
import com.example.fifa_cards.database.DeckDao;

import java.util.List;

public class DeckRepository {

    private DeckDao deckDao;
    private MediatorLiveData<List<DeckList>> liveDataDecks = new MediatorLiveData<>();

    public DeckRepository(Application application) {
        CardsRoomDataBase db = CardsRoomDataBase.getInstance(application.getApplicationContext());
        deckDao = db.deckDao();
        liveDataDecks.addSource(db.deckDao().getAllDecks(), deckLists -> {
            if (db.getDatabaseCreated().getValue() != null) {
                liveDataDecks.postValue(deckLists);
            }
        });
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

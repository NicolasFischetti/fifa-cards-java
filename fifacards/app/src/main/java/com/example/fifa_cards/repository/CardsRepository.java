package com.example.fifa_cards.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.fifa_cards.entity.CardList;
import com.example.fifa_cards.database.CardsDao;
import com.example.fifa_cards.database.CardsRoomDataBase;

import java.util.List;

public class CardsRepository {

    private MediatorLiveData<List<CardList>> liveDataPlayers = new MediatorLiveData<>();
    private CardsDao cardsDao;

    public CardsRepository(Application application) {
        CardsRoomDataBase db = CardsRoomDataBase.getInstance(application.getApplicationContext());
        cardsDao = db.cardsDao();
        liveDataPlayers.addSource(db.cardsDao().getall(),
                cardsEntities -> {
                    if (db.getDatabaseCreated().getValue() != null) {
                        liveDataPlayers.postValue(cardsEntities);
                    }
                });
    }

    public LiveData<List<CardList>> getAll() {
        return liveDataPlayers;
    }

    public void insert(List<CardList> cards) {
        CardsRoomDataBase.databaseWriteExecutor.execute(() -> {
            cardsDao.insert(cards);
        });
    }

}

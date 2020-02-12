package com.example.fifa_cards;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlayersViewModel extends AndroidViewModel {
    private CardsRepository cardRepository;
    private LiveData<List<CardList>> mCardList;

    public PlayersViewModel(@NonNull Application application) {
        super(application);
        cardRepository = new CardsRepository(application);
        mCardList = cardRepository.getAll();
    }

    LiveData<List<CardList>> getPlayersCards() {
        return mCardList;
    }

    public void insert(List<CardList> cards) { cardRepository.insert(cards); }

}

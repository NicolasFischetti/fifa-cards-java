package com.example.fifa_cards.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fifa_cards.entity.CardList;
import com.example.fifa_cards.repository.CardsRepository;

import java.util.List;

public class CardsViewModel extends AndroidViewModel {
    private CardsRepository cardRepository;
    private LiveData<List<CardList>> mCardList;

    public CardsViewModel(@NonNull Application application) {
        super(application);
        cardRepository = new CardsRepository(application);
        mCardList = cardRepository.getAll();
    }

    public LiveData<List<CardList>> getPlayersCards() {
        return mCardList;
    }

    public void insert(List<CardList> cards) { cardRepository.insert(cards); }

}

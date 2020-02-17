package com.example.fifa_cards.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fifa_cards.entity.DeckList;
import com.example.fifa_cards.repository.DeckRepository;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {
    private DeckRepository deckRepository;
    private LiveData<List<DeckList>> mDeckList;

    public DeckViewModel(Application application) {
        super(application);
        deckRepository = new DeckRepository(application);
        mDeckList = deckRepository.getAllDecks();
    }

    public LiveData<List<DeckList>> getDecksCard() {
        return mDeckList;
    }

    public LiveData<DeckList> getDeckId(int id) {
        return deckRepository.getDeck(id);
    }

    public void insert(DeckList decks) { deckRepository.insert(decks); }

    public void update(DeckList decks){
        deckRepository.update(decks);
    }

    public void deleteDeck(int id){
        deckRepository.deleteDeck(id);
    }

}

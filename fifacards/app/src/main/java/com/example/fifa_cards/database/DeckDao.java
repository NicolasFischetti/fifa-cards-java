package com.example.fifa_cards.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fifa_cards.entity.DeckList;

import java.util.List;

@Dao
public interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DeckList deck);

    @Query("DELETE FROM deck_table")
    void deleteAll();

    @Update
    void update(DeckList deck);

    @Query("SELECT * FROM deck_table")
    LiveData<List<DeckList>> getAllDecks();

    @Query("SELECT * FROM deck_table WHERE id=:id")
    LiveData<DeckList> getDeckId(int id);

    @Query("DELETE FROM deck_table WHERE id=:id")
    void deleteDeck(int id);
}

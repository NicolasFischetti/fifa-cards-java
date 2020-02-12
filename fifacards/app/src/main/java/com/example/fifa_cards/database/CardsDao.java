package com.example.fifa_cards.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fifa_cards.CardList;

import java.util.List;

@Dao
public interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<CardList> cards);

    @Query("SELECT * from cards")
   LiveData<List<CardList>> getall();

    @Query("DELETE FROM cards")
    void deleteAll();
}

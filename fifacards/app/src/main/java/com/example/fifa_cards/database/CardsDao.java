package com.example.fifa_cards.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.fifa_cards.CardList;

import java.util.ArrayList;

@Dao
public interface CardsDao {

    @Query("SELECT * from cards_table")
    ArrayList<CardList> getall();

    @Query("DELETE FROM cards_table")
    void deleteAll();
}

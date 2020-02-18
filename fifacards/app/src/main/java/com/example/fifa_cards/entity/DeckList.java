package com.example.fifa_cards.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fifa_cards.utils.ConverterType;

import java.util.List;

@Entity(tableName = "deck_table")
public class DeckList {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    private String name;
    private int countCards;

    @TypeConverters({ConverterType.class})
    public List<CardList> cardLists;

    public int getCountCards() {
        return countCards;
    }

    public void setCountCards(int countCards) {
        this.countCards = countCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeckList(){}

    public DeckList(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @TypeConverters({ConverterType.class})
    public List<CardList> getCardListPlayers() {
        return cardLists;
    }

    @TypeConverters({ConverterType.class})
    public void setCardListPlayers(List<CardList> cardLists) {
        this.cardLists = cardLists;
    }

}

package com.example.fifa_cards.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fifa_cards.CardList;

import java.util.ArrayList;

@Entity(tableName = "cards_table")
public class Cards {

    @NonNull
    @PrimaryKey
    public Integer id;

    public String mName;
    public String mPlayerImage;
    public String mPosition;
    public Integer mPac;
    public Integer mSho;
    public Integer mPas;
    public Integer mDri;
    public Integer mDef;
    public Integer mPhy;

   @TypeConverters(ConverterType.class)
   @ColumnInfo(name = "cards")
   public ArrayList<CardList> cardList;

   @ColumnInfo(name = "name")
   public String name;

   @ColumnInfo(name = "playerImage")
   public String playerImage;

   @ColumnInfo(name = "position")
   public String position;

    @ColumnInfo(name = "pac")
    public Integer pac;

    @ColumnInfo(name = "sho")
    public Integer sho;

    @ColumnInfo(name = "pas")
    public Integer pas;

    @ColumnInfo(name = "dri")
    public Integer dri;

    @ColumnInfo(name = "def")
    public Integer def;

    @ColumnInfo(name = "phy")
    public Integer phy;

}

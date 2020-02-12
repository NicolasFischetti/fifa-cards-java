package com.example.fifa_cards.database;

import androidx.room.TypeConverter;

import com.example.fifa_cards.CardList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConverterType {

    public static Gson gson;

    @TypeConverter
    public static ArrayList<CardList> stringToSomeObjectList(String data) {
        if (data == null) {
            return null;
        }
        Type listType = new TypeToken<ArrayList<CardList>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(ArrayList<CardList> someObjects) {
        return gson.toJson(someObjects);
    }

}

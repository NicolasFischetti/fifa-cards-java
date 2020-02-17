package com.example.fifa_cards.utils;

import androidx.room.TypeConverter;

import com.example.fifa_cards.entity.CardList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ConverterType {

    @TypeConverter
    public static List<CardList> fromString(String value) {
        Type listType = new TypeToken<List<CardList>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<CardList> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}

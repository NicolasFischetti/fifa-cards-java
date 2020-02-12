package com.example.fifa_cards.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fifa_cards.CardList;
import com.example.fifa_cards.LoadJson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CardList.class}, version = 1, exportSchema = false)
public abstract class CardsRoomDataBase extends RoomDatabase {
     public abstract CardsDao cardsDao();
     private MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static volatile CardsRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CardsRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardsRoomDataBase.class) {
                if (INSTANCE == null) { INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardsRoomDataBase.class, "cards_database")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                databaseWriteExecutor.execute(() -> {
                                    addDelay();
                                    CardsRoomDataBase database = CardsRoomDataBase.getDatabase(context);
                                    database.setDatabaseCreated();
                                    LoadJson json = new LoadJson(context);
                                    List<CardList> cards = json.getJsonFileFromLocally();
                                    database.cardsDao().insert(cards);
                                });
                            }
                        })
                        .build();
                    }
                }
            }
            return INSTANCE;
        }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }
    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }
}

package com.example.fifa_cards.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Cards.class}, version = 1, exportSchema = false)
public abstract class CardsRoomDataBase extends RoomDatabase {
     public abstract CardsDao cardsDao();

    private static volatile CardsRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CardsRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardsRoomDataBase.class) {
                if (INSTANCE == null) { INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardsRoomDataBase.class, "cards_table")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    }
                }
            }
            return INSTANCE;
        }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CardsDao dao = INSTANCE.cardsDao();
                dao.getall();
            });
        }
    };
    }

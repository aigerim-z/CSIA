package com.example.myapplication.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.SyncStateContract;

//Database
@Database(entities = {Dish.class}, version = 1, exportSchema = false)
public abstract class DishRoomDatabase extends RoomDatabase {
    public abstract DishDAO dishDAO();

    public static DishRoomDatabase INSTANCE;

    public static DishRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = buldDatabaseInstance(context);
        }
        return INSTANCE;
    }

    private static DishRoomDatabase buldDatabaseInstance(Context context){
        return Room.databaseBuilder(context,
                DishRoomDatabase.class,
                SyncStateContract.Constants.ACCOUNT_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp(){ INSTANCE = null;}
}

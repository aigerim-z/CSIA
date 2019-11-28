package com.example.myapplication.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface DishDAO {

    @Insert
    long insert(Dish dish);

    @Query("SELECT * from dishes_table")
    List<Dish> getAllDIshes();

    @Delete
    void delete(Dish dish);

}
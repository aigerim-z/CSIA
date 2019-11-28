package com.example.myapplication.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "dishes_table")
public class Dish implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long dish_id;

    @NonNull
    @ColumnInfo(name = "Dish Name")
    private String mDish;

    @ColumnInfo(name = "Dish Type")
    private String mType;


    public Dish(@NonNull String dish, String type) {
        this.mDish = dish;
        this.mType = type;
    }

    @Ignore
    public Dish(){}

    public long getDish_id() {return this.dish_id;}

    public void setDish_id(long dish_id) {this.dish_id = dish_id;}

    public String getDish() {
        return this.mDish;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String mType){
        this.mType = mType;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if ( !(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (dish_id != dish.dish_id) return false;
        return Objects.equals(mType, dish.mType);
    }

    @Override
    public int hashCode(){
        int result = (int) dish_id;
        result = 31 * result + (mType != null ? mType.hashCode(): 0);
        return result;
    }

    @Override
    public String toString(){
        return ("Dish{" +
                "dish_id=" + dish_id +
                ", dish name" + mDish +
                '\'' +
                ", type ='" + mType + '}');
    }

}
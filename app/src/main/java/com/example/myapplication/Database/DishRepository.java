package com.example.myapplication.Database;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class DishRepository {
    private DishDAO mDishDao;
    private LiveData<List<Dish>> mAllDishes;

    public DishRepository(Application application){
        DishRoomDatabase db = DishRoomDatabase.getDatabase(application);
        mDishDao = db.dishDAO();
        mAllDishes = (LiveData<List<Dish>>) mDishDao.getAllDIshes();
    }
    public LiveData<List<Dish>> getAllDishes(){
        return mAllDishes;
    }
    public void insert (Dish dish){
        new insertAsyncTask(mDishDao).execute(dish);
    }

    private static class insertAsyncTask extends AsyncTask<Dish, Void, Void>{
        private DishDAO mAsyncTaskDao;

        insertAsyncTask(DishDAO dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Dish... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}

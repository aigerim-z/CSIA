package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.myapplication.Database.Dish;
import com.example.myapplication.Database.DishListAdapter;
import com.example.myapplication.Database.DishRoomDatabase;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


//class of all recipes
public class Allrecipes extends AppCompatActivity implements DishListAdapter.OnDishItemClick {

    private TextView textViewMsg;
    private DishRoomDatabase dishRoomDatabase;
    private List<Dish> dishes;
    private DishListAdapter dishListAdapter;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allrecipes);
        intializeVies();
        displayList();
    }

    private void displayList(){
        dishRoomDatabase = DishRoomDatabase.getDatabase(Allrecipes.this);
        new RetrieveTask(this).execute();
    }

    private static class RetrieveTask extends AsyncTask<Void,Void,List<Dish>>{
        private WeakReference<Allrecipes> activityReference;

        RetrieveTask(Allrecipes context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Dish> doInBackground(Void... voids){
            if (activityReference.get()!= null) {
                return activityReference.get().dishRoomDatabase.dishDAO().getAllDIshes();
            } else {return null;}
        }

        @Override
        protected void onPostExecute(List<Dish> dishes){
            if (dishes!=null && dishes.size()>0){
                activityReference.get().dishes.clear();
                activityReference.get().dishes.addAll(dishes);

                activityReference.get().textViewMsg.setVisibility(View.GONE);
                activityReference.get().dishListAdapter.notifyDataSetChanged();}
        }
    }

    private void intializeVies(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewMsg = (TextView) findViewById(R.id.tv__empty);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(listener);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dishes = new ArrayList<>();
        dishListAdapter = new DishListAdapter(dishes,Allrecipes.this);
        recyclerView.setAdapter(dishListAdapter);
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            startActivityForResult(new Intent(Allrecipes.this,Main2Activity.class),100);
        }
    };

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        if (requestCode == 100 && resultCode > 0){
            if ( resultCode == 1){
                dishes.add((Dish) data.getSerializableExtra("dish"));
            }else if( resultCode == 2){
                dishes.set(pos,(Dish) data.getSerializableExtra("dish"));
            }
            listVisibility();
        }
    }

    @Override
    public void onDishClick(final int pos){
        new AlertDialog.Builder(Allrecipes.this)
        .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                dishRoomDatabase.dishDAO().delete(dishes.get(pos));
                                dishes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                Allrecipes.this.pos = pos;
                                startActivityForResult(
                                        new Intent(Allrecipes.this, Main2Activity.class).putExtra("dish",dishes.get(pos)), 100);
                                break;
                        }
                    }
                }).show();
    }

    private void listVisibility(){
        int emptyMsgVisibility = View.GONE;
        if (dishes.size()==0){
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        dishListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy(){
        dishRoomDatabase.cleanUp();
        super.onDestroy();
    }
}

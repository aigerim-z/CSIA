package com.example.myapplication;

import android.app.Activity;
import android.arch.persistence.room.Insert;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.myapplication.Database.Dish;
import com.example.myapplication.Database.DishRoomDatabase;

import java.lang.ref.WeakReference;

import static android.util.Log.*;

public class Main2Activity extends AppCompatActivity {

    String types[] = {"Breakfast", "Main", "Desserts", "One more"};

    private DishRoomDatabase dishRoomDatabase;
    //IMPORT TEXT INPUT AND SCROLL VIEW
    private Dish dish;
    private Spinner dish_type;
    private EditText dish_name;
    private Boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //TEXT INPUT AND spinner GO HERE
        dish_type = findViewById(R.id.recipe_type);
        dish_name = findViewById(R.id.recipe_name);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dish_type.setAdapter(spinnerArrayAdapter);


        dishRoomDatabase = DishRoomDatabase.getDatabase(Main2Activity.this);
        Button button = findViewById(R.id.create_new_recipe);
        if( (dish = (Dish) getIntent().getSerializableExtra("dish"))!=null){
            getSupportActionBar().setTitle("Edit Dish");
            update = true;
            button.setText("Update");
            dish_name.setText(dish.getDish());
            dish_type.setAdapter(spinnerArrayAdapter);
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dish = new Dish(dish_name.getText().toString(), dish_type.getSelectedItem().toString());
                new InsertTask(Main2Activity.this,dish).execute();
            }
        });
}
    private void setResult(Dish dish, int flag){
        setResult(flag, new Intent().putExtra("dish", dish));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean>{

        private WeakReference<Main2Activity> main2ActivityWeakReference;
        private Dish dish;

        InsertTask(Main2Activity context, Dish dish){
            main2ActivityWeakReference = new WeakReference<>(context);
            this.dish = dish;
        }

        @Override
        protected Boolean doInBackground(Void... objs){

            long j = main2ActivityWeakReference.get().dishRoomDatabase.dishDAO().insert(dish);
            dish.setDish_id(j);
            e("ID", "doInBackground:"+j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean bool){
            if (bool){
                main2ActivityWeakReference.get().setResult(dish, 1);
                main2ActivityWeakReference.get().finish();
            }
        }

    }





}


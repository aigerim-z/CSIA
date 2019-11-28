package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MyRecipes extends Activity {

    private Button button2;
    private Button button;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        button2 = (Button) findViewById(R.id.all_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity3();
            }
        });
        button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });
        button3 = (Button) findViewById(R.id.breakfast);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity4();
            }
        });
        button4 = (Button) findViewById(R.id.appetizers);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity5();
            }
        });
        button5 = (Button) findViewById(R.id.desserts);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity6();
            }
        });
        button7 = (Button) findViewById(R.id.main_dishes);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity7();
            }
        });

    }

    public void openactivity7(){
        Intent intent = new Intent(this, main_dishes.class);
        startActivity(intent);
    }
    public void openactivity6(){
        Intent intent = new Intent(this, desserts.class);
        startActivity(intent);
    }
    public void openactivity5(){
        Intent intent = new Intent(this, Appetizers.class);
        startActivity(intent);
    }
    public void openactivity4(){
        Intent intent = new Intent(this, Breakfasts.class);
        startActivity(intent);
    }
    public void openactivity2() {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void openactivity3(){
        Intent intent = new Intent(this, Allrecipes.class);
        startActivity(intent);
    }
}

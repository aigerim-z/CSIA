package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class edit_recipe extends Activity {
    private LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        parentLinearLayout = findViewById(R.id.edit_recipe);}
        public void onAddField(View v){
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.new_ing_field, null);
            //dynamically adding linear layout
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount()-3);
        }


    public void onDelete(View v){
        parentLinearLayout.removeView((View) v.getParent());
    }
}

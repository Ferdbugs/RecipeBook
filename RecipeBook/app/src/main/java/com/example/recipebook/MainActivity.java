package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    EditText RecipeTitle;
    EditText Instructions;
    EditText Ratings;
    Button Explore;
    Button AddRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddRecipe = findViewById(R.id.ADD);
        final Intent AddItem = new Intent(MainActivity.this,AddItem.class);
        AddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddItem);
            }
        });
        Explore = findViewById(R.id.Explore);
        final Intent List = new Intent(MainActivity.this,RecipeList.class);
        Explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(List);
            }
        });
    }
}

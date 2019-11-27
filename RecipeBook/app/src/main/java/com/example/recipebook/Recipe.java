package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Recipe extends AppCompatActivity {

    DBHandler db;
    String Title;
    String Instructions;
    String Rating;
    TextView RecipeName;
    TextView InstructionsCard;
    RecipeGet Recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        RecipeName= findViewById(R.id.TitleCard);
        Title = getIntent().getStringExtra("RecipeTitle");
        RecipeName.setText(Title);
        InstructionsCard = findViewById(R.id.Instructions);
        db = new DBHandler(this,"RecipeList.db",null,1);
        Recipe = new RecipeGet();
        Recipe = db.findRecipe(Title);
        InstructionsCard.setText(Recipe.getInstructions());
    }
}

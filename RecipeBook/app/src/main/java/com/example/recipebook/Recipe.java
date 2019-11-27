package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class Recipe extends AppCompatActivity {

    DBHandler db;
    int ID;
    String Instructions;
    String Rating;
    TextView RecipeName;
    TextView InstructionsCard;
    RecipeGet Recipe;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ratingBar = findViewById(R.id.ratingBar);
        RecipeName= findViewById(R.id.TitleCard);
        ID = getIntent().getIntExtra("RecipeID",0);
        InstructionsCard = findViewById(R.id.Instructions);
        db = new DBHandler(this,"RecipeList.db",null,1);
        Recipe = new RecipeGet();
        Recipe= db.findRecipe(ID);
        RecipeName.setText(Recipe.getTitle());
        InstructionsCard.setText(Recipe.getInstructions());
        if(Recipe.getRatings()!=null){
            Rating = Recipe.getRatings();
            ratingBar.setRating(Float.parseFloat(Rating));
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Recipe.setRatings(String.valueOf(rating));
                db.setRating(Recipe);
            }
        });

    }
}

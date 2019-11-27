package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    EditText Title;
    EditText Instructions;
    EditText Rating;
    Button Save;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Title = findViewById(R.id.TitleCard);
        Instructions = findViewById(R.id.Instructions);
        Save = findViewById(R.id.SaveRecipe);
        db = new DBHandler(this,"RecipeList.db",null,1);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RecipeName = Title.getText().toString();
                String RecipeInstructions = Instructions.getText().toString();

                RecipeGet Recipe = new RecipeGet(RecipeName,RecipeInstructions);

                db.addRecipe(Recipe);
                Title.setText("");
                Instructions.setText("");
            }
        });
    }

}

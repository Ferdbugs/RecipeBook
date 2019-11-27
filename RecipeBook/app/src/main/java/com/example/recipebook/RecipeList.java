package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeList extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHandler db;
    ListView RecipeList;
    String FinalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        RecipeList = findViewById(R.id.ListRecipe);
        listItem = new ArrayList<>();
        db = new DBHandler(this,"RecipeList.db",null,1);
        getData();

        if (RecipeList!=null){
            RecipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Title = RecipeList.getItemAtPosition(position).toString();
                    char getID = Title.charAt(0);
                    int ID = getID - '0';
                    Intent RecipeIntent = new Intent(RecipeList.this,Recipe.class);
                    RecipeIntent.putExtra("RecipeID",ID);
                    startActivity(RecipeIntent);
                }
            });
        }
    }

    private void getData() {
        Cursor cursor = db.list();

        if (cursor.getCount()==0){
            Toast.makeText(this,"List Is Empty!",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                FinalList = cursor.getString(0) + " " + cursor.getString(1);
                listItem.add(FinalList);
            }
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem);
            RecipeList.setAdapter(adapter);
        }
    }

}

package com.example.recipebook;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "RecipeList.db";
    private static String TABLE_RECIPE = "Recipes";
    public static final String TABLE_CONTENT = "Recipes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RECIPENAME = "Title";
    public static final String COLUMN_INSTRUCTIONS = "Instructions";
    public static final String COLUMN_RATINGS= "Ratings";

    public DBHandler(Context context,String name,
                       SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPE_TABLE = "CREATE TABLE " +
                TABLE_RECIPE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_RECIPENAME
                + " TEXT," + COLUMN_INSTRUCTIONS + " TEXT," + COLUMN_RATINGS + " TEXT" +")";
        db.execSQL(CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(db);
    }

    public void addRecipe(RecipeGet Recipe) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPENAME, Recipe.getTitle());
        values.put(COLUMN_INSTRUCTIONS, Recipe.getInstructions());
        values.put(COLUMN_RATINGS, Recipe.getRatings());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RECIPE, null, values);

        db.close();
    }

    public RecipeGet findRecipe(String RecipeName) {
        String[] projection = {COLUMN_ID,
                COLUMN_RECIPENAME, COLUMN_RATINGS };

        String query = "Select * FROM " + TABLE_RECIPE + " WHERE " +
                COLUMN_RECIPENAME + " = \"" + RecipeName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        RecipeGet Recipe = new RecipeGet();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            Recipe.setID(Integer.parseInt(cursor.getString(0)));
            Recipe.setTitle(cursor.getString(1));
            Recipe.setInstructions(cursor.getString(2));
            Recipe.setRatings(cursor.getString(3));
            cursor.close();
        } else {
            Recipe = null;
        }

        db.close();

        return Recipe;
    }

    public boolean deleteRecipe(String RecipeName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_RECIPE + " WHERE " +
                COLUMN_RECIPENAME + " = \"" + RecipeName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        RecipeGet Recipe = new RecipeGet();

        if (cursor.moveToFirst()) {
            Recipe.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_RECIPE, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(Recipe.getID()) });
            cursor.close();
            result = true;
        }

        db.close();

        return result;
    }

    public Cursor list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPE;
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }
}

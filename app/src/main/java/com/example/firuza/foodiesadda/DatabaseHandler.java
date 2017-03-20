package com.example.firuza.foodiesadda;

/**
 * Created by firuza on 2/2/17.
 */

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.database.sqlite.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;

import android.database.DatabaseUtils;
import android.widget.TextView;


public class DatabaseHandler extends SQLiteOpenHelper {

//    DatabaseHandler mydb;
    public static final String DATABASE_NAME = "firuzaFoodiesAdda.db";
    public static final String TABLE_NAME = "tblRecipe";
    public static final String COLUMN_ID = "RID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_TIME = "PrepTime";
    public static final String COLUMN_PROCEDURE = "Procedure";
    public static final String COLUMN_ING_ID_PK = "IID";
    public static final String COLUMN_ING_NAME = "Name";

    public static final String COLUMN_FRID ="FRID";
    public static final String COLUMN_FIID ="FIID";
    public static final String COLUMN_Qty ="Quantity";

    //private HashMap hp;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME , null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tblRecipe " + "(RID integer primary key, Name text, PrepTime text, Procedure text)" );
        db.execSQL("create table if not exists tblIngMaster " + "(IID integer primary key, Name text)" );
        db.execSQL("create table if not exists tblIngNeeded " + "(FRID integer, FIID Integer, Quantity text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tblRecipe");
        db.execSQL("DROP TABLE IF EXISTS tblIngMaster");
        db.execSQL("DROP TABLE IF EXISTS tblIngNeeded");
        onCreate(db);
    }

    public boolean insertRecipe(String Name, String PrepTime, String Procedure) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("PrepTime", PrepTime);
        contentValues.put("Procedure", Procedure);
        db.insert("tblRecipe", null, contentValues);
        return true;
    }



    public ArrayList<String> getListofRecipes() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblRecipe", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String colname;
            colname = res.getString(res.getColumnIndex(COLUMN_NAME));
            array_list.add(colname);
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getData(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblRecipe where Name='"+title+"'", null );
        return res;
    }

    public boolean isIngredientPresent(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblIngMaster where Name='"+title+"'", null );

        if(res.getCount()==0)
            return false;
        else
            return true;
    }

    public void loadIngridientsMaster() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", "Salt");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Oil");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Turmeric Powder");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Chilly Powder");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Dhansak Masala");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Dhana Jeera Masala");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Sambhar Masala");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Onion");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Tomato");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Potato");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Drumsticks");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Beetroot");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Ladyfingers");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Cauliflower");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Green Peas");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Spinach");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Coriander Leaves");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Carrot");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Cucumber");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Ginger Garlic Paste");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Rye");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Rice");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Moong Dal");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "King Fish");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Prawns");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Radish");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Decicated coconut");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Green Chilly");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Poha");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Sugar");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Chicken");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Mutton");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Egg");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Rava");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Capsicum");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Pepper whole");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Clove");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Cinnamon Sticks");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Garlic");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Ginger");
        db.insert("tblIngMaster", null, contentValues);
        contentValues.put("Name", "Jeera");
        db.insert("tblIngMaster", null, contentValues);
    }

    public ArrayList<String> getListofIngredients() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblIngMaster", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String colname;
            colname = res.getString(res.getColumnIndex(COLUMN_ING_NAME));
            array_list.add(colname);
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getIngID(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblIngMaster where Name='"+title+"'", null );
        return res;
    }

    public boolean insertIngredientsNeeded(int RID, int IID, String Quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FRID", RID);
        contentValues.put("FIID", IID);
        contentValues.put("Quantity", Quantity);
        db.insert("tblIngNeeded", null, contentValues);
        return true;
    }


    public boolean deleteAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from tblRecipe");
        db.execSQL("delete from tblIngMaster");
        db.execSQL("delete from tblIngNeeded");
        return true;
    }

    public ArrayList<String> getIngsAndQtysOfRecipe(int RID) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();

        //This will return quantify from tblIngNeeded and the ingredient from tblIngMaster table based on the IDs given in tblIngNeeded table of the specific RID
        //Format will be IngName, Quantity
        Cursor res =  db.rawQuery( "select tblIngMaster.Name, tblIngNeeded.Quantity from tblIngNeeded join tblIngMaster on tblIngNeeded.FIID = tblIngMaster.IID where tblIngNeeded.FRID='"+RID+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(0) + ", " + res.getString(1) );
            res.moveToNext();
        }
        return array_list;
    }



/*
    public int getRecordCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from tblRecipe",null);
        res.moveToFirst();
        return (res.getCount());
    }*/

}



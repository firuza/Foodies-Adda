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
    //private HashMap hp;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tblRecipe " + "(RID integer primary key, Name text, PrepTime text, Procedure text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tblRecipe");
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

    public Cursor getIng(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tblIngMaster where IID="+id+"", null );
        return res;
    }

/*    public boolean deleteAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from tblRecipe");
        return true;
    }

    public int getRecordCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from tblRecipe",null);
        res.moveToFirst();
        return (res.getCount());
    }*/

}



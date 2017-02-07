package com.example.firuza.foodiesadda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddIngridient extends AppCompatActivity implements View.OnClickListener   {

    Spinner spn;
    DatabaseHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingridient);


        mydb = new DatabaseHandler(this);

        ArrayList array_list = mydb.getListofRecipes();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);


        spn = (Spinner)findViewById(R.id.spnIngridients);
        spn.setAdapter(arrayAdapter);


    }
    public void onClick(View view) {

    }
}

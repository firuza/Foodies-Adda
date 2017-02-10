package com.example.firuza.foodiesadda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.layout_height;
import static android.R.attr.layout_width;

public class AddIngridient extends AppCompatActivity implements View.OnClickListener   {

    DatabaseHandler mydb;
    LinearLayout llayout;
    Button btnAddIng;
    ArrayAdapter arrayAdapter;
    ScrollView scroll;
    int btnID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btnID=1000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingridient);

        llayout = (LinearLayout) findViewById(R.id.linearlayout);
        scroll = (ScrollView) findViewById(R.id.scroll);

        btnAddIng = (Button) findViewById(R.id.btnAddIng);
        btnAddIng.setOnClickListener(this);

        mydb = new DatabaseHandler(this);

        ArrayList array_list = mydb.getListofRecipes();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

    }
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnAddIng:
                addIngQty();

                break;

            default:
                break;
        }

    }

    void addIngQty() {
//        acMasterIngridients = (AutoCompleteTextView) findViewById(R.id.acMasterIngridients);
//        txtQty = (TextView) findViewById(R.id.txtQty);

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        final AutoCompleteTextView acMasterIngridients = new AutoCompleteTextView(this);
        acMasterIngridients.setAdapter(arrayAdapter);
        acMasterIngridients.setWidth(700);
        ll.addView(acMasterIngridients);

        final EditText txtQty = new EditText(this);
        txtQty.setWidth(500);
        ll.addView(txtQty);

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(70,10,10,10);
        ll.setLayoutParams(lparams);
        llayout.addView(ll);

    }

}

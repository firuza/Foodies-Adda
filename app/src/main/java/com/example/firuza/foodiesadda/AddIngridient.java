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
    ArrayAdapter arrayAdapter;
    ScrollView scroll;
    int layoutID=1001;
    int IngID=2001;
    int QtyID=3001;
    int btnAddID=4001;
    int btnRemoveID=5001;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingridient);

        //Scrollable layout on the activity
        scroll = (ScrollView) findViewById(R.id.scroll);

        //Main layout of scrollable layout
        llayout = (LinearLayout) findViewById(R.id.linearlayout);
        mydb = new DatabaseHandler(this);

        ArrayList array_list = mydb.getListofRecipes();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        addIngQty();
    }

    //Function to add auto complte text, edit text, and 2 buttons to the layout
    void addIngQty() {
        //Create a linear layout ll to comprise of ingredients edittext, quantity edittext, add button, and remove button
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setId(layoutID+i);

        //Create a autocomplete text view and load all ingredients
        final AutoCompleteTextView acMasterIngridients = new AutoCompleteTextView(this);
        acMasterIngridients.setId(IngID+i);
        acMasterIngridients.setAdapter(arrayAdapter);
        acMasterIngridients.setWidth(400);
        ll.addView(acMasterIngridients); //Adding to layout

        //Create a quantity edit text
        final EditText txtQty = new EditText(this);
        txtQty.setId(QtyID+i);
        txtQty.setWidth(300);
        ll.addView(txtQty); //Adding to layout

        //Create a add button to dynamically create items in layout
        final Button btnAdd = new Button(this);
        btnAdd.setText("+");
        btnAdd.setId(btnAddID+i);
        btnAdd.setOnClickListener(this);
        ll.addView(btnAdd);

        //Create a remove buttons to dynamically remove items from layout.
        if(i!=0) { //Atleast one set of item should be present in the layout
            final Button btnRemove = new Button(this);
            btnRemove.setText("X");
            btnRemove.setId(btnRemoveID + i);
            btnRemove.setOnClickListener(this);
            ll.addView(btnRemove);
        }

        i++; //Increment id iterator

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(5,10,10,10);
        ll.setLayoutParams(lparams);
        llayout.addView(ll); //Add the layout to the main layout in scrollable view

    }

    //Function to remove the layout (containing auto complete text view, edit text, and 2 buttons) from the main layout
    void removeIngQty(View view) {
        int viewID = view.getId(); //ID of the button that is clicked
        int layout;
        layout = viewID-4000; //Layout where the button is located
        llayout.removeView(llayout.findViewById(layout));
    }

    public void onClick(View view) {
        int viewID = view.getId(); //ID of the button that is clicke

        //Button Add has ID from 3001
        if(viewID >= btnAddID && viewID<btnAddID+1000) {
            addIngQty();
        }

        //Button Remove has ID from 4001
        if(viewID >= btnRemoveID && viewID<=btnRemoveID+1000) {
            removeIngQty(view);
        }
    }
}

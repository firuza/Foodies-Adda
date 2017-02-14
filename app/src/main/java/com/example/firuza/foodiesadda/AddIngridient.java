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
    TextView textView;
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

        textView = (TextView) findViewById(R.id.textView);

        llayout = (LinearLayout) findViewById(R.id.linearlayout);

        scroll = (ScrollView) findViewById(R.id.scroll);

        mydb = new DatabaseHandler(this);

        ArrayList array_list = mydb.getListofRecipes();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        addIngQty();
    }

    void addIngQty() {
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setId(layoutID+i);

        final AutoCompleteTextView acMasterIngridients = new AutoCompleteTextView(this);
        acMasterIngridients.setId(IngID+i);
        acMasterIngridients.setAdapter(arrayAdapter);
        acMasterIngridients.setWidth(400);

        ll.addView(acMasterIngridients);

        final EditText txtQty = new EditText(this);
        txtQty.setId(QtyID+i);
        txtQty.setWidth(300);
        ll.addView(txtQty);

        final Button btnAdd = new Button(this);
        btnAdd.setText("+");
        btnAdd.setId(btnAddID+i);
        btnAdd.setOnClickListener(this);
        ll.addView(btnAdd);

        if(i!=0) {
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
        llayout.addView(ll);

    }

    void removeIngQty(View view) {
        int viewID = view.getId();
        int layout;
        layout = viewID-4000;
        llayout.removeView(llayout.findViewById(layout));
        textView.setText("removing i = " + i + "  id=" + viewID + llayout.findViewById(layout));
    }

    public void onClick(View view) {
        int viewID = view.getId();

        //Button Add has ID from 3001
        if(viewID >= btnAddID && viewID<btnAddID+1000) {
            addIngQty();
            textView.setText("i = " + i + "  id=" + view.getId());
        }

        //Button Remove has ID from 4001
        if(viewID >= btnRemoveID && viewID<=btnRemoveID+1000) {
            removeIngQty(view);
        }
    }
}

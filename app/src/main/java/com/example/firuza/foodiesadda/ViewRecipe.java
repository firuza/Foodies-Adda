package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;

import android.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ViewRecipe extends AppCompatActivity  implements View.OnClickListener{

    DatabaseHandler mydb;
    TextView  txtProcedure, txtTitle, txtPreparationTime;
    ListView lstIngsQtys;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        txtProcedure = (TextView)findViewById(R.id.txtProcedure);
        txtTitle = (TextView)findViewById(R.id.txtTitle);
        txtPreparationTime = (TextView)findViewById(R.id.txtPreparationTime);

        Bundle extras = getIntent().getExtras();
        int RID = Integer.parseInt(extras.getString("rID")); //Recipe ID
        String title = extras.getString("RTitle");
        String time = extras.getString("RTime");
        String procedure = extras.getString("RProcedure");

        txtTitle.setText(title);
        txtPreparationTime.setText(time);
        txtProcedure.setText(procedure);

        mydb = new DatabaseHandler(this);
        ArrayList array_list = mydb.getIngsAndQtysOfRecipe(RID);
        int newsize = array_list.size() * 180;
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        lstIngsQtys = (ListView)findViewById(R.id.lstIngsQtys);
        lstIngsQtys.setAdapter(arrayAdapter);
        lstIngsQtys.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, newsize));

    }
    public void onClick(View view) {

    }

}

package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


public class ViewRecipe extends AppCompatActivity  implements View.OnClickListener{

    TextView txtProcedure;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        txtProcedure = (TextView)findViewById(R.id.txtProcedure);

        Intent intent = getIntent();
        if (intent.hasExtra("myextra")){
            txtProcedure.setText(intent.getStringExtra("myextra")+"!");
        }

    }
    public void onClick(View view) {

    }

}

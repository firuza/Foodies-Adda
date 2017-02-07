package com.example.firuza.foodiesadda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;

import android.app.AlertDialog;


public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler mydb;
    Button btnAddRecipe, btnClear;
    EditText editTextRTitle, editTextPrepTime, editTextProcedure;
    String insertRTitle, insertPrepTime, insertProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mydb = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);


        editTextRTitle = (EditText) findViewById(R.id.editTextRTitle);
        editTextPrepTime = (EditText) findViewById(R.id.editTextPrepTime);
        editTextProcedure = (EditText) findViewById(R.id.editTextProcedure);

        btnAddRecipe = (Button) findViewById(R.id.btnAddRecipe);
        btnAddRecipe.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    public void onClick(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(AddRecipe.this);

        switch (view.getId()) {

            case R.id.btnAddRecipe:
                insertRTitle = editTextRTitle.getText().toString();
                insertPrepTime = editTextPrepTime.getText().toString();
                insertProcedure = editTextProcedure.getText().toString();
                mydb.insertRecipe(insertRTitle,insertPrepTime,insertProcedure);
                alert.setTitle("Successful Insertion");
                alert.setMessage("The recipe has been successfully inserted");
                alert.setPositiveButton("OK",null);
                alert.show();
                break;

            case R.id.btnClear:
                editTextRTitle.setText("");
                editTextPrepTime.setText("");
                editTextProcedure.setText("");
                break;
/*
            case R.id.btnDeleteAll:
                mydb.deleteAll();
                alert.setTitle("Successful Delete");
                alert.setMessage("All records have been successfully deleted");
                alert.setPositiveButton("OK",null);
                alert.show();
                break;

            case R.id.btnRecordCount:
                int count = mydb.getRecordCount();
                txtID.setText("Records: " + count);
                break;
*/

            default:
                break;
        }


    }

}

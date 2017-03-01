 package com.example.firuza.foodiesadda;

import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;

import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;

import android.app.AlertDialog;

import java.util.ArrayList;


public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler mydb;
    Button btnAddRecipe, btnClear, btnAddIngridients;
    EditText editTextRTitle, editTextPrepTime, editTextProcedure;
    String insertRTitle, insertPrepTime, insertProcedure;
    TextView textViewTitle;
    ListView lstIngQty;
    int requestCode=1;
    ArrayList alIng, alQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mydb = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        lstIngQty = (ListView) findViewById(R.id.lstIngQty);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        editTextRTitle = (EditText) findViewById(R.id.editTextRTitle);
        editTextPrepTime = (EditText) findViewById(R.id.editTextPrepTime);
        editTextProcedure = (EditText) findViewById(R.id.editTextProcedure);

        btnAddRecipe = (Button) findViewById(R.id.btnAddRecipe);
        btnAddRecipe.setOnClickListener(this);

        btnAddIngridients = (Button) findViewById(R.id.btnAddIngridients);
        btnAddIngridients.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }


    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAddRecipe:
                String RID = insertRecipe();
                insertIngredientsQuantity(RID);
                break;

            case R.id.btnClear:
                editTextRTitle.setText("");
                editTextPrepTime.setText("");
                editTextProcedure.setText("");
                break;

            case R.id.btnAddIngridients:
                startActivityForResult(new Intent(getApplicationContext(), AddIngridient.class),requestCode);
                break;

            default:
                break;
        }
    }

    public String insertRecipe() {
        AlertDialog.Builder alert = new AlertDialog.Builder(AddRecipe.this);
        insertRTitle = editTextRTitle.getText().toString();
        insertPrepTime = editTextPrepTime.getText().toString();
        insertProcedure = editTextProcedure.getText().toString();
        mydb.insertRecipe(insertRTitle,insertPrepTime,insertProcedure);

        Cursor rs = mydb.getData(insertRTitle);
        rs.moveToFirst();
        String RID = rs.getString(rs.getColumnIndex(DatabaseHandler.COLUMN_ID));
        return RID;
    }

    public void insertIngredientsQuantity(String rID){
        AlertDialog.Builder alert = new AlertDialog.Builder(AddRecipe.this);

        //Iterate through all ingredients one by one
        for (int i=0; i<alIng.size(); i++) {
            //Get the Ingredient ID from the master ingredient list
            Cursor rs = mydb.getIngID(alIng.get(i).toString());
            rs.moveToFirst();
            int IID = Integer.parseInt(rs.getString(rs.getColumnIndex(DatabaseHandler.COLUMN_ING_ID_PK)));

            int RID = Integer.parseInt(rID); //Recipe ID

            //Insert RID, IID, and the quantity in 'Ingredients needed' table
            mydb.insertIngredientsNeeded(RID,IID,alQty.get(i).toString());
        }
        alert.setTitle("Successful Insertion");
        alert.setMessage("Recipe inserted successfully");
        alert.setPositiveButton("OK",null);
        alert.show();
    }

    //Data (Ingredient and Quantity) returned by 'Add Ingredients' activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {

                alIng = data.getStringArrayListExtra("strArrayIng");
                alQty = data.getStringArrayListExtra("strArrayQty");
		
    	        ArrayList<String> IngQty = new ArrayList<String>();
		        for (int i=0; i<alIng.size(); i++) {
        			IngQty.add(alIng.get(i) + ", " + alQty.get(i));
		        }

                ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, IngQty);
                lstIngQty.setAdapter(arrayAdapter);
            }
    }
}

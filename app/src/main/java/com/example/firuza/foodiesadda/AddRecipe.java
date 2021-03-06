 package com.example.firuza.foodiesadda;

import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;

import java.util.ArrayList;


public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler mydb;
    Button btnAddRecipe, btnClear, btnAddIngridients;
    EditText editTextRTitle, editTextPrepTime, editTextProcedure;
    String insertRTitle, insertPrepTime, insertProcedure;
    TextView textViewTitle;
    ListView lstIngQty;
    int requestCode=1;
    ArrayList<String> alIng, alQty;

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
        alIng = new ArrayList<String>();
        alQty = new ArrayList<String>();
        alIng.add("Ingredients");
        alQty.add("Qty");

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }


    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAddRecipe:
                boolean validationPassed=true;
                insertRTitle = editTextRTitle.getText().toString().trim();
                insertPrepTime = editTextPrepTime.getText().toString().trim();
                insertProcedure = editTextProcedure.getText().toString().trim();

                if(TextUtils.isEmpty(insertRTitle)) {
                    editTextRTitle.setError("Title cannot be blank");
                    validationPassed=false;
                }
                if(TextUtils.isEmpty(insertPrepTime)) {
                    editTextPrepTime.setError("Preparation time cannot be blank");
                    validationPassed=false;
                }
                if(TextUtils.isEmpty(insertProcedure)) {
                    editTextProcedure.setError("Procedure cannot be blank");
                    validationPassed=false;
                }
                if(lstIngQty.getCount()==0){
                    Toast.makeText( getApplicationContext(), "Ingredient(s) needed", Toast.LENGTH_SHORT).show();
                    validationPassed=false;
                }

                if(validationPassed==true) {
                    String RID = insertRecipe();
                    insertIngredientsQuantity(RID);
                }
                break;

            case R.id.btnClear:
                editTextRTitle.setText("");
                editTextPrepTime.setText("");
                editTextProcedure.setText("");
                break;

            case R.id.btnAddIngridients:

                Intent data = new Intent(getApplicationContext(), AddIngridient.class);

                //Load the ingredients which were already selected by the user and wants to add/remove/edit it
                Bundle extras = new Bundle();
                extras.putStringArrayList("initialIng",alIng);
                extras.putStringArrayList("initialQty",alQty);

                data.putExtras(extras);
                startActivityForResult(data,requestCode);
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

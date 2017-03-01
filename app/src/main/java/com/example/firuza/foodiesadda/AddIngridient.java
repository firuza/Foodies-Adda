package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Arrays;

public class AddIngridient extends AppCompatActivity implements View.OnClickListener   {

    DatabaseHandler mydb;
    LinearLayout llayout;
    ArrayAdapter arrayAdapter;
    ScrollView scroll;
    Button btnIncludeIngredients;
    int layoutID=1001;
    int IngID=2001;
    int QtyID=3001;
    int btnAddID=4001;
    int btnRemoveID=5001;
    int i=0;
    EditText txtQty[] = new EditText[999];
    AutoCompleteTextView acMasterIngridients[] = new AutoCompleteTextView[999];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingridient);

        btnIncludeIngredients = (Button) findViewById(R.id.btnIncludeIngredients);
        btnIncludeIngredients.setOnClickListener(this);

        //Scrollable layout on the activity
        scroll = (ScrollView) findViewById(R.id.scroll);

        //Main layout of scrollable layout
        llayout = (LinearLayout) findViewById(R.id.linearlayout);
        mydb = new DatabaseHandler(this);

       // mydb.LoadIngridientsMaster();
        ArrayList array_list = mydb.getListofIngredients();
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
        acMasterIngridients[i] = new AutoCompleteTextView(this);
        acMasterIngridients[i].setId(IngID+i);
        acMasterIngridients[i].setAdapter(arrayAdapter);
        acMasterIngridients[i].setWidth(400);
        ll.addView(acMasterIngridients[i]); //Adding to layout

        txtQty[i] = new EditText(this);
        txtQty[i].setId(QtyID+i);
        txtQty[i].setWidth(300);
        ll.addView(txtQty[i]); //Adding to layout

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
        acMasterIngridients[viewID-5001].setText("XX");
        txtQty[viewID-5001].setText("XX");
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

        if (viewID==R.id.btnIncludeIngredients) {
            //Extract all ingredients and quantity in string array list
            AlertDialog.Builder alert = new AlertDialog.Builder(AddIngridient.this);
//            String[] strArrayQty;
//            strArrayQty = new String [100];

            ArrayList<String> strArrayIng = new ArrayList<String>();
            ArrayList<String> strArrayQty = new ArrayList<String>();

            int itemCount=0, actualCount=0;
            boolean duplicatePresent=false, recordInDB=true, isEmpty=false;

            //Check values of all items present in the layout
            while (itemCount < i) {
                String tempIng="", tempQty="";
                tempIng = acMasterIngridients[itemCount].getText().toString();
                tempQty = txtQty[itemCount].getText().toString();

                //Check whether the item was removed or not
                if(!(tempIng.equalsIgnoreCase("XX"))) {

                    if( tempIng.isEmpty() || tempQty.isEmpty()) {
                        isEmpty=true;
                        break;
                    }

                    //Check whether the list contains duplicate items
                    if(strArrayIng.contains(tempIng)){
                        duplicatePresent=true;
                        break;
                    }

                    //Check whether the ingredients written are present in the table
                    if(!(mydb.isIngredientPresent(tempIng))) {
                        recordInDB=false;
                        break;
                    }

                    strArrayIng.add(tempIng);
                    strArrayQty.add(tempQty);
                    actualCount++;
                }
                itemCount++;
            } //End of while

            if(isEmpty) {
                alert.setTitle("Fields cannot be empty");
                alert.setMessage("Ingredients and quantity fields cannot be empty");
                alert.setPositiveButton("OK", null);
                alert.show();
            }

            if(duplicatePresent) {
                alert.setTitle("Contains Duplicates");
                alert.setMessage("Your list contains duplicate ingredients item. Please remove them");
                alert.setPositiveButton("OK", null);
                alert.show();
            }

            if(!recordInDB) {
                alert.setTitle("Ingredient not present");
                alert.setMessage("One of the Ingredients included in your list is not in the master list. Please rectify it.");
                alert.setPositiveButton("OK", null);
                alert.show();
            }

            if (!duplicatePresent && recordInDB && !isEmpty) {
//                alert.setTitle("Successful");
//                alert.setMessage(actualCount + "ingredients included successfully");
//                alert.setPositiveButton("OK", null);
//                alert.show();

                Intent data = new Intent();
                data.putStringArrayListExtra("strArrayIng",strArrayIng);
                data.putStringArrayListExtra("strArrayQty",strArrayQty);
                data.putExtra("prevActivity","ADDIng");
                setResult(RESULT_OK,data);
                finish();//Going back to add recipe and retaining the values in that activity
            }
        }
    }
}


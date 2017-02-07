package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListOfRecipes extends AppCompatActivity implements View.OnClickListener   {

    DatabaseHandler mydb;
    ViewRecipe vr;
    ListView obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_recipes);

        mydb = new DatabaseHandler(this);

        ArrayList array_list = mydb.getListofRecipes();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.recipeList);
        obj.setAdapter(arrayAdapter);
        obj.setClickable(true);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

                String valueProcedure;
                String title=((TextView) view).getText().toString();

                Cursor rs = mydb.getData(title);
                rs.moveToFirst();

                valueProcedure = rs.getString(rs.getColumnIndex(DatabaseHandler.COLUMN_PROCEDURE));
                Intent intent1 = new Intent(getApplicationContext(), ViewRecipe.class);

                intent1.putExtra("myextra",(CharSequence)valueProcedure);

                startActivity(intent1);


            }
        });
    }


    public void onClick(View view)
    {
  //      startActivity(new Intent("com.example.firuza.foodiesadda.ViewRecipe"));


//        switch (view.getId()) {
//
//            case R.id.button:
//
//                Intent intent1 = new Intent(getApplicationContext(), AddRecipe.class);
//                startActivity(intent1);
//                break;

//            case R.id.btnViewRecipes:
//                Intent intent2 = new Intent(getApplicationContext(), ListOfRecipes.class);
//                startActivity(intent2);
//                break;

//            default:
//                break;
//        }
    }

}








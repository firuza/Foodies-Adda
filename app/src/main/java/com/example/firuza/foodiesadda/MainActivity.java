package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    DatabaseHandler mydb;
    Button btnAddRecipe, btnViewRecipes, btnRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddRecipe = (Button)findViewById(R.id.btnAddRecipe);
        btnAddRecipe.setOnClickListener(this);

        btnViewRecipes = (Button)findViewById(R.id.btnViewRecipes);
        btnViewRecipes.setOnClickListener(this);

        btnRestore = (Button)findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(this);
    }
    public void onClick(View view)
    {
        switch (view.getId()) {

            case R.id.btnAddRecipe:
                Intent intent1 = new Intent(getApplicationContext(), AddRecipe.class);
                Bundle extras = new Bundle();
                extras.putString("prevActivity","Main");
                intent1.putExtras(extras);
                startActivity(intent1);
                break;

            case R.id.btnViewRecipes:
                Intent intent2 = new Intent(getApplicationContext(), ListOfRecipes.class);
                startActivity(intent2);
                break;

            case R.id.btnRestore:
                mydb.deleteAll();
                mydb.loadIngridientsMaster();
                break;

            default:
                break;
        }
    }
}

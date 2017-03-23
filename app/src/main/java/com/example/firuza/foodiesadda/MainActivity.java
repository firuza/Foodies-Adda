package com.example.firuza.foodiesadda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    DatabaseHandler mydb;
    Button btnAddRecipe, btnViewRecipes, btnRestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAddRecipe = (Button)findViewById(R.id.btnAddRecipe);
        btnAddRecipe.setOnClickListener(this);

        btnViewRecipes = (Button)findViewById(R.id.btnViewRecipes);
        btnViewRecipes.setOnClickListener(this);

        btnRestore = (Button)findViewById(R.id.btnRestore);
        btnRestore.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_FAQs) {
            Toast.makeText( getApplicationContext(), "FAQs clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_About) {
            Toast.makeText( getApplicationContext(), "About us clicked", Toast.LENGTH_LONG).show();
            Intent intentAbout = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intentAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    DialogInterface.OnClickListener dialogYesNo = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int YesOrNo) {
            switch (YesOrNo){
                case DialogInterface.BUTTON_POSITIVE:

                    mydb.deleteAll();
                    mydb.loadIngridientsMaster();

                    mydb.insertRecipe("Poha","20 min","Chop onions finely.\nSaute in oil till golden brown.\nAdd Rye, turmeric powder, salt.\nMix well.\nAdd boiled potatoes and boiled green peas.\nStir well.\nAdd poha.\nStir till the poha becomes light yellow.\nAdd sugar and mix well.\nCook for 3 minutes\nGarnish with coriander leaves.\nServe Hot!");
                    mydb.insertIngredientsNeeded(1,8,"2");
                    mydb.insertIngredientsNeeded(1,21,"1/2 tea spoon");
                    mydb.insertIngredientsNeeded(1,3,"3/4 tea spoon");
                    mydb.insertIngredientsNeeded(1,1,"1 tea spoon");
                    mydb.insertIngredientsNeeded(1,10,"1");
                    mydb.insertIngredientsNeeded(1,15,"50 gms");
                    mydb.insertIngredientsNeeded(1,29,"300 gms");
                    mydb.insertIngredientsNeeded(1,30,"1 tea spoon");
                    mydb.insertIngredientsNeeded(1,17,"20 gms");

                    mydb.insertRecipe("Chicken Cutlet","2 Hours","Wash chicken.\nCut small pieces.\nMarinate with salt and ginger garlic paste.\nIn a vessel put whole pepper, clove, cinnamon sticks, and heat chicken without water.\nAdd little water and boil.\nDrain out the soup.\n\nShred the chicken.\n\nBoil potatoes and mash them.\nMix dhansak masala, turmeric powder, chilli powder, and corriander leaves with the potatoes.\nMix shredded chicken with potatoes.\n\nMake small patties.\n\nPut egg in another vessel and flip thoroughly.\n\nDip patties in egg and cover them with rava.\nor\nCover them with rava and then dip patties in egg.\n\nFry the patties in oil till golden brown.");
                    mydb.insertIngredientsNeeded(2,31,"900 gms");
                    mydb.insertIngredientsNeeded(2,1,"2 tea spoons");
                    mydb.insertIngredientsNeeded(2,36,"3 pods");
                    mydb.insertIngredientsNeeded(2,37,"3 pods");
                    mydb.insertIngredientsNeeded(2,38,"1 stick");
                    mydb.insertIngredientsNeeded(2,20,"3 tea spoons");
                    mydb.insertIngredientsNeeded(2,10,"5");
                    mydb.insertIngredientsNeeded(2,3,"1 tea spoon");
                    mydb.insertIngredientsNeeded(2,4,"1 tea spoon");
                    mydb.insertIngredientsNeeded(2,5,"2 tea spoons");
                    mydb.insertIngredientsNeeded(2,17,"30 gms");
                    mydb.insertIngredientsNeeded(2,33,"4");
                    mydb.insertIngredientsNeeded(2,34,"150 gms");
                    mydb.insertIngredientsNeeded(2,2,"150 ml");

                    Toast.makeText( getApplicationContext(), "Restored", Toast.LENGTH_LONG).show();

                    break;
            }
        }
    };


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
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restore");
                alert.setMessage("Restore will delete all the recipes and ingredients that you have added. \nOnly the default receipes and ingredients will remain. \nAre you sure, you want to restore?");
                alert.setPositiveButton("Yes", dialogYesNo);
                alert.setNegativeButton("No",null);
                alert.show();
                break;

            default:
                break;
        }
    }
}

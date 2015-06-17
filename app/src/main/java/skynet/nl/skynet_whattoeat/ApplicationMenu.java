package skynet.nl.skynet_whattoeat;

import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ApplicationMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_menu);
        ActionBar bar = getActionBar();

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();
        // Convert currentUser into String
        String cUser = currentUser.getUsername().toString();
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + cUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application_menu, menu);
        return true;
    }

    @Override

    // menu rop right
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.a1:
                createRecipe();
                return true;
            case R.id.a2:
                ingredient_list();
                return true;
            case R.id.a3:
                ParseUser.logOut();
                startActivity(new Intent(ApplicationMenu.this, Login.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // ingredient list start activity
    public void ingredient_list(){
        startActivity(new Intent(ApplicationMenu.this,IngredientList.class));
    }

    // settings start activity
    public void setting(){
        startActivity(new Intent(ApplicationMenu.this,Settings.class));
    }

    // New intent search recipes
    public void searchRecipe(View view){
       startActivity(new Intent(ApplicationMenu.this, SearchRecipe.class));
    }

    // New intent view your recipes
    public void viewRecipe(View view){
        startActivity(new Intent(ApplicationMenu.this, MyRecipesActivity.class));
    }

    //New intent create recipe
    public void createRecipe(){
        startActivity(new Intent(ApplicationMenu.this, CreateRecipe.class));
    }
}
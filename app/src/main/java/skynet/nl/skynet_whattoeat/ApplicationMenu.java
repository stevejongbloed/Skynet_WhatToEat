package skynet.nl.skynet_whattoeat;

import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApplicationMenu extends Activity {

    private static ArrayList<Recipe> recipes = template();

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

        // Set the currentUser String into the TextView
        txtuser.setText("You are logged in as " + cUser);

        populateRecipeList();
        populateListView();
        itemClickListen();
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
            case R.id.search:
                searchRecipe();
                return true;
            case R.id.a1:
                createRecipe();
                return true;
            case R.id.a2:
                ingredient_list();
                return true;
            case R.id.a3:
                ParseUser.logOut();
                startActivity(new Intent(ApplicationMenu.this, LoginSignupActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // ingredient list start activity
    public void ingredient_list(){
        startActivity(new Intent(ApplicationMenu.this,IngredientList.class));
    }

    // New intent search recipes
    public void searchRecipe(){
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

    private static ArrayList<Recipe> template()
    {
        ArrayList<Recipe> newArray = new ArrayList<>();
        return newArray;
    }

    private void populateRecipeList()
    {
        recipes = Database.getAllRecipes();
    }

    private void populateListView()
    {
        ArrayAdapter<Recipe> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.myRecipes_ListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Recipe>
    {
        public MyListAdapter()
        {
            super(ApplicationMenu.this, R.layout.recipe_view, recipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View recipeView = convertView;
            if(recipeView == null)
            {
                recipeView = getLayoutInflater().inflate(R.layout.recipe_view, parent, false);
            }

            Recipe currentRecipe = recipes.get(position);

            TextView nameText = (TextView) recipeView.findViewById(R.id.searchRecipe_txtName);
            nameText.setText(currentRecipe.getName());

            TextView durationText = (TextView) recipeView.findViewById(R.id.searchRecipe_txtDuration);
            durationText.setText(currentRecipe.getDuration().toString());

            return recipeView;
        }
    }

    private void itemClickListen()
    {
        final ListView list = (ListView) findViewById(R.id.myRecipes_ListView);
        list.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {

                Intent intent = new Intent(ApplicationMenu.this, CreateRecipe.class);

                Bundle b = new Bundle();
                b.putInt("key", position + 1); //Your id
                intent.putExtras(b);

                startActivity(intent);
            }
        });

    }

    public void createRecipe(View v)
    {
        Intent intent = new Intent(ApplicationMenu.this, CreateRecipe.class);

        Bundle b = new Bundle();
        b.putInt("key", 999); //Your id
        intent.putExtras(b);

        startActivity(intent);
    }

    public static ArrayList<Recipe> getRecipes()
    {
        return recipes;
    }

}
package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Oteken on 07-06-15.
 */
public class MyRecipesActivity extends Activity {

    private static ArrayList<Recipe> recipes = template();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        populateRecipeList();
        populateListView();
        itemClickListen();
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
            super(MyRecipesActivity.this, R.layout.recipe_view, recipes);
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

                Intent intent = new Intent(MyRecipesActivity.this, CreateRecipe.class);

                Bundle b = new Bundle();
                b.putInt("key", position + 1); //Your id
                intent.putExtras(b);

                startActivity(intent);
            }
        });

    }

    public void createRecipe(View v)
    {
        Intent intent = new Intent(MyRecipesActivity.this, CreateRecipe.class);

        Bundle b = new Bundle();
        b.putInt("key", 999); //Your id
        intent.putExtras(b);

        startActivity(intent);
    }

    public static ArrayList<Recipe> getRecipes()
    {
        return recipes;
    }

    public void back(View v)
    {
        Intent intent = new Intent(MyRecipesActivity.this, ApplicationMenu.class);
        startActivity(intent);
    }
}

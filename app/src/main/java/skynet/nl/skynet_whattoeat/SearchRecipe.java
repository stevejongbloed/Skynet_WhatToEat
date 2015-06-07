package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Oteken on 06-06-15.
 */
public class SearchRecipe extends Activity {

    private ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private static Recipe chosenRecipe;
    private String searchFor = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        allRecipes = Database.getAllRecipes();

        populateRecipeList();
        populateListView();
        itemClickListen();
        searchBarListen();

    }

   /* private void addHardCode()
    {
        Recipe recipe = new Recipe();
        recipe.setName("Spaghetti");
        recipe.setDuration(20.0);
        ArrayList<Step> steps = new ArrayList<Step>();
        Step step = new Step();
        step.setTitle("Prepare");
        step.setText("Lets start with step 1");
        step.setDuration(1.0);
        Ingredient ingredient = new Ingredient(1, new Unit(1, 2.0));
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(ingredient);
        step.setIngredients(ingredients);
        step.setStepNumber(2);
        steps.add(step);
        recipe.setSteps(steps);
        Ingredient ingredient2 = new Ingredient(2, new Unit(2, 3.0));
        Ingredient ingredient3 = new Ingredient(3, new Unit(0, 4.0));
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        recipe.setIngredients(ingredients);
        allRecipes.add(recipe);
    }*/

    private void searchBarListen()
    {

        final EditText searchBar = (EditText) findViewById(R.id.searchRecipe_fieldSearch);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchFor = searchBar.getText().toString();
                populateRecipeList();
            }
        });
    }

    private void populateRecipeList()
    {
        recipes.clear();
        if(!(searchFor.equals("")))
        {
            if(allRecipes != null)
            {
                for (Recipe recipe : allRecipes)
                {
                    if (recipe.getName().toLowerCase().contains(searchFor.toLowerCase()))
                    {
                        recipes.add(recipe);
                    }
                }
            }
        }
        populateListView();

    }

    private void refresh()
    {
        populateListView();
    }

    private void populateListView()
    {
        ArrayAdapter<Recipe> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.searchRecipe_ListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Recipe>
    {
        public MyListAdapter()
        {
            super(SearchRecipe.this, R.layout.recipe_view, recipes);
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
        final ListView list = (ListView) findViewById(R.id.searchRecipe_ListView);
        list.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {

            chosenRecipe = recipes.get(position);

            Intent intent = new Intent(SearchRecipe.this, ViewRecipe.class);
            startActivity(intent);

            }
        });

    }

    public static Recipe getChosenRecipe()
    {
        return chosenRecipe;
    }
}

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oteken on 06-06-15.
 */
public class SearchRecipe extends Activity {

    private ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
    private ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
    private ArrayList<Recipe> shownRecipes = new ArrayList<Recipe>();
    private static Recipe chosenRecipe;
    private String searchFor = "";
    private List<String> category;
    private Boolean filterApplied;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        allRecipes = Database.getAllRecipes();

        // Intent is only filled when activity is activated from IngredientFormActivity class.
        if(getIntent().getExtras() != null)
        {
            shownRecipes = getMatchingRecipes();
            categoryFilter();
            populateListView();
            itemClickListen();
            searchBarListen();

            // Invisible button on xml to stop form changes to visible.
            Button stopButton = (Button) findViewById(R.id.searchRecipe_buttonStopIngredientForm);
            stopButton.setVisibility(View.VISIBLE);
        } else
        {
            populateRecipeList();
            categoryFilter();
            populateListView();
            itemClickListen();
            searchBarListen();
        }

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
        shownRecipes.clear();
        if(!(searchFor.equals("")))
        {
            if(allRecipes != null)
            {
                for (Recipe recipe : allRecipes)
                {
                    if (recipe.getName().toLowerCase().contains(searchFor.toLowerCase()))
                    {
                        shownRecipes.add(recipe);
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
            super(SearchRecipe.this, R.layout.recipe_view, shownRecipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View recipeView = convertView;
            if(recipeView == null)
            {
                recipeView = getLayoutInflater().inflate(R.layout.recipe_view, parent, false);
            }

            Recipe currentRecipe = shownRecipes.get(position);

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

            if(filterApplied)
            {
                chosenRecipe = filteredRecipes.get(position);
            }else
            {
                chosenRecipe = shownRecipes.get(position);
            }

            Intent intent = new Intent(SearchRecipe.this, ViewRecipe.class);
            startActivity(intent);

            }
        });

    }

    private void populateListViewFiltered()
    {
        ArrayAdapter<Recipe> adapter = new MyListAdapter2();
        ListView list = (ListView) findViewById(R.id.searchRecipe_ListView);
        list.setAdapter(adapter);
        filterApplied = true;
    }

    private class MyListAdapter2 extends ArrayAdapter<Recipe>
    {
        public MyListAdapter2()
        {
            super(SearchRecipe.this, R.layout.recipe_view, filteredRecipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View recipeView = convertView;
            if(recipeView == null)
            {
                recipeView = getLayoutInflater().inflate(R.layout.recipe_view, parent, false);
            }

            Recipe currentRecipe = filteredRecipes.get(position);

            TextView nameText = (TextView) recipeView.findViewById(R.id.searchRecipe_txtName);
            nameText.setText(currentRecipe.getName());

            TextView durationText = (TextView) recipeView.findViewById(R.id.searchRecipe_txtDuration);
            durationText.setText(currentRecipe.getDuration().toString());

            return recipeView;
        }
    }

    public static Recipe getChosenRecipe()
    {
        return chosenRecipe;
    }

    public void newIngredientForm(View v)
    {
        Intent intent = new Intent(SearchRecipe.this, IngredientFormActivity.class);
        startActivity(intent);
    }

    public void stopIngredientForm(View v)
    {

        // Change stop button to invisible.
        Button stopButton = (Button) findViewById(R.id.searchRecipe_buttonStopIngredientForm);
        stopButton.setVisibility(View.INVISIBLE);

        populateRecipeList();
        populateListView();
        itemClickListen();
        searchBarListen();
    }

    private ArrayList<Recipe> getMatchingRecipes()
    {
        ArrayList<Recipe> matches = new ArrayList<Recipe>();
        matches = IngredientFormActivity.matchingRecipes;
        return matches;
    }

    private void applyCategoryFilter()
    {

        ArrayList<Recipe> newRecipes = new ArrayList<Recipe>();
        Spinner dropdownCategories = (Spinner) findViewById(R.id.searchRecipe_dropdownCategories);

        // If category is on default, no filtering is applied.
        if(!(((String)dropdownCategories.getSelectedItem()).equals("Default")))
        {

            for (int i = 0; i < shownRecipes.size(); i++)
            {
                String currentRecipeCategory = shownRecipes.get(i).getCategory().getName();
                if (currentRecipeCategory.equals(dropdownCategories.getSelectedItem()))
                {
                    newRecipes.add(shownRecipes.get(i));
                }
            }

            filteredRecipes = newRecipes;
            populateListViewFiltered();
            itemClickListen();
            searchBarListen();
        } else
        {
            populateListView();
            itemClickListen();
            searchBarListen();
            filterApplied = false;
        }
    }

    private void categoryFilter()
    {
        // Create category spinner.
        Spinner dropdownCategories = (Spinner) findViewById(R.id.searchRecipe_dropdownCategories);
        List<String> Categories = Database.getAllCategories();
        Categories.add("Default");
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(SearchRecipe.this, android.R.layout.simple_spinner_item, Categories);

        dropdownCategories.setAdapter(adapterCategories);

        // Set category.
        dropdownCategories.setSelection(Categories.size() - 1);

        dropdownCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                applyCategoryFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void back(View v)
    {
        Intent intent = new Intent(SearchRecipe.this, ApplicationMenu.class);
        startActivity(intent);
    }
}

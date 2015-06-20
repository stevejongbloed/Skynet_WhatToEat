package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CreateRecipe extends Activity implements AdapterView.OnItemSelectedListener{

    private List<String> allCategories = Database.getAllCategories();
    Spinner spinner;
    private static Recipe recipe = template();
    private static int chosenRecipe = -1;
    private int editingRecipe = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);


        setPage();

        populateIngredientView();
        populateStepView();
        itemClickListen();

    }

    private static Recipe template()
    {
        Recipe recipe = new Recipe();
        recipe.setName("");
        recipe.setDuration(0.0);
        recipe.setCategory(new Category(1));
        recipe.setStepCount(0);
        recipe.setIngredients(new ArrayList<Ingredient>());
        recipe.setSteps(new ArrayList<Step>());
        return recipe;
    }

    public void setPage()
    {
        editingRecipe = -1;

        if(getIntent().getExtras() != null)
        {
            Bundle b = getIntent().getExtras();
            int value = b.getInt("key");

            // Intent 999 is given when this activity is started to create a new recipe.
            if(value != 999)
            {
                if (value != 0)
                {
                    // Intent 1001 is given when this activity is started from adding a new step;
                    if(value != 1001)
                    {
                        recipe = ApplicationMenu.getRecipes().get(value - 1);
                        editingRecipe = value - 1;
                        chosenRecipe = value - 1;
                    }
                }
                if (editingRecipe == -1)
                {
                    if (getIntent().getBooleanExtra("EXTRA_MESSAGE", true) == true && value != 1001) {
                        recipe.setIngredients(AddIngredientActivity.getNewIngredientsList());
                    } else if (value == 1001)
                    {
                        recipe.getSteps().add(AddStepActivity.getStep());
                        recipe.setStepCount(recipe.getStepCount() + 1);
                    }
                }
            }else
            {
                recipe = template();
                chosenRecipe = -1;
            }
        }



        // Create category spinner.
        Spinner dropdownCategories = (Spinner) findViewById(R.id.createRecipe_dropdownCategories);
        List<String> Categories = Database.getAllCategories();
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(CreateRecipe.this, android.R.layout.simple_spinner_item, Categories);
        dropdownCategories.setAdapter(adapterCategories);

        // Set recipe category.
        dropdownCategories.setSelection(recipe.getCategory().getId());

        // Set recipe name.
        EditText recipeName = (EditText) findViewById(R.id.createRecipe_fieldName);
        recipeName.setText(recipe.getName());
        // Set recipe duration.
        EditText recipeDuration = (EditText) findViewById(R.id.createRecipe_fieldDuration);
        recipeDuration.setText(recipe.getDuration().toString());
    }

    public void goBack(View view){
        this.finish();
    }

    private void populateIngredientView() {
        ArrayAdapter<Ingredient> adapter = new MyIngredientAdapter();
        ListView list = (ListView) findViewById(R.id.createRecipe_ListViewIngredient);
        list.setAdapter(adapter);
    }

    private class MyIngredientAdapter extends ArrayAdapter<Ingredient>
    {
        public MyIngredientAdapter()
        {
            super(CreateRecipe.this, R.layout.ingredient_view, recipe.getIngredients());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View ingredientView = convertView;
            if(ingredientView == null)
            {
                ingredientView = getLayoutInflater().inflate(R.layout.ingredient_view, parent, false);
            }

            Ingredient currentIngredient = recipe.getIngredients().get(position);

            TextView nameText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtName);
            nameText.setText(currentIngredient.getName());

            TextView unitText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtUnit);
            unitText.setText(currentIngredient.getUnit().getMeasurementUnit());

            TextView amountText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtAmount);
            amountText.setText(currentIngredient.getUnit().getAmount());

            return ingredientView;
        }

    }

    private void populateStepView()
    {
        ArrayAdapter<Step> adapter = new MyStepAdapter();
        ListView list = (ListView) findViewById(R.id.createRecipe_ListViewSteps);
        list.setAdapter(adapter);
    }

    private class MyStepAdapter extends ArrayAdapter<Step>
    {
        public MyStepAdapter()
        {
            super(CreateRecipe.this, R.layout.step_view, recipe.getSteps());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View stepView = convertView;
            if(stepView == null)
            {
                stepView = getLayoutInflater().inflate(R.layout.step_view, parent, false);
            }

            Step currentStep = recipe.getSteps().get(position);

            TextView stepTitle = (TextView) stepView.findViewById(R.id.step_txtTitle);
            stepTitle.setText(currentStep.getTitle());

            TextView stepCount = (TextView) stepView.findViewById(R.id.step_txtNumber);
            stepCount.setText(currentStep.getStepNumber() + "");

            return stepView;
        }

    }

    private void itemClickListen() {
        final ListView list = (ListView) findViewById(R.id.createRecipe_ListViewSteps);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {

                Intent intent = new Intent(CreateRecipe.this, AddStepActivity.class);

                Bundle b = new Bundle();
                b.putInt("key", position); //Your id
                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static Recipe getRecipe()
    {
        return recipe;
    }

    public void addIngredients(View v)
    {
        saveRecipeVariables();

        Intent intent = new Intent(CreateRecipe.this, AddIngredientActivity.class);
        intent.putExtra("EXTRA_MESSAGE", true);
        startActivity(intent);
    }

    public void addStep(View v)
    {
        saveRecipeVariables();

        Intent intent = new Intent(CreateRecipe.this, AddStepActivity.class);
        startActivity(intent);
    }

    public void saveRecipe(View v)
    {
        saveRecipeVariables();

        // If user is creating a new recipe, this if statement will run.
        if (chosenRecipe == -1)
        {
            Database.allRecipes.add(recipe);
        }
        Intent intent = new Intent(CreateRecipe.this, ApplicationMenu.class);
        startActivity(intent);
    }

    public void deleteRecipe(View v)
    {
        if(chosenRecipe != -1)
        {
            Database.allRecipes.remove(chosenRecipe);
        }
        Intent intent = new Intent(CreateRecipe.this, ApplicationMenu.class);
        startActivity(intent);
    }

    private void saveRecipeVariables()
    {
        // Saving recipe name.
        EditText recipeName = (EditText) findViewById(R.id.createRecipe_fieldName);
        recipe.setName(recipeName.getText().toString());
        // Saving recipe duration.
        EditText recipeDuration = (EditText) findViewById(R.id.createRecipe_fieldDuration);
        recipe.setDuration(Double.valueOf(recipeDuration.getText().toString()));
        // Saving recipe category
        Spinner dropdownCategories = (Spinner) findViewById(R.id.createRecipe_dropdownCategories);
        Integer categoryId = allCategories.indexOf(dropdownCategories.getSelectedItem());
        Category category = new Category(categoryId);
        recipe.setCategory(category);
    }

    public static List<Ingredient> getIngredients()
    {
        return recipe.getIngredients();
    }

}

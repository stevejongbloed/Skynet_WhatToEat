package skynet.nl.skynet_whattoeat;

import android.app.Activity;
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

import java.io.IOException;
import java.util.ArrayList;


public class ViewRecipe extends Activity {

   // public ViewRecipe(Recipe recipe)
   // {
    //    this.recipe = recipe;
    //}

    private static Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);



        setPage();
        populateStepView();
        populateIngredientView();
        itemClickListen();
    }

    private void setPage()
    {

        this.recipe = SearchRecipe.getChosenRecipe();

        TextView recipeName = (TextView) findViewById(R.id.recipe_txtName);
        recipeName.setText(recipe.getName());

        TextView duration = (TextView) findViewById(R.id.recipe_txtDuration);
        duration.setText(recipe.getDuration().toString());

    }

    private void itemClickListen() {
        final ListView list = (ListView) findViewById(R.id.recipe_stepsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {

                Intent intent = new Intent(ViewRecipe.this, StepActivity.class);

                Bundle b = new Bundle();
                b.putInt("key", position); //Your id
                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }

    private void populateStepView()
    {
        ArrayAdapter<Step> adapter = new MyStepAdapter();
        ListView list = (ListView) findViewById(R.id.recipe_stepsListView);
        list.setAdapter(adapter);
    }

    private void populateIngredientView()
    {
        ArrayAdapter<Ingredient> adapter = new MyIngredientAdapter();
        ListView list = (ListView) findViewById(R.id.recipe_ingredientsListView);
        list.setAdapter(adapter);
    }

    private class MyStepAdapter extends ArrayAdapter<Step>
    {
        public MyStepAdapter()
        {
            super(ViewRecipe.this, R.layout.step_view, recipe.getSteps());
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

    private class MyIngredientAdapter extends ArrayAdapter<Ingredient>
    {
        public MyIngredientAdapter()
        {
            super(ViewRecipe.this, R.layout.ingredient_view, recipe.getIngredients());
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

    public void goBack(View view){
        this.finish();
    }

    public static Recipe getRecipe()
    {
        return recipe;
    }
}

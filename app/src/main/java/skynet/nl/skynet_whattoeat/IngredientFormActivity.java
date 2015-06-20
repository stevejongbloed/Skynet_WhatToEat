package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by Oteken on 19-06-15.
 */
public class IngredientFormActivity extends Activity
{

    private List<Ingredient> myIngredients = new ArrayList<Ingredient>();
    private List<String> allUnits = getAllUnits();
    private List<String> allIngredients = getAllIngredients();
    private static final String TAG = "tag";
    private Integer selectedView = -1;
    private boolean editMode = false;
    private boolean newMode = false;
    public static ArrayList<Recipe> matchingRecipes = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientform);

        populateIngredientList();
        populateListView();
        itemClickListen();

    }

    private void refresh()
    {
        populateListView();
        itemClickListen();
    }

    private void populateIngredientList()
    {

    }

    private void populateListView()
    {
        ArrayAdapter<Ingredient> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.ingredientform_ListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ingredient>
    {
        public MyListAdapter()
        {
            super(IngredientFormActivity.this, R.layout.ingredient_view, myIngredients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View ingredientView = convertView;
            if(ingredientView == null)
            {
                ingredientView = getLayoutInflater().inflate(R.layout.ingredient_view, parent, false);
            }


            Ingredient currentIngredient = myIngredients.get(position);

            //ImageView imageView = (ImageView) findViewById(R.id.ingredientform_imageImage);
            //imageView.setImageResource(currentIngredient.getIconId());

            TextView nameText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtName);
            nameText.setText(currentIngredient.getName());

            TextView unitText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtUnit);
            unitText.setText(currentIngredient.getUnit().getMeasurementUnit());

            TextView amountText = (TextView) ingredientView.findViewById(R.id.myIngredients_txtAmount);
            amountText.setText(currentIngredient.getUnit().getAmount());

            return ingredientView;
        }

    }

    private void itemClickListen()
    {
        final ListView list = (ListView) findViewById(R.id.ingredientform_ListView);
        list.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View clickedView, int position, long id) {

                cancelNewIngredient();
                if (position != selectedView)
                {
                    if (selectedView != -1)
                    {
                        list.getChildAt(selectedView).setBackgroundColor(0x00000000);
                    }
                    selectedView = position;
                    clickedView.setBackgroundColor(0x80FF8800);
                    newEdit();
                } else
                {
                    clickedView.setBackgroundColor(0x00000000);
                    selectedView = -1;
                    cancelEdit();
                }

            }
        });

    }

    private List<String> getAllUnits()
    {
        List<String> allUnits = Database.getAllUnits();
        return allUnits;
    }

    private List<String> getAllIngredients()
    {
        List<String> allIngredients = Database.getAllIngredients();
        return allIngredients;
    }

    public void deleteIngredient(View v)
    {
        cancelEdit();
        cancelNewIngredient();
        if(selectedView >= 0)
        {
            myIngredients.remove(selectedView.intValue());
            selectedView = -1;
            cancelEdit();
            refresh();
        }else
        {
            String toast = "No ingredient selected";
            Toast.makeText(IngredientFormActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void editIngredient(View v)
    {
        cancelNewIngredient();
        if(selectedView >= 0)
        {
            EditText amount = (EditText) findViewById(R.id.ingredientform_editAmount);
            Spinner dropdown = (Spinner) findViewById(R.id.ingredientform_dropdownUnit);
            if(editMode)
            {
                Ingredient editIngredient = myIngredients.get(selectedView.intValue());
                // Getting the text in the amount box, and converting it to a double.
                Double newUnitAmount = Double.parseDouble(amount.getText().toString());
                // Getting the selection from the dropdown menu, and selecting the right unit from
                // our list of units.
                Integer newUnitName = allUnits.indexOf((dropdown.getSelectedItem().toString()));

                // Creating a new Unit.
                Unit editUnit = new Unit(newUnitName, newUnitAmount);

                // Setting the new unit of the editing ingredient.
                editIngredient.setUnit(editUnit);


                // Global variable.
                editMode = false;

                selectedView = -1;
                refresh();
            } else
            {
                // Get the dropdown box, and add the adapter.
                List<String> units = allUnits;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(IngredientFormActivity.this, android.R.layout.simple_spinner_item, units);
                dropdown.setAdapter(adapter);
                int i = 0;

                // Set the unit of the current selected ingredient.
                while (!(myIngredients.get(selectedView).getUnit().getMeasurementUnit().equals(units.get(i))) || i > units.size() - 1) {
                    i++;
                    dropdown.setSelection(i);
                }

                // Change Text field to be equal to the selected ingredient amount.
                amount.setText("" + myIngredients.get(selectedView).getUnit().getAmount());

                // Global variable.
                editMode = true;
            }
        }else
        {
            String toast = "No ingredient selected";
            Toast.makeText(IngredientFormActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelEdit()
    {
        EditText amount = (EditText) findViewById(R.id.ingredientform_editAmount);
        Spinner dropdown = (Spinner) findViewById(R.id.ingredientform_dropdownUnit);


        // Global variable.
        editMode = false;
    }

    private void newEdit()
    {
        if(editMode)
        {
            EditText amount = (EditText) findViewById(R.id.ingredientform_editAmount);
            Spinner dropdown = (Spinner) findViewById(R.id.ingredientform_dropdownUnit);

            // Get the dropdown box, and add the adapter.
            List<String> units = allUnits;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(IngredientFormActivity.this, android.R.layout.simple_spinner_item, units);
            dropdown.setAdapter(adapter);
            int i = 0;

            // Set the unit of the current selected ingredient.
            while (!(myIngredients.get(selectedView).getUnit().getMeasurementUnit().equals(units.get(i))) || i > units.size() - 1) {
                i++;
                dropdown.setSelection(i);
            }


            // Change Text field to be equal to the selected ingredient amount.
            amount.setText("" + myIngredients.get(selectedView).getUnit().getAmount());
        }
    }

    public void newIngredient(View v)
    {
        cancelEdit();

        Button newIngredientButton = (Button) findViewById(R.id.ingredientform_buttonNewIngredient);
        Spinner dropdownIngredients = (Spinner) findViewById(R.id.ingredientform_dropdownIngredient);
        EditText amount = (EditText) findViewById(R.id.ingredientform_editAmount);
        Spinner dropdownUnits = (Spinner) findViewById(R.id.ingredientform_dropdownUnit);

        if(newMode)
        {
            if(!(amount.getText().toString().length() > 0))
            {
                amount.setText("1.0");
            }
                Integer ingredientId = allIngredients.indexOf(dropdownIngredients.getSelectedItem());
                Double newUnitAmount = Double.parseDouble(amount.getText().toString());
                // Getting the selection from the dropdown menu, and selecting the right unit from
                // our list of units.
                Integer newUnitName = allUnits.indexOf((dropdownUnits.getSelectedItem().toString()));
                Unit ingredientUnit = new Unit(newUnitName, newUnitAmount);

                Ingredient newIngredient = new Ingredient(ingredientId, ingredientUnit);

                myIngredients.add(newIngredient);

                // Changing text of the edit button from New Ingredient to Add Ingredient.
                newIngredientButton.setText("New Ingredient");


                dropdownIngredients.setVisibility(View.INVISIBLE);

                newMode = false;
                selectedView = -1;
                refresh();

        }else
        {
            // Get the dropdown box, and add the adapter.
            List<String> ingredients = allIngredients;
            ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(IngredientFormActivity.this, android.R.layout.simple_spinner_item, ingredients);
            dropdownIngredients.setAdapter(adapterIngredients);

            // Get the dropdown box, and add the adapter.
            List<String> units = allUnits;
            ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(IngredientFormActivity.this, android.R.layout.simple_spinner_item, units);
            dropdownUnits.setAdapter(adapterUnits);

            // Changing text of the edit button from New Ingredient to Add Ingredient.
            newIngredientButton.setText("Add Ingredient");

            // Set the dropdown menu and amount text to visible.
            dropdownIngredients.setVisibility(View.VISIBLE);

            newMode = true;
        }
    }

    private void cancelNewIngredient()
    {
        Button newIngredientButton = (Button) findViewById(R.id.ingredientform_buttonNewIngredient);
        Spinner dropdownIngredients = (Spinner) findViewById(R.id.ingredientform_dropdownIngredient);
        EditText amount = (EditText) findViewById(R.id.ingredientform_editAmount);
        Spinner dropdownUnits = (Spinner) findViewById(R.id.ingredientform_dropdownUnit);

        // Changing text of the edit button from New Ingredient to Add Ingredient.
        newIngredientButton.setText("New Ingredient");

        // Set the dropdown menu and amount text to visible.
        dropdownIngredients.setVisibility(View.INVISIBLE);

        newMode = false;
    }

    public void searchForm(View v)
    {
        Intent intent = new Intent(IngredientFormActivity.this, SearchRecipe.class);

        // getMatches only runs if there is athleast one ingredient filled inside the form.
        if(myIngredients.size() > 0)
        {
            matchingRecipes = getMatches(myIngredients);

            // Intent is only given when matches have been found.
            Bundle b = new Bundle();
            b.putInt("key", 999); //Your id
            intent.putExtras(b);

        }

        startActivity(intent);
    }

    // Returns an arraylist of all recipes in our database that match with the given ingredients.
    private ArrayList<Recipe> getMatches(List<Ingredient> formIngredients)
    {

        // All recipes inside our database.
        ArrayList<Recipe> allRecipes = Database.getAllRecipes();
        // All recipes that can be made with the given ingredients
        ArrayList<Recipe> matchRecipes = new ArrayList<Recipe>();
        // All ids of the ingredients given.
        ArrayList<Integer> formIngredientIds = new ArrayList<Integer>();


        // Translating all ingredients on the form page to id's.
        for (int i = 0; i < formIngredients.size(); i++) {
            formIngredientIds.add(formIngredients.get(i).getId());
        }

        Boolean skipRecipe = false;

        // Looping through all recipes.
        for (int i = 0; i < allRecipes.size(); i++) {
            // Looping through all ingredients.
            for (int j = 0; j < allRecipes.get(i).getIngredients().size(); j++) {
                // If the recipe doesn't contain one ingredient, this code is skipped.
                if (skipRecipe == false) {
                    Integer ingredientId = allRecipes.get(i).getIngredients().get(j).getId();
                    // Compare ingredient id with ingredients filled into the form.
                    if (!(formIngredientIds.contains(ingredientId))) {
                        skipRecipe = true;
                    }
                }
            }
            // Recipes only get added if they contain all ingredients in the form.
            if (skipRecipe == false) {
                matchRecipes.add(allRecipes.get(i));
            }
            skipRecipe = false;

        }
        return matchRecipes;
    }
}

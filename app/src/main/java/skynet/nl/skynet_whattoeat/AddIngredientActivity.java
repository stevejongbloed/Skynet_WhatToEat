package skynet.nl.skynet_whattoeat;

import android.app.ActionBar;
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
 * Created by Oteken on 07-06-15.
 */
public class AddIngredientActivity extends Activity
{

    public static List<Ingredient> newIngredientsList = new ArrayList<Ingredient>();
    private List<Ingredient> myIngredients = new ArrayList<Ingredient>();
    private List<String> allUnits = getAllUnits();
    private List<String> allIngredients = getAllIngredients();
    private static final String TAG = "tag";
    private Integer selectedView = -1;
    private boolean editMode = false;
    private boolean newMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        setPage();
        populateIngredientList();
        populateListView();
        itemClickListen();

    }

    private void setPage()
    {
        if(getIntent().getExtras() != null)
        {
            myIngredients = CreateRecipe.getIngredients();
        }
    }


    private void refresh()
    {
        populateListView();
        itemClickListen();
    }

    private void populateIngredientList()
    {
        //for(Ingredient ingredient: CreateRecipe.getRecipe().getIngredients())
        //{
        //    myIngredients.add(ingredient);
        //}
    }

    private void populateListView()
    {
        ArrayAdapter<Ingredient> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.addIngredients_ListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ingredient>
    {
        public MyListAdapter()
        {
            super(AddIngredientActivity.this, R.layout.ingredient_view, myIngredients);
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
        final ListView list = (ListView) findViewById(R.id.addIngredients_ListView);
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
            Toast.makeText(AddIngredientActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void editIngredient(View v)
    {
        cancelNewIngredient();
        if(selectedView >= 0)
        {
            EditText amount = (EditText) findViewById(R.id.addIngredients_editAmount);
            Spinner dropdown = (Spinner) findViewById(R.id.addIngredients_dropdownUnit);
            Button editButton = (Button) findViewById(R.id.addIngredients_buttonEdit);
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


                // Changing text of the edit button from edit to save.
                editButton.setText("Edit");

                // Set the dropdown menu and amount text to visible.
                dropdown.setVisibility(View.INVISIBLE);
                amount.setVisibility(View.INVISIBLE);

                // Global variable.
                editMode = false;

                selectedView = -1;
                refresh();
            } else
            {
                // Get the dropdown box, and add the adapter.
                List<String> units = allUnits;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIngredientActivity.this, android.R.layout.simple_spinner_item, units);
                dropdown.setAdapter(adapter);
                int i = 0;

                // Set the unit of the current selected ingredient.
                while (!(myIngredients.get(selectedView).getUnit().getMeasurementUnit().equals(units.get(i))) || i > units.size() - 1) {
                    i++;
                    dropdown.setSelection(i);
                }

                // Change Text field to be equal to the selected ingredient amount.
                amount.setText("" + myIngredients.get(selectedView).getUnit().getAmount());

                // Changing text of the edit button from edit to save.
                editButton.setText("Save");

                // Set the dropdown menu and amount text to visible.
                dropdown.setVisibility(View.VISIBLE);
                amount.setVisibility(View.VISIBLE);

                // Global variable.
                editMode = true;
            }
        }else
        {
            String toast = "No ingredient selected";
            Toast.makeText(AddIngredientActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelEdit()
    {
        EditText amount = (EditText) findViewById(R.id.addIngredients_editAmount);
        Spinner dropdown = (Spinner) findViewById(R.id.addIngredients_dropdownUnit);
        Button editButton = (Button) findViewById(R.id.addIngredients_buttonEdit);

        // Changing text of the edit button from edit to save.
        editButton.setText("Edit");

        // Set the dropdown menu and amount text to visible.
        dropdown.setVisibility(View.INVISIBLE);
        amount.setVisibility(View.INVISIBLE);

        // Global variable.
        editMode = false;
    }

    private void newEdit()
    {
        if(editMode)
        {
            EditText amount = (EditText) findViewById(R.id.addIngredients_editAmount);
            Spinner dropdown = (Spinner) findViewById(R.id.addIngredients_dropdownUnit);
            Button editButton = (Button) findViewById(R.id.addIngredients_buttonEdit);

            // Get the dropdown box, and add the adapter.
            List<String> units = allUnits;
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddIngredientActivity.this, android.R.layout.simple_spinner_item, units);
            dropdown.setAdapter(adapter);
            int i = 0;

            // Set the unit of the current selected ingredient.
            while (!(myIngredients.get(selectedView).getUnit().getMeasurementUnit().equals(units.get(i))) || i > units.size() - 1) {
                i++;
                dropdown.setSelection(i);
            }

            // Set the dropdown menu and amount text to visible.
            dropdown.setVisibility(View.VISIBLE);
            amount.setVisibility(View.VISIBLE);

            // Change Text field to be equal to the selected ingredient amount.
            amount.setText("" + myIngredients.get(selectedView).getUnit().getAmount());
        }
    }

    public void newIngredient(View v)
    {
        cancelEdit();

        Button newIngredientButton = (Button) findViewById(R.id.addIngredients_buttonNewIngredient);
        Spinner dropdownIngredients = (Spinner) findViewById(R.id.addIngredients_dropdownIngredient);
        EditText amount = (EditText) findViewById(R.id.addIngredients_editAmount);
        Spinner dropdownUnits = (Spinner) findViewById(R.id.addIngredients_dropdownUnit);

        if(newMode)
        {
            if(amount.getText().toString().length() > 0) {
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

                // Set the dropdown menu and amount text to visible.
                dropdownUnits.setVisibility(View.INVISIBLE);
                dropdownIngredients.setVisibility(View.INVISIBLE);
                amount.setVisibility(View.INVISIBLE);

                newMode = false;
                selectedView = -1;
                refresh();
            } else
            {
                String toast = "No amount filled";
                Toast.makeText(AddIngredientActivity.this, toast, Toast.LENGTH_SHORT).show();
                cancelNewIngredient();
            }
        }else
        {
            // Get the dropdown box, and add the adapter.
            List<String> ingredients = allIngredients;
            ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(AddIngredientActivity.this, android.R.layout.simple_spinner_item, ingredients);
            dropdownIngredients.setAdapter(adapterIngredients);

            // Get the dropdown box, and add the adapter.
            List<String> units = allUnits;
            ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(AddIngredientActivity.this, android.R.layout.simple_spinner_item, units);
            dropdownUnits.setAdapter(adapterUnits);

            // Changing text of the edit button from New Ingredient to Add Ingredient.
            newIngredientButton.setText("Add Ingredient");

            // Set the dropdown menu and amount text to visible.
            dropdownUnits.setVisibility(View.VISIBLE);
            dropdownIngredients.setVisibility(View.VISIBLE);
            amount.setVisibility(View.VISIBLE);

            newMode = true;
        }
    }

    private void cancelNewIngredient()
    {
        Button newIngredientButton = (Button) findViewById(R.id.addIngredients_buttonNewIngredient);
        Spinner dropdownIngredients = (Spinner) findViewById(R.id.addIngredients_dropdownIngredient);
        EditText amount = (EditText) findViewById(R.id.addIngredients_editAmount);
        Spinner dropdownUnits = (Spinner) findViewById(R.id.addIngredients_dropdownUnit);

        // Changing text of the edit button from New Ingredient to Add Ingredient.
        newIngredientButton.setText("New Ingredient");

        // Set the dropdown menu and amount text to visible.
        dropdownUnits.setVisibility(View.INVISIBLE);
        dropdownIngredients.setVisibility(View.INVISIBLE);
        amount.setVisibility(View.INVISIBLE);

        newMode = false;
    }
    // Back to main screen
    public void goBack(View view){
        this.finish();
    }

    public void saveIngredients(View v)
    {
        newIngredientsList = myIngredients;

        Intent intent = new Intent(AddIngredientActivity.this, CreateRecipe.class);
        intent.putExtra("EXTRA_MESSAGE", true);
        startActivity(intent);
    }

    public static List<Ingredient> getNewIngredientsList()
    {
        return newIngredientsList;
    }
}

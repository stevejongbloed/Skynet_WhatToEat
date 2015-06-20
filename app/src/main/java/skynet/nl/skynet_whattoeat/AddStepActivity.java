package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Oteken on 07-06-15.
 */
public class AddStepActivity extends Activity {

    private static Step step;
    private static int editingStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_step);

        setPage();
    }

    private static Step template()
    {
        Step step = new Step();
        return step;
    }

    private void setPage()
    {
        if (getIntent().getExtras() != null)
        {
            if(!(getIntent().getExtras().getInt("key") == -1))
            {
                Bundle b = getIntent().getExtras();
                int value = b.getInt("key");

                step = CreateRecipe.getRecipe().getSteps().get(value);
                editingStep = value;
            } else
            {
                if(AddPhotoActivity.photo == true)
                {
                    step.setPhoto(AddPhotoActivity.getPhoto());
                }

            }
            EditText title = (EditText) findViewById(R.id.addStep_txtName);
            title.setText(step.getTitle());

            EditText text = (EditText) findViewById(R.id.addStep_txtText);
            text.setText(step.getText());

            EditText stepNumber = (EditText) findViewById(R.id.addStep_txtStepNumber);
            stepNumber.setText(step.getStepNumber().toString());

            if(!(step.getPhoto() == null))
            {
                ImageView image = (ImageView) findViewById(R.id.addStep_ImageView);
                image.setImageDrawable(step.getPhoto().getDrawable());
            }


        } else
        {
            step = template();
            editingStep = -1;
        }
    }

    public void saveStep(View v)
    {
        if(validateStepNumber())
        {
            saveStepVariables();

            if (editingStep == -1) {
                Intent intent = new Intent(AddStepActivity.this, CreateRecipe.class);

                Bundle b = new Bundle();
                b.putInt("key", 1001); //Your id
                intent.putExtras(b);

                startActivity(intent);
            } else
            {
                CreateRecipe.getRecipe().getSteps().set(editingStep, step);
                Intent intent = new Intent(AddStepActivity.this, CreateRecipe.class);
                startActivity(intent);
            }
        } else
        {
            String toast = "Step already exists, or invalid step number";
            Toast.makeText(AddStepActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    // Returns true if the step count does not exist in the original recipe.
    private Boolean validateStepNumber()
    {
        EditText stepNumber = (EditText) findViewById(R.id.addStep_txtStepNumber);

        if(stepNumber.getText().toString().equals(""))
        {
            return false;
        }

        Integer stepCount= Integer.valueOf(stepNumber.getText().toString());
        ArrayList<Step> allSteps = CreateRecipe.getRecipe().getSteps();

        if(stepCount == 0) {
            return false;
        }

            for (int i = 0; i < allSteps.size(); i++)
            {
                if (stepCount == allSteps.get(i).getStepNumber())
                {
                    if(editingStep == -1 && step.getStepNumber() == stepCount)
                    {
                        return false;
                    }
                }
            }

        return true;
    }

    public void deleteStep(View v)
    {
        if(editingStep != -1)
        {
            CreateRecipe.getRecipe().getSteps().remove(editingStep);
        }
        Intent intent = new Intent(AddStepActivity.this, CreateRecipe.class);
        startActivity(intent);
    }

    public void takePic(View v)
    {
        if(validateStepNumber())
        {
            saveStepVariables();

            Intent intent = new Intent(AddStepActivity.this, AddPhotoActivity.class);
            startActivity(intent);
        } else
        {
            String toast = "Step already exists, or invalid step number";
            Toast.makeText(AddStepActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveStepVariables()
    {
        EditText title = (EditText) findViewById(R.id.addStep_txtName);
        step.setTitle(title.getText().toString());

        EditText text = (EditText) findViewById(R.id.addStep_txtText);
        step.setText(text.getText().toString());

        EditText stepNumber = (EditText) findViewById(R.id.addStep_txtStepNumber);
        step.setStepNumber(Integer.valueOf(stepNumber.getText().toString()));

        if(!(step.getPhoto() == null))
        {
            ImageView image = (ImageView) findViewById(R.id.addStep_ImageView);
            image.setImageDrawable(step.getPhoto().getDrawable());
        }

    }


    public static Step getStep()
    {
        return step;
    }
}

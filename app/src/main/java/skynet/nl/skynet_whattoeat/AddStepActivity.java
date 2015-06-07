package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Oteken on 07-06-15.
 */
public class AddStepActivity extends Activity {

    private static Step step = template();
    private static int editingStep = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_step);
        editingStep = -1;
        step = new Step();

        setPage();
    }

    private static Step template()
    {
        Step step = new Step();
        return step;
    }

    private void setPage()
    {
        if(getIntent().getExtras() != null)
        {
            Bundle b = getIntent().getExtras();
            int value = b.getInt("key");

            step = CreateRecipe.getRecipe().getSteps().get(value);
            editingStep = value;

            EditText title = (EditText) findViewById(R.id.addStep_txtName);
            title.setText(step.getTitle());

            EditText text = (EditText) findViewById(R.id.addStep_txtText);
            text.setText(step.getText());

            EditText stepNumber = (EditText) findViewById(R.id.addStep_txtStepNumber);
            stepNumber.setText(step.getStepNumber().toString());
        }else
        {
            step = template();
            editingStep = -1;
        }
    }

    public void saveStep(View v)
    {
        EditText title = (EditText) findViewById(R.id.addStep_txtName);
        step.setTitle(title.getText().toString());

        EditText text = (EditText) findViewById(R.id.addStep_txtText);
        step.setText(text.getText().toString());

        EditText stepNumber = (EditText) findViewById(R.id.addStep_txtStepNumber);
        step.setStepNumber(Integer.valueOf(stepNumber.getText().toString()));

        if(editingStep == -1)
        {
            Intent intent = new Intent(AddStepActivity.this, CreateRecipe.class);

            Bundle b = new Bundle();
            b.putInt("key", 1001); //Your id
            intent.putExtras(b);

            startActivity(intent);
        }else
        {
            CreateRecipe.getRecipe().getSteps().set(editingStep, step);
            Intent intent = new Intent(AddStepActivity.this, CreateRecipe.class);
            startActivity(intent);
        }
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

    public static Step getStep()
    {
        return step;
    }
}

package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Oteken on 06-06-15.
 */
public class StepActivity extends Activity
{

    private Step step;
    private int value;

    //public StepActivity(Step step)
    //{
    //    this.step = step;
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle b = getIntent().getExtras();
        int value = b.getInt("key");

        step = ViewRecipe.getRecipe().getSteps().get(value);
        this.value = value;

        setPage();
    }

    private void setPage()
    {
        TextView name = (TextView)findViewById(R.id.step_txtName);
        name.setText(step.getTitle());

        TextView text = (TextView)findViewById(R.id.step_txtText);
        text.setText(step.getText());
    }

    public void lastStep(View v)
    {
        if(value != 0)
        {
            Intent intent = new Intent(StepActivity.this, StepActivity.class);

            Bundle b = new Bundle();
            b.putInt("key", value - 1); //Your id
            intent.putExtras(b);

            startActivity(intent);
        }
    }

    public void nextStep(View v)
    {
        if(value < (ViewRecipe.getRecipe().getSteps().size() - 1))
        {
            Intent intent = new Intent(StepActivity.this, StepActivity.class);

            Bundle b = new Bundle();
            b.putInt("key", value + 1); //Your id
            intent.putExtras(b);

            startActivity(intent);
        }
    }

}

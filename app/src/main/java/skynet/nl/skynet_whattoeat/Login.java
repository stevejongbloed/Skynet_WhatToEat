package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v){
        EditText username = (EditText)findViewById(R.id.etUsername);
        String usernameString = username.getText().toString();
        EditText password = (EditText)findViewById(R.id.etPassword);
        String passwordString = password.getText().toString();


        if(usernameString == "admin"){
            Toast.makeText(getApplicationContext(),"worked",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
        }
    }
}

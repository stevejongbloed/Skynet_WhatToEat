package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView logo = (ImageView)findViewById(R.id.logoHolder);
        logo.setImageResource(R.mipmap.ic_launcher); //set logo
    }

    //hardcoded login, main screen
    public void onClick(View v){
        EditText username = (EditText)findViewById(R.id.etUsername);
        String usernameString = username.getText().toString();
        EditText password = (EditText)findViewById(R.id.etPassword);
        String passwordString = password.getText().toString();

        if(usernameString.equals("admin") && passwordString.equals("admin")){
            Toast.makeText(getApplicationContext(),"Welcome!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this, ApplicationMenu.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"Wrong password, or Username",Toast.LENGTH_LONG).show();
        }
    }
}

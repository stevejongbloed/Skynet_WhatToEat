package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView logo = (ImageView)findViewById(R.id.logoHolder);
        logo.setImageResource(R.mipmap.ic_launcher); //set logo
        getActionBar().hide();
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"American_Captain.ttf");
        //Typeface myTypeface2 = Typeface.createFromAsset(getAssets(),"American_Captain.tff");

        TextView myTextview = (TextView) findViewById(R.id.appName);
        //TextView myTextview2 = (TextView) findViewById(R.id.appName);
        //myTextview2.setTypeface(myTypeface2);
        myTextview.setTypeface(myTypeface);
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

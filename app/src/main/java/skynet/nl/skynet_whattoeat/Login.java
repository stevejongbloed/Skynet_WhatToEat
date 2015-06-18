package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class Login extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        getActionBar().hide(); // hide top action bar

        //set image on splashscreen
        ImageView splashImg = (ImageView) findViewById(R.id.splashLogin);
        splashImg.setImageResource(R.mipmap.ic_launcher);

        //set font for text on splashscreen
        Typeface splashFont = Typeface.createFromAsset(getAssets(),"American_Captain.ttf");
        TextView splashTxt = (TextView) findViewById(R.id.splashName);
        splashTxt.setTypeface(splashFont);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(4500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    // Determine whether the current user is an anonymous user
                    if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                        // If user is anonymous, send the user to LoginSignupActivity.class
                        Intent intent = new Intent(Login.this,LoginSignupActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If current user is NOT anonymous user
                        // Get current user data from Parse.com
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        if (currentUser != null) {
                            // Send logged in user to ApplicationMenu.class
                            Intent intent = new Intent(Login.this, ApplicationMenu.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Send user to LoginSignupActivity.class
                            Intent intent = new Intent(Login.this,LoginSignupActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        };
        timer.start();
    }

    // destroy splash screen if back button is pressed
    @Override
    public void onPause(){
        super.onPause();
        finish();
    }


}
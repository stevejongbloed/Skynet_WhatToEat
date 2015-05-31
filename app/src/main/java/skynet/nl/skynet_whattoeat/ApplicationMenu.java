package skynet.nl.skynet_whattoeat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ApplicationMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_menu);
        ActionBar bar = getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){

            case R.id.a1:
                login();
                return true;
            case R.id.a2:
                expirationDate();
                return true;
            case R.id.a3:
                setting();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(){
        startActivity(new Intent(ApplicationMenu.this,Login.class));
    }
    public void expirationDate(){
        startActivity(new Intent(ApplicationMenu.this,IngredientsList.class));
    }

    public void setting(){
        startActivity(new Intent(ApplicationMenu.this,Settings.class));
    }

}
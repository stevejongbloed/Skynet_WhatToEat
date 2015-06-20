package skynet.nl.skynet_whattoeat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Oteken on 20-06-15.
 */
public class AddPhotoActivity extends Activity
{

    Button btnTakePhoto;
    private static ImageView imgTakenPhoto;
    private static final int CAM_REQUEST = 1313;
    public static Boolean photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photo = false;
        imgTakenPhoto = null;

        setContentView(R.layout.activity_add_photo);

        btnTakePhoto = (Button) findViewById(R.id.button1);
        imgTakenPhoto = (ImageView) findViewById(R.id.imageview1);

        btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgTakenPhoto.setImageBitmap(thumbnail);
        }
    }

    class btnTakePhotoClicker implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void savePicture(View v)
    {
        if(imgTakenPhoto != null)
        {
            photo = true;
        }
        Intent intent = new Intent(AddPhotoActivity.this, AddStepActivity.class);

        Bundle b = new Bundle();
        b.putInt("key", -1); //Your id
        intent.putExtras(b);

        startActivity(intent);
    }

    public void deletePicture(View v)
    {
        imgTakenPhoto.setImageDrawable(null);
    }

    public static ImageView getPhoto()
    {
        return imgTakenPhoto;
    }
}

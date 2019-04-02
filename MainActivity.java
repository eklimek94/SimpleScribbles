package peppersapplications.com.simplescribbles;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private PaintArea paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkWritePermissions();
        checkReadPermissions();


        paint = findViewById(R.id.paint);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paint.init(metrics);


    }


    public void checkWritePermissions() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void checkReadPermissions() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void screenShot() {

        Random generator = new Random();
        int n = 1000000000;
        Bitmap bit;

        bit = paint.sendBitMap();
        n = generator.nextInt(n);
        String name = "ss-" + n + ".jpg";

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();


        File file = new File(myDir, name);
        if (file.exists()) file.delete();
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), bit, name, "Screenshot from Simple Scribbles");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.black_round) {
            Toast.makeText(getApplicationContext(), "Black Lines Activated", Toast.LENGTH_SHORT).show();
            paint.black();
        }
        if (id == R.id.white_round) {
            Toast.makeText(getApplicationContext(), "White Lines Activated", Toast.LENGTH_SHORT).show();
            paint.white();
        }
        if (id == R.id.erase) {
            Toast.makeText(getApplicationContext(), "Erased", Toast.LENGTH_SHORT).show();
            paint.erase();
        }

        if (id == R.id.screenShot) {
            Toast.makeText(getApplicationContext(), "Click, Scribble Added to Gallery", Toast.LENGTH_LONG).show();
            screenShot();

        }

        return super.onOptionsItemSelected(item);
    }
}

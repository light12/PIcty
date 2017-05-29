package fr.pictycompany.picty;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;


public class MainActivity extends AppCompatActivity {

    private GridView mListPhotos;
    private ImageAdapter myImageAdapter;
    private CursorPhotos mCursor;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //Cela signifie que la permission à déjà était
                //demandé et l'utilisateur l'a refusé
                //Vous pouvez aussi expliquer à l'utilisateur pourquoi
                //cette permission est nécessaire et la redemander
            } else {
                //Sinon demander la permission
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListPhotos = (GridView)findViewById(R.id.listPhotos);
        myImageAdapter = new ImageAdapter(this);
        mListPhotos.setAdapter(myImageAdapter);

        mCursor = new CursorPhotos(getContentResolver(), myImageAdapter);
        mCursor.execute();

    }
}

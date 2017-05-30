package fr.pictycompany.picty;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    // Création de menu et liens entre les items

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.photos:
                return true;
            case R.id.sharing:
                return true;
            case R.id.hangover:
                return true;
        }
        return false;
    }
}

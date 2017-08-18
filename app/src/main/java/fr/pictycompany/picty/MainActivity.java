package fr.pictycompany.picty;

import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Vérification de l'autorisation d'accéder aux données
        do {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            }
        }while(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED);

        //Si l'autorisation est accordée, on affiche le fragment des photos

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

            mBottomNavigationView.setOnNavigationItemSelectedListener
                    (new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Fragment selectedFragment = null;
                            switch (item.getItemId()) {
                                case R.id.myphotos:
                                    selectedFragment = PhotosFragment.newInstance();
                                    break;
                                case R.id.sharing:
                                    selectedFragment = SharingFragment.newInstance();
                                    break;
                                case R.id.hangover:
                                    selectedFragment = HangoverFragment.newInstance();
                                    break;
                            }
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();
                            return true;
                        }
                    });

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, PhotosFragment.newInstance());
            transaction.commit();
        }
    }

}

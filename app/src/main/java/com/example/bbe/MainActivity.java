package com.example.bbe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    ImageButton cloud;
    ImageButton barcode;
    ImageButton input;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_einstellungen, R.id.nav_profil, R.id.nav_ausweis, R.id.nav_benutzerhandbuch, R.id.nav_feedback, R.id.nav_impressum)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu

            @Override

            public boolean onNavigationItemSelected(MenuItem nav_host_fragment) {

                //Check to see which item was being clicked and perform appropriate action

                switch (nav_host_fragment.getItemId()) {

                    // launch new intent instead of loading fragment

                        //case R.id.nav_home: Intent intent = new Intent(MainActivity.this, home.class);
                        //startActivity(intent);
                        /*case R.id.nav_einstellungen: Intent intent2 = new Intent(MainActivity.this, activity_settings.class);
                                                    startActivity(intent2);
                                                    break;*/
                        case R.id.nav_profil: Intent intent3 = new Intent(MainActivity.this, profil.class);
                                                    startActivity(intent3);
                                                    break;
                        case R.id.nav_ausweis: Intent intent4 = new Intent(MainActivity.this, ausweis.class);
                        startActivity(intent4);
                        break;
                        case R.id.nav_benutzerhandbuch: Intent intent5 = new Intent(MainActivity.this, benutzerhandbuch.class);
                        startActivity(intent5);
                        break;
                        case R.id.nav_feedback: Intent intent6 = new Intent(MainActivity.this, feedback.class);
                        startActivity(intent6);
                        break;
                        case R.id.nav_impressum: Intent intent7 = new Intent(MainActivity.this, impressum.class);
                        startActivity(intent7);


                    drawer.closeDrawers();

                    return true;
                } return true;} });

        cloud= (ImageButton) findViewById(R.id.dataprint);
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencloud();

            }
        });
        barcode= (ImageButton) findViewById(R.id.barcode);
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbarcode();

            }
        });
        input= (ImageButton) findViewById(R.id.input);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openinput();

            }
        });



    }
    public void opencloud(){
        Intent intent = new Intent(this, com.example.bbe.cloud.class);
        startActivity(intent);
    }
    public void openbarcode(){
        Intent intent = new Intent(this, com.example.bbe.barcode.class);
        startActivity(intent);
    }
    public void openinput(){
        Intent intent = new Intent(this, com.example.bbe.input.class);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}



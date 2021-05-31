package com.example.bbe;

import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.preference.PreferenceActivity;
import java.util.Set;

public class input extends AppCompatActivity {

    private EditText blutzucker,langzeit,befaktor,kalorien,menge,broteinheiten,insulin;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_input);
            blutzucker = findViewById(R.id.edit1);
            langzeit = findViewById(R.id.edit2);
            befaktor = findViewById(R.id.edit3);
            kalorien = findViewById(R.id.edit4);
            menge = findViewById(R.id.edit5);
            broteinheiten = findViewById(R.id.edit6);
            insulin = findViewById(R.id.edit7);
        }

        // Fetch the stored data in onResume()
        // Because this is what will be called
        // when the app opens again
        @Override
        protected void onResume() {
            super.onResume();

            // Fetching the stored data
            // from the SharedPreference

            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);


            //
            String s1 = sh.getString("blutzucker", "");
            int a = sh.getInt("langzeit", 0);
            float b = sh.getFloat("befaktor", 0);
            float kalo = sh.getFloat("kalorien", 0);
            int gegessen = sh.getInt("menge", 0);
            float BE = sh.getFloat("broteinheiten",0);

            // Setting the fetched data
            // in the EditTexts
            blutzucker.setText(s1);
            befaktor.setText(String.valueOf(b));
            kalorien.setText(String.valueOf(kalo));
            menge.setText(String.valueOf(gegessen));

            String kalorien1 = kalorien.getText().toString();
            String menge1 = menge.getText().toString();
            float kalorien2=Float.parseFloat(kalorien1);
            float menge2 = Float.parseFloat(menge1);
            float brot = (kalorien2/100)*(menge2/12);

            String f = blutzucker.getText().toString();
            float f1 = Float.parseFloat(f);
            float hba1 = 0.031f * f1 + 2.393f;
            int hba1c = (int) hba1;
            langzeit.setText(String.valueOf(hba1c));
            broteinheiten.setText(String.valueOf(brot));

            String faktor = befaktor.getText().toString();
            float faktor1 = Float.parseFloat(faktor);
            String brot1 = broteinheiten.getText().toString();
            float brot2 = Float.parseFloat(brot1);

            float insulineinheiten = brot2 * faktor1;
            insulin.setText(String.valueOf(insulineinheiten));

        }

        // Store the data in the SharedPreference
        // in the onPause() method
        // When the user closes the application
        // onPause() will be called
        // and data will be stored
        @Override
        protected void onPause() {
            super.onPause();

            // Creating a shared pref object
            // with a file name "MySharedPref"
            // in private mode
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // write all the data entered by the user in SharedPreference and apply
            myEdit.putString("blutzucker", blutzucker.getText().toString());
            myEdit.putInt("langzeit", Integer.parseInt(langzeit.getText().toString()));
            myEdit.putFloat("befaktor",Float.parseFloat(befaktor.getText().toString()));
            myEdit.putFloat("kalorien",Float.parseFloat(kalorien.getText().toString()));
            myEdit.putInt("menge", Integer.parseInt(menge.getText().toString()));
            myEdit.putFloat("broteinheiten", Float.parseFloat(broteinheiten.getText().toString()));
            myEdit.putFloat("insulin", Float.parseFloat(insulin.getText().toString()));
            myEdit.apply();
        }
    }








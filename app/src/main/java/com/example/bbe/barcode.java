package com.example.bbe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.util.List;

import java.io.IOException;
import java.text.BreakIterator;

public class barcode extends AppCompatActivity {


    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private TextView barcodeText;
    private TextView kohlenhydrateText;
    private TextView be;
    private String barcodeData;
    private EditText essen;
    private TextView insulin;
    private String barcodereuse = "";

     String testarray[][] = new String[][]
            {{"9006900014858", "Der Grüne", "6.2"},
                    {"7311250079993", "Zyn Deep Freeze", "0"},
                    {"4031300252932", "Velo Snus", "0"},
                    {"7612100017043", "Flasche", "0"},
                    {"5060166692285", "Monster Energy", "12"},
                    {"8000500310427", "Nutella biscuits", "63.3"},
                    {"5449000236623", "Fuze Tea Peach", "4.5"},
                    {"42104643", "Cappy Ice Fruits", "6.7"},
                    {"9010437002486", "Popcorn", "50"}};




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_barcode);
        setContentView(R.layout.activity_barcode);
        surfaceView = findViewById(R.id.surface_view);
        barcodeText = findViewById(R.id.barcode_text);
        kohlenhydrateText = findViewById(R.id.kohlenhydrate_text);
        be = (TextView) findViewById(R.id.edit_be);
        essen = findViewById(R.id.edit_essen);
        insulin = findViewById(R.id.edit_insulin);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        float b = sh.getFloat("befaktor", 0);

        Button refresh;
        refresh= (Button) findViewById(R.id.refreshbutton);
        refresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v) {
                String menge1 = essen.getText().toString();
                if (menge1.matches("")) {
                    be.setText("Berechnung fehlgeschlagen!");
                    insulin.setText("Berechnung fehlgeschlagen!");
                }
                else {
                    for (int i = 0; i < testarray.length; i++) {
                        if (barcodereuse.equals(testarray[i][0])) {

                            float menge2 = Float.parseFloat(menge1);
                            float kalorien = Float.parseFloat(testarray[i][2]);
                            float brot = (kalorien / 100) * (menge2 / 12);
                            String brot2 = String.format("%.02f", brot);
                            be.setText("Broteinheiten: " + brot2);

                            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            float b = sh.getFloat("befaktor", 0);
                            float insulineinheiten = b * brot;
                            int insulin2 = Math.round(insulineinheiten);
                            insulin.setText("Insulineinheiten: " + insulin2);
                        } else {
                            be.setText("Berechnung fehlgeschlagen!");
                            insulin.setText("Berechnung fehlgeschlagen!");
                        }
                    }
                }
            }
        });


        initialiseDetectorsAndSources();


    }

    private void initialiseDetectorsAndSources() {

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(barcode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(barcode.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {


                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {

                    barcodeText.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                barcodeText.removeCallbacks(null);
                                barcodeData = barcodes.valueAt(0).email.address;
                                for (int i = 0; i < testarray.length; i++) {


                                    if (barcodeData.equals(testarray[i][0])) {
                                        barcodereuse = testarray[i][0];

                                        barcodeText.setText("Produkt: " + testarray[i][1]);

                                        kohlenhydrateText.setText("Kohlenhydrate in g/100g: " + testarray[i][2]);
                                        String menge1 = essen.getText().toString();
                                        if (menge1.matches("")) {
                                            Toast.makeText(barcode.this, "Geben Sie die Menge ein", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                        else{
                                            float menge2 = Float.parseFloat(menge1);
                                            float kalorien = Float.parseFloat(testarray[i][2]);
                                            float brot = (kalorien / 100) * (menge2 / 12);
                                            String brot2 = String.format("%.02f", brot);
                                            be.setText("Broteinheiten: " + brot2);

                                            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                            float b = sh.getFloat("befaktor", 0);
                                            float insulineinheiten = b * brot;
                                            int insulin2 = Math.round(insulineinheiten);
                                            insulin.setText("Insulineinheiten: " + insulin2);
                                            barcodereuse = barcodeData;
                                        }

                                        break;
                                    } else {
                                        barcodeText.setText(barcodeData);
                                        kohlenhydrateText.setText("Produkt unbekannt");
                                        be.setText("Berechnung fehlgeschlagen");
                                        barcodereuse = barcodeData;
                                    }

                                }
                            } else {

                                barcodeData = barcodes.valueAt(0).displayValue;
                                barcodereuse = barcodeData;
                                for (int i = 0; i < testarray.length; i++) {


                                    if (barcodeData.equals(testarray[i][0])) {
                                        barcodereuse = testarray[i][0];

                                        barcodeText.setText("Produkt: " + testarray[i][1]);
                                        kohlenhydrateText.setText("Kohlenhydrate in g/100g: " + testarray[i][2]);
                                        String menge1 = essen.getText().toString();

                                        if (menge1.matches("")) {
                                            Toast.makeText(barcode.this, "Geben Sie die Menge ein", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                        else{
                                            float menge2 = Float.parseFloat(menge1);
                                            float kalorien = Float.parseFloat(testarray[i][2]);
                                            float brot = (kalorien / 100) * (menge2 / 12);
                                            String brot2 = String.format("%.02f", brot);
                                            be.setText("Broteinheiten: " + brot2);
                                            SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                            float b = sh.getFloat("befaktor", 0);
                                            float insulineinheiten = b * brot;
                                            int insulin2 = Math.round(insulineinheiten);
                                            insulin.setText("Insulineinheiten: " + insulin2);
                                            barcodereuse = barcodeData;
                                        }
                                        break;
                                    } else {
                                        barcodeText.setText(barcodeData);
                                        kohlenhydrateText.setText("Produkt unbekannt");
                                        be.setText("Berechnung fehlgeschlagen");
                                        barcodereuse = barcodeData;
                                    }

                                }
                            }
                        }


                    });
                }

            }

        });
    }





    @Override
    protected void onPause() {
        super.onPause();
        getSupportActionBar().hide();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
        initialiseDetectorsAndSources();
    }

}
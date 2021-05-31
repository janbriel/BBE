package com.example.bbe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.text.BreakIterator;

public class barcode extends AppCompatActivity {


    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    private TextView barcodeText;
    private TextView kohlenhydrateText;
    private String barcodeData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_barcode);
        setContentView(R.layout.activity_barcode);
        surfaceView = findViewById(R.id.surface_view);
        barcodeText = findViewById(R.id.barcode_text);
        kohlenhydrateText = findViewById(R.id.kohlenhydrate_text);
        initialiseDetectorsAndSources();
    }

    private void initialiseDetectorsAndSources() {

        //Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

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

                            String testarray [] [] = new String [][]
                                    {{"9006900014858","Der Grüne", "NULL"},
                                    {"7311250079993","Zyn Deep Freeze", "NULL"},
                                    {"4031300252932","Velo Snus", "NULL"},
                                    {"7612100017043","Flasche", "NULL"},
                                    {"5060166692285","Monster Energy", "12"},
                                    {"8000500310427", "Nutella biscuits", "63,3"},
                                    {"5449000236623", "Fuze Tea Peach", "4,5"},
                                            {"42104643", "Cappy Ice Fruits", "6,7"}};

                            if (barcodes.valueAt(0).email != null) {
                                barcodeText.removeCallbacks(null);
                                barcodeData = barcodes.valueAt(0).email.address;
                                for(int i = 0; i < testarray.length; i++) {

                                        if (barcodeData.equals(testarray[i][0])) {
                                            barcodeText.setText("Produkt: " + testarray[i][1]);
                                            kohlenhydrateText.setText("Kohlenhydrate in g/100g: " + testarray[i][2]);
                                            break;
                                        } else {
                                            barcodeText.setText(barcodeData);
                                            kohlenhydrateText.setText("Produkt unbekannt");

                                        }

                                }
                            }
                            else {

                                barcodeData = barcodes.valueAt(0).displayValue;

                                    for(int i = 0; i < testarray.length; i++) {

                                            if (barcodeData.equals(testarray[i][0])) {
                                                barcodeText.setText("Produkt: " + testarray[i][1]);
                                                kohlenhydrateText.setText("Kohlenhydrate in g/100g: " + testarray[i][2]);
                                                break;
                                            } else {
                                                barcodeText.setText(barcodeData);
                                                kohlenhydrateText.setText("Produkt unbekannt");
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
package com.example.flashlightapp;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isFlashOn = false;
    private CameraManager cameraManager;
    private String cameraId;
    private Button flashlightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashlightButton = findViewById(R.id.flashlightButton);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        flashlightButton.setOnClickListener(v -> {
            try {
                isFlashOn = !isFlashOn;
                cameraManager.setTorchMode(cameraId, isFlashOn);
                flashlightButton.setText(isFlashOn ? "Turn OFF" : "Turn ON");
                flashlightButton.setBackgroundColor(
                        getResources().getColor(
                                isFlashOn ? android.R.color.holo_red_light : android.R.color.holo_green_light
                        )
                );
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        });
    }
}

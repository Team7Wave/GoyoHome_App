package com.example.goyohome_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] deviceNames = {"에어컨", "TV", "가습기", "스피커", "공기청정기"};
    String[] deviceLocations = {"거실", "안방", "아이 방", "부엌", "현관"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDeviceText(R.id.device1, deviceNames[0], deviceLocations[0]);
        setDeviceText(R.id.device2, deviceNames[1], deviceLocations[1]);
        setDeviceText(R.id.device3, deviceNames[2], deviceLocations[2]);
        setDeviceText(R.id.device4, deviceNames[3], deviceLocations[3]);
        setDeviceText(R.id.device5, deviceNames[4], deviceLocations[4]);
    }

    private void setDeviceText(int layoutId, String name, String location) {
        View deviceView = findViewById(layoutId);
        TextView nameText = deviceView.findViewById(R.id.deviceName);
        TextView locationText = deviceView.findViewById(R.id.deviceLocation);
        nameText.setText(name);
        locationText.setText(location);
    }
}

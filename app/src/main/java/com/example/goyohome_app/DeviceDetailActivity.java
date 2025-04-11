package com.example.goyohome_app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DeviceDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        TextView title = findViewById(R.id.deviceDetailTitle);
        TextView location = findViewById(R.id.deviceDetailLocation);

        String name = getIntent().getStringExtra("deviceName");
        String loc = getIntent().getStringExtra("deviceLocation");

        title.setText(name);
        location.setText(loc);
    }
}

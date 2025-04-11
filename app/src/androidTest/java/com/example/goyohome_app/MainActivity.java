package com.example.goyohome_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 기기 이름과 위치 배열
    String[] deviceNames = {"에어컨", "TV", "가습기", "스피커", "공기청정기"};
    String[] deviceLocations = {"거실", "안방", "아이 방", "부엌", "현관"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각 카드에 이름/위치 설정 + 클릭 이벤트 연결
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

        // ✅ 카드 클릭 시 상세 화면으로 이동
        deviceView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeviceDetailActivity.class);
            intent.putExtra("deviceName", name);
            intent.putExtra("deviceLocation", location);
            startActivity(intent);
        });
    }
}

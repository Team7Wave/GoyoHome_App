package com.example.goyohome_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private LinearLayout deviceContainer;
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "DevicePrefs";
    private static final String KEY_IMEIS = "imei_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceContainer = findViewById(R.id.deviceContainer);
        Button addDeviceButton = findViewById(R.id.addDeviceButton);
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadSavedDevices();

        addDeviceButton.setOnClickListener(v -> showImeiDialog());
    }

    // IMEI 입력 다이얼로그
    private void showImeiDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.imei_dialog, null);
        EditText imeiEdit = dialogView.findViewById(R.id.imeiEditText);
        ImageView clearBtn = dialogView.findViewById(R.id.clearButton);
        TextView btnYes = dialogView.findViewById(R.id.btnYes);
        TextView btnNo = dialogView.findViewById(R.id.btnNo);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        clearBtn.setOnClickListener(v -> imeiEdit.setText(""));
        btnNo.setOnClickListener(v -> dialog.dismiss());

        btnYes.setOnClickListener(v -> {
            String imei = imeiEdit.getText().toString().trim();
            if (!imei.isEmpty()) {
                addDeviceCard(imei); // 🔧 이 부분도 showDeleteDialog 포함하도록 구성됨
                saveImei(imei);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "기기 번호를 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    // 핑크 카드 추가 + 삭제 다이얼로그 연결
    private void addDeviceCard(String imei) {
        View card = LayoutInflater.from(this).inflate(R.layout.device_item, deviceContainer, false);
        TextView nameText = card.findViewById(R.id.deviceName);
        TextView locationText = card.findViewById(R.id.deviceLocation);

        nameText.setText(imei);
        locationText.setText("위치");

        // 삭제 다이얼로그 연결
        card.setOnClickListener(v -> showDeleteDialog(card, imei));

        // 🆕 위치 수정 다이얼로그 연결
        locationText.setOnClickListener(v -> showEditLocationDialog(locationText));

        deviceContainer.addView(card);
    }


    // SharedPreferences 저장
    private void saveImei(String imei) {
        Set<String> imeiSet = prefs.getStringSet(KEY_IMEIS, new HashSet<>());
        Set<String> newSet = new HashSet<>(imeiSet);
        newSet.add(imei);
        prefs.edit().putStringSet(KEY_IMEIS, newSet).apply();
    }

    // SharedPreferences 삭제
    private void deleteImei(String imei) {
        Set<String> imeiSet = prefs.getStringSet(KEY_IMEIS, new HashSet<>());
        Set<String> newSet = new HashSet<>(imeiSet);
        newSet.remove(imei);
        prefs.edit().putStringSet(KEY_IMEIS, newSet).apply();
    }

    // 앱 실행 시 저장된 카드 로드
    private void loadSavedDevices() {
        Set<String> imeiSet = prefs.getStringSet(KEY_IMEIS, new HashSet<>());
        for (String imei : imeiSet) {
            addDeviceCard(imei);
        }
    }

    // 삭제 다이얼로그
    private void showDeleteDialog(View card, String imei) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.delete_dialog, null);
        TextView btnYes = dialogView.findViewById(R.id.btnYes);
        TextView btnNo = dialogView.findViewById(R.id.btnNo);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        btnNo.setOnClickListener(v -> dialog.dismiss());

        btnYes.setOnClickListener(v -> {
            deviceContainer.removeView(card);  // 화면에서 삭제
            deleteImei(imei);                  // 저장된 값에서도 삭제
            dialog.dismiss();
        });

        dialog.show();
    }

    private void showEditLocationDialog(TextView locationText) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.edit_location_dialog, null);
        EditText locationEdit = dialogView.findViewById(R.id.locationEditText);
        ImageView clearBtn = dialogView.findViewById(R.id.clearButton);
        TextView btnYes = dialogView.findViewById(R.id.btnYes);
        TextView btnNo = dialogView.findViewById(R.id.btnNo);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create();

        clearBtn.setOnClickListener(v -> locationEdit.setText(""));
        btnNo.setOnClickListener(v -> dialog.dismiss());

        btnYes.setOnClickListener(v -> {
            String newLocation = locationEdit.getText().toString().trim();
            if (!newLocation.isEmpty()) {
                locationText.setText(newLocation);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "위치를 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

}

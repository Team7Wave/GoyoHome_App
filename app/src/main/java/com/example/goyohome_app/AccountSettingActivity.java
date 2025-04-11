package com.example.goyohome_app;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;

public class AccountSettingActivity extends AppCompatActivity {
    EditText nicknameText;
    Button nicknameBtn;
    TextView startTimeText, endTimeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);

        nicknameText = findViewById(R.id.nicknameText);     // '닉네임 변경' 부분
        nicknameBtn = findViewById(R.id.nicknameBtn);

        startTimeText = findViewById(R.id.startTimeText);   // '시간 설정' 부분
        endTimeText = findViewById(R.id.endTimeText);

        nicknameBtn.setOnClickListener(new View.OnClickListener() {     // 버튼 클릭 리스너
            @Override
            public void onClick(View v) {                // trim = 공백 제거
                String nickname = nicknameText.getText().toString().trim();
                if (!nickname.isEmpty()) {
                    Toast.makeText(AccountSettingActivity.this, "닉네임이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AccountSettingActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        startTimeText.setOnClickListener(new View.OnClickListener() {     // 시작 시간 클릭 리스너
            @Override
            public void onClick(View v) {
                showTimePickerDialog(startTimeText);
            }
        });
        endTimeText.setOnClickListener(new View.OnClickListener() {      // 종료 시간 클릭 리스너
            @Override
            public void onClick(View v) {   // 종료 시간 클릭 리스너
                showTimePickerDialog(endTimeText);
            }
        });
    }

    public void showTimePickerDialog(TextView targetView) {    // 시간 선택 다이얼로그 메서드
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AccountSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                        String amPm = (hourOfDay >= 12) ? "PM" : "AM";       // 12시 넘어가면 오후, 아니면 오전 구분
                        int timeHour = (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;    // AM, PM 형식 구분
                        if (timeHour == 0) timeHour = 12;       // 0시 = 12시 (자정 예외)
                        String selectedTime = String.format(Locale.getDefault(), "%d:%02d %s", timeHour, minute1, amPm);   // 시간 문자열 생성
                        targetView.setText(selectedTime);       // TextView에 표시
                    }
                }, hour, minute, false); // false = AM, PM 형식, true = 24시간 형식
        timePickerDialog.show();  // 다이얼로그 화면에 표시
    }
}

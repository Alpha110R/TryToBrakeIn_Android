package com.example.targil1_mobilesecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textInput_UserPassword;
    private MaterialButton button_getPassword;
    private int batteryLevel, currentYear;
    private TextView checkTextOfPass;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        batteryLevel = getBatteryLevel();
        currentYear = getYear();
        Log.d("tagg","BatteryManager: " + batteryLevel + " THe Time is: " + currentYear + " is wifi on: " + isWifiOn());

        button_getPassword.setOnClickListener(view -> {
            String passwordFromUser = textInput_UserPassword.getText().toString();

            if(checkUserPassword(passwordFromUser))
                checkTextOfPass.setText("You Succeed!");
            else
                checkTextOfPass.setText("Not Good Password, Try Again");

        });
    }

    private void findViews() {
        textInput_UserPassword = findViewById(R.id.textInput_UserPassword);
        button_getPassword = findViewById(R.id.button_getPassword);
        checkTextOfPass = findViewById(R.id.checkTextOfPass);
    }
    private int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    private int getBatteryLevel(){
        BatteryManager bm = (BatteryManager) this.getSystemService(BATTERY_SERVICE);
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }
    private boolean isWifiOn(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        return nInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    private boolean checkUserPassword(String password){
        String systemPassword = batteryLevel + String.valueOf(currentYear);
        return password.equals(systemPassword) && isWifiOn();
    }

}
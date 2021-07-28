package com.example.magicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.magicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    SharedPreferences sharedPreferences;
    private static final String KEY_USERNAME ="username";
    private static final String KEY_PASSWORD="pass";
    private static final String KEY_SHAREDPREFENCES="myPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main)

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        sharedPreferences = getSharedPreferences(KEY_SHAREDPREFENCES,MODE_PRIVATE);

        mainBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mainBinding.ETUserName.getText()) && TextUtils.isEmpty(mainBinding.EtPassword.getText())){
                    Toast.makeText(MainActivity.this, "Please fill the above fields", Toast.LENGTH_SHORT).show();
                }else{
                    saveData();
                }
            }
        });

        String name = sharedPreferences.getString(KEY_USERNAME,null);
        String pass = sharedPreferences.getString(KEY_PASSWORD,null);

        if (name != null && pass != null){
            startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
        }


    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME,mainBinding.ETUserName.getText().toString());
        editor.putString(KEY_PASSWORD,mainBinding.EtPassword.getText().toString());
        editor.apply();

        Intent welcomeIntent = new Intent(MainActivity.this,WelcomeActivity.class);
        startActivity(welcomeIntent);
        finish();
    }
}
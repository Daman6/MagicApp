package com.example.magicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.magicapp.databinding.ActivityMainBinding;
import com.example.magicapp.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding welcomeBinding;

    SharedPreferences sharedPreferences;
    private static final String KEY_USERNAME ="username";
    private static final String KEY_SHAREDPREFENCES="myPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);

        welcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = welcomeBinding.getRoot();
        setContentView(view);

        sharedPreferences = getSharedPreferences(KEY_SHAREDPREFENCES,MODE_PRIVATE);

        String username  = sharedPreferences.getString(KEY_USERNAME,null);

        if (username != null){
            welcomeBinding.textViewwel.setText( "Welcome "+ username);
        }

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new WelcomeFragment()).commit();
        }

        welcomeBinding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(WelcomeActivity.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        welcomeBinding.welcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new WelcomeFragment());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
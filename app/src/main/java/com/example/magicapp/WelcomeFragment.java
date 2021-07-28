package com.example.magicapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magicapp.databinding.FragmentWelcomeBinding;


public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding fragmentWelcomeBinding;
    SharedPreferences sharedPreferences;
    private static final String KEY_USERNAME ="username";
    private static final String KEY_SHAREDPREFENCES="myPref";

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentWelcomeBinding =  FragmentWelcomeBinding.inflate(inflater,container,false);
        View view = fragmentWelcomeBinding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(KEY_SHAREDPREFENCES, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_USERNAME,null);

        if (name != null){
            fragmentWelcomeBinding.welcomeText.setText("Welcome "+name);
        }

        fragmentWelcomeBinding.taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TaskFragment());
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentWelcomeBinding = null;
    }
}
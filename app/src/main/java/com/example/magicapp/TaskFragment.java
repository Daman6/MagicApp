package com.example.magicapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magicapp.databinding.FragmentTaskBinding;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {

    private FragmentTaskBinding fragmentTaskBinding;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTaskBinding = FragmentTaskBinding.inflate(inflater,container,false);
        View view = fragmentTaskBinding.getRoot();
        context = container.getContext();
        String[] mystring = getResources().getStringArray(R.array.task_Name);
        String[] myImages = getResources().getStringArray(R.array.task_Img);

        List<Task> taskList =  new ArrayList<>();
        taskList.add(new Task(mystring[0],myImages[0]));
        taskList.add(new Task(mystring[1],myImages[1]));
        taskList.add(new Task(mystring[2],myImages[2]));
        taskList.add(new Task(mystring[3],myImages[3]));
        taskList.add(new Task(mystring[4],myImages[4]));
        taskList.add(new Task(mystring[5],myImages[5]));
        taskList.add(new Task(mystring[6],myImages[6]));
        taskList.add(new Task(mystring[7],myImages[7]));
        taskList.add(new Task(mystring[8],myImages[8]));
        taskList.add(new Task(mystring[9],myImages[9]));

        fragmentTaskBinding.taskRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        fragmentTaskBinding.taskRecyclerview.setAdapter(new TaskAdapter(taskList,context));
        return view;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentTaskBinding = null;
    }
}
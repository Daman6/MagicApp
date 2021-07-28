package com.example.magicapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.magicapp.databinding.CustomDialogBinding;
import com.example.magicapp.databinding.TaskItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private Context context;
    private Dialog dialog;

    public TaskAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       return new TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAdapter.TaskViewHolder holder, int position) {

        holder.setImage(taskList.get(position));
        holder.setTitle(taskList.get(position));

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);

        Button okayBtn = (Button) dialog.findViewById(R.id.okayBtn);
        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView taskname = (TextView) dialog.findViewById(R.id.tasknameTv);
                taskname.setText(taskList.get(holder.getAdapterPosition()).getTaskTitle());
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        TaskItemBinding taskItemBinding;
        public TaskViewHolder(TaskItemBinding binding) {
            super(binding.getRoot());
            taskItemBinding = binding;
        }
        void setTitle(Task task){
            taskItemBinding.taskTitleTv.setText(task.getTaskTitle());
        }

        void setImage(Task task){
            Glide.with(context).load(task.getTaskImage()).into(taskItemBinding.taskImageIV);
        }

    }
}

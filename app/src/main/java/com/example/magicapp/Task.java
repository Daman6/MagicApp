package com.example.magicapp;

public class Task {
    private String taskTitle;
    private String taskImage;

    public Task() {
    }

    public Task(String taskTitle, String taskImage) {
        this.taskTitle = taskTitle;
        this.taskImage = taskImage;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskImage() {
        return taskImage;
    }
}

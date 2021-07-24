package com.alamat.todolist.DataBaseUtils.dataBaseModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity

public class ToDoModel extends ArrayList<ToDoModel> {

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo
        String todoTitle;

        @ColumnInfo
        String todoContect;

        @ColumnInfo(defaultValue = "false", index = false)
        public
        boolean todoState;

        public ToDoModel() {
        }

        @Ignore
        public ToDoModel(String todoTitle, String todoContect, boolean todoState) {
            this.todoTitle = todoTitle;
            this.todoContect = todoContect;
            this.todoState = todoState;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoTitle() {
            return todoTitle;
        }

        public void setTodoTitle(String todoTitle) {
            this.todoTitle = todoTitle;
        }

        public String getTodoContect() {
            return todoContect;
        }

        public void setTodoContect(String todoContect) {
            this.todoContect = todoContect;
        }

    public boolean isState() {
        return todoState;
    }

    public void setState(boolean state) {
        this.todoState = todoState;
    }
}
package com.alamat.todolist.DataBaseUtils;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;

@Database(entities = {ToDoModel.class},version = 1,exportSchema = false)

public abstract class RoomDataBase extends RoomDatabase {

    private static RoomDataBase roomDataBase;
    private static final String DBName = "TodoListDataBase";

    public abstract TodoDao todoDao();

    public static RoomDataBase getInstance(Context context){
        if (roomDataBase == null){
            roomDataBase = Room.databaseBuilder(context ,
                    RoomDataBase.class,DBName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDataBase;
    }

}

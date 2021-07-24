package com.alamat.todolist.DataBaseUtils;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface TodoDao
{
    @Insert
    void insertTodo(ToDoModel todoModel);

    @Query("select * from todomodel;")
    List<ToDoModel> getAllTodo();

    @Query("delete from todomodel where `id` = :id")
    void deleteItem(int id);

    @Query("delete from todomodel;")
    void deleteAll();

    @Query("update todomodel set todoTitle = :todoTitle,todoContect = :todoContect where `id` = :id")
    void updateItem(String todoTitle,String todoContect, int id);

    @Query("select * from todomodel where todoTitle LIKE '%' || :seachedStatment || '%' " +
            "or todoContect LIKE '%' || :seachedStatment || '%' ")
    List<ToDoModel> searchItem(String seachedStatment);

    @Query("update todomodel set todoState = :todoState where `id` = :id")
    void checkedState(boolean todoState, int id);

}

package com.alamat.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.todolist.DataBaseUtils.RoomDataBase;
import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;
import com.alamat.todolist.databinding.ActivitySaveToDoListBinding;

public class SaveToDoList extends AppCompatActivity {

    ActivitySaveToDoListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_save_to_do_list);

        binding.btnSaveTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTodo();
            }
        });
    }

    public void insertTodo(){
        ToDoModel todoModel = new ToDoModel(binding.edTodoTile.getText().toString(),
                binding.edTodoContent.getText().toString(),false);
        RoomDataBase.getInstance(this).todoDao().insertTodo(todoModel);
        finish();
    }

    public void back(View view)
    {
        Intent intent = new Intent(SaveToDoList.this, MainAppActivity.class);
        startActivity(intent);
    }
}
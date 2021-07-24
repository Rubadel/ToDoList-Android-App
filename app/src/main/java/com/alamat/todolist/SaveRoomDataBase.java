package com.alamat.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.alamat.todolist.DataBaseUtils.RoomDataBase;
import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;
import com.alamat.todolist.databinding.ActivitySaveRoomDataBaseBinding;

public class SaveRoomDataBase extends AppCompatActivity {

    ActivitySaveRoomDataBaseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_save_room_data_base);

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
}
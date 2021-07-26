package com.alamat.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alamat.todolist.DataBaseUtils.RoomDataBase;
import com.alamat.todolist.databinding.ActivityUpdateToDoListBinding;

public class UpdateToDoList extends AppCompatActivity {

    ActivityUpdateToDoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_update_to_do_list);

        int id = getIntent().getExtras().getInt("id");
        binding.edTodoTile.setText(getIntent().getExtras().getString("title"));
        binding.edTodoContent.setText(getIntent().getExtras().getString("content"));

        updateItem(id);
    }

    private void updateItem(int id)
    {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("TAG", "the id is: "+ id);
                String newtitle = binding.edTodoTile.getText().toString().trim();
                String newcontent = binding.edTodoContent.getText().toString().trim();
                RoomDataBase.getInstance(getApplicationContext()).todoDao().updateItem(newtitle,newcontent,id);
                finish();
            }
        });
    }

    public void back(View view)
    {
        Intent intent = new Intent(UpdateToDoList.this, MainAppActivity.class);
        startActivity(intent);
    }
}
package com.alamat.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alamat.todolist.DataBaseUtils.RoomDataBase;
import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;
import com.alamat.todolist.databinding.MainAppActivityBinding;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainAppActivity extends AppCompatActivity {

    MainAppActivityBinding binding;

    RecyclerView.LayoutManager layoutManager;
    ToDoRoomAdapter adapter;

   public List<ToDoModel> toDoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_app_activity);

        //recycler setup
        adapter = new ToDoRoomAdapter( null,getApplicationContext());
        layoutManager = new GridLayoutManager(this,2);
        binding.recyclerViewToDo.setLayoutManager(layoutManager);
        binding.recyclerViewToDo.setAdapter(adapter);
        //</.>

        addItem();
        getData();
        deleteItem();
        deleteAll();
        update();
        search();
    }

    @Override
    protected void onStart() {
        super.onStart();

        addItem();
        getData();
        deleteItem();
        deleteAll();
        update();
        search();
    }

    private void addItem(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, SaveToDoList.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        adapter.notifyDataSetChanged();
        toDoModelList = RoomDataBase.getInstance(this).todoDao().getAllTodo();
        adapter = new ToDoRoomAdapter(toDoModelList,getApplicationContext());
        binding.recyclerViewToDo.setAdapter(adapter);
    }

    private void deleteItem(){
        adapter.setItemClickListener(new ToDoRoomAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int position, int id) {
                RoomDataBase.getInstance(getApplicationContext()).todoDao().deleteItem(id);
                toDoModelList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }

    private void deleteAll(){
        adapter.notifyDataSetChanged();
        binding.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomDataBase.getInstance(getApplicationContext()).todoDao().deleteAll();
                toDoModelList.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void update() {

        adapter.setOnItemUpdateListener(new ToDoRoomAdapter.OnItemClickedListenerU() {
            @Override
            public void onItemUpdate(int position, int id) {
                Intent intent = new Intent(getApplicationContext(), UpdateToDoList.class);
                intent.putExtra("title", String.valueOf(toDoModelList.get(position).getTodoTitle()));
                intent.putExtra("content", String.valueOf(toDoModelList.get(position).getTodoContect()));
                intent.putExtra("id", Integer.valueOf(toDoModelList.get(position).getId()));
                startActivity(intent);
            }
        });
    }

    private void search(){
        binding.edSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tilte = binding.edSearch.getText().toString();
                toDoModelList = RoomDataBase.getInstance(getApplicationContext()).todoDao().searchItem(tilte);
                adapter = new ToDoRoomAdapter(toDoModelList,getApplicationContext());
                binding.recyclerViewToDo.setAdapter(adapter);
            }
        });


    }

}
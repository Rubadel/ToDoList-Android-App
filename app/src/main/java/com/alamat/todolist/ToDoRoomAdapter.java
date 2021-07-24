package com.alamat.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.todolist.DataBaseUtils.RoomDataBase;
import com.alamat.todolist.DataBaseUtils.dataBaseModels.ToDoModel;
import com.alamat.todolist.databinding.ItemToDoListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ToDoRoomAdapter extends RecyclerView.Adapter<ToDoRoomAdapter.ViewHolder> {

    List<ToDoModel> todoModels;

    Context context;
    //constructor
    public ToDoRoomAdapter(List<ToDoModel> todoModels, Context context) {
        this.todoModels = todoModels;
        this.context = context;
    }


    //    Delete interface
    OnItemClickedListenerD deleteItemClickListener;
    public void setDeleteItemClickListener(OnItemClickedListenerD deleteItemClickListener) {
        this.deleteItemClickListener = deleteItemClickListener;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemToDoListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_to_do_list,parent,false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoModel todoModel = todoModels.get(position);
        holder.itemTodoRoomBinding.tvTodoTitle.setText(todoModel.getTodoTitle());
        holder.itemTodoRoomBinding.tvTodoContent.setText(todoModel.getTodoContect());
        //for delete
        holder.itemTodoRoomBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (deleteItemClickListener != null) {
                    deleteItemClickListener.onItemDelete(position, todoModels.get(position).getId());
                }
            }
        });

        //for update
     holder.itemTodoRoomBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             deleteItemClickListener.onItemDelete(position, todoModels.get(position).getId());
         }
     });
    }

    @Override
    public int getItemCount() {
        if (todoModels==null){
            return 0;

        }else {
            return todoModels.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemToDoListBinding itemTodoRoomBinding;

        public ViewHolder(@NonNull ItemToDoListBinding itemView) {
            super(itemView.getRoot());
            this.itemTodoRoomBinding = itemView;
        }
    }

    //INTERFACE
    public interface OnItemClickedListenerD{
        void onItemDelete(int position, int id);
    }
}

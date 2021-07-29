package com.alamat.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
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


    //interfaces
    OnItemClickedListener onItemClick;
    public void setItemClickListener(OnItemClickedListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    OnItemClickedListenerU onItemUpdate;
    public void setOnItemUpdateListener(OnItemClickedListenerU onItemUpdate) {
        this.onItemUpdate = onItemUpdate;
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

        if (todoModels.get(position).isState() == true )
        {
            holder.itemTodoRoomBinding.checkBox.setTextColor(context.getResources().getColor(R.color.white));
            holder.itemTodoRoomBinding.checkBox.setChecked(true);
        }
        else { holder.itemTodoRoomBinding.checkBox.setTextColor(context.getResources().getColor(R.color.black)); }

        //for delete
        holder.itemTodoRoomBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClick != null) {
                    onItemClick.onItemClick(position, todoModels.get(position).getId());
                }
            }
        });

//        //for update
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(onItemUpdate !=null) {
                 onItemUpdate.onItemUpdate(position, todoModels.get(position).getId());
             }
         }
     });

     //for state
        holder.itemTodoRoomBinding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = todoModels.get(position).getId();

                if (holder.itemTodoRoomBinding.checkBox.isChecked()){
                holder.itemTodoRoomBinding.checkBox.setTextColor(context.getResources().getColor(R.color.white));
                RoomDataBase.getInstance(context.getApplicationContext()).todoDao().checkedState(true,id);
            }
                else
                {
                    holder.itemTodoRoomBinding.checkBox.setTextColor(context.getResources().getColor(R.color.black));
                    RoomDataBase.getInstance(context.getApplicationContext()).todoDao().checkedState(false,id);
                }

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

    //INTERFACES
    public interface OnItemClickedListener{
        void onItemClick(int position, int id);
    }

    public interface OnItemClickedListenerU{
        void onItemUpdate(int position, int id);
    }

}

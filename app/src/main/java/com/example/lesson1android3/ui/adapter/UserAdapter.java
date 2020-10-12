package com.example.lesson1android3.ui.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.OnItemClickListener;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.ui.adapter.main.BaseRecyclerViewAdapter;

import java.util.ArrayList;

public class UserAdapter extends BaseRecyclerViewAdapter<PostsModel> {

    protected OnItemClickListener listener;

    public UserAdapter(ArrayList<PostsModel> list, OnItemClickListener listener) {
        super(list);
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, R.layout.list_user);
    }

    @Override
    protected void bind(PostsModel model, BaseViewHolder holder) {
        TextView textView = holder.itemView.findViewById(R.id.textUser_listUser);
        textView.setText(model.getUser());
        holder.itemView.setOnClickListener(view -> {
            listener.onItemViewPostFragment(holder.getAdapterPosition());
        });
    }
}

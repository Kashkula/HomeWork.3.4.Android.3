package com.example.lesson1android3.ui.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.OnItemClickListener;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.ui.adapter.main.BaseRecyclerViewAdapter;

import java.util.ArrayList;

public class PostAdapter extends BaseRecyclerViewAdapter<PostsModel> {

    private OnItemClickListener listener;

    public PostAdapter(ArrayList<PostsModel> list, OnItemClickListener listener) {
        super(list);
        this.listener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, R.layout.list_post);
    }

    @Override
    protected void bind(PostsModel model, BaseViewHolder holder) {
        TextView textTitle = holder.itemView.findViewById(R.id.textTitle_listPost);
        TextView textContent = holder.itemView.findViewById(R.id.textContent_listPost);
        textContent.setText(model.getContent());
        textTitle.setText(model.getTitle());
        holder.itemView.setOnClickListener(view -> listener.onItemViewPostFragment(holder.getAdapterPosition()));
        holder.itemView.setOnLongClickListener(view -> {
            listener.onLongItemView(holder.getAdapterPosition());
            return true;
        });
    }
}
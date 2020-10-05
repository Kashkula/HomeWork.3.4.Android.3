package com.example.lesson1android3.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.OnItemClickListener;
import com.example.lesson1android3.data.model.PostsModel;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    protected ArrayList<PostsModel> list;
    protected OnItemClickListener listener;


    public PostAdapter(ArrayList<PostsModel> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView textTitle;
        protected TextView textContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle_listPost);
            textContent = itemView.findViewById(R.id.textContent_listPost);
            itemView.setOnLongClickListener(view -> {
                listener.onLongItemView(getAdapterPosition());
                return true;
            });
            itemView.setOnClickListener(view -> {
                listener.onItemView(getAdapterPosition());
            });

        }

        public void bind(PostsModel postsModel) {
            textTitle.setText(postsModel.getTitle());
            textContent.setText(postsModel.getContent());
        }
    }
}

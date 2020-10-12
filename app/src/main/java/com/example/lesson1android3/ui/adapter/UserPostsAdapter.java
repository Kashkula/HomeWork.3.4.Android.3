package com.example.lesson1android3.ui.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.ui.adapter.main.BaseRecyclerViewAdapter;

import java.util.ArrayList;

public class UserPostsAdapter extends BaseRecyclerViewAdapter<PostsModel> {

    public UserPostsAdapter(ArrayList<PostsModel> list) {
        super(list);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, R.layout.list_user_posts);
    }

    @Override
    protected void bind(PostsModel model, BaseViewHolder holder) {
        TextView textTitle = holder.itemView.findViewById(R.id.textTitle_ListUserActivity);
        TextView textContent = holder.itemView.findViewById(R.id.textContent_ListUserActivity);
        textTitle.setText(model.getTitle());
        textContent.setText(model.getContent());
    }
}

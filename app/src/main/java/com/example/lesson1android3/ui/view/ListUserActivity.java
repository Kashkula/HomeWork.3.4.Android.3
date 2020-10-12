package com.example.lesson1android3.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.data.network.PostApiService;
import com.example.lesson1android3.ui.adapter.UserPostsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserActivity extends AppCompatActivity {

    protected UserPostsAdapter adapter;
    @BindView(R.id.recyclerView_ListUserActivity)
    RecyclerView recyclerView;
    protected ArrayList<PostsModel> list;
    protected String position;

    @Override
    protected void onResume() {
        super.onResume();
        getUserPosts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        ButterKnife.bind(this);
        init();
        getUserPosts();

        getPosition();
    }

    private void getPosition() {
        Intent intent = getIntent();
        position = intent.getStringExtra("op");
    }

    private void getUserPosts() {
        //noinspection NullableProblems
        PostApiService.getInstance().getPostApi().getUserPosts(position).enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                list.clear();
                if (response.isSuccessful() && response.body() != null) {
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {

            }
        });
    }

    private void init() {
        if (list == null)
            list = new ArrayList<>();
        adapter = new UserPostsAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}
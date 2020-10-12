package com.example.lesson1android3.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.data.network.PostApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class ChangeActivity extends AppCompatActivity {

    @BindView(R.id.editTitle_changeActivity)
    EditText editTitle;
    @BindView(R.id.editContent_changeActivity)
    EditText editContent;
    @BindView(R.id.editUser_changeActivity)
    EditText editUser;
    @BindView(R.id.editGroup_changeActivity)
    EditText editGroup;

    @BindView(R.id.btnChange_changeActivity)
    Button btnChange;

    protected Integer position;
    protected PostsModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        init();
        intentGet();
        getPost();
        btnChange.setOnClickListener(view -> {
            model.setContent(editContent.getText().toString().trim());
            model.setGroup(editGroup.getText().toString().trim());
            model.setUser(editUser.getText().toString().trim());
            model.setTitle(editTitle.getText().toString().trim());

            PostApiService.getInstance().getPostApi().updatePost(position, model).enqueue(new Callback<PostsModel>() {
                @Override
                public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                    Toast.makeText(ChangeActivity.this, "Model is successly!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<PostsModel> call, Throwable t) {
                    Toast.makeText(ChangeActivity.this, "Model is failed!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void intentGet() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            position = (Integer) intent.getSerializableExtra("po");
        }
    }

    private void getPost() {
        PostApiService.getInstance()
                .getPostApi()
                .getPostWithID(position)
                .enqueue(new Callback<PostsModel>() {
                    @Override
                    public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            editTitle.setText(response.body().getTitle());
                            editContent.setText(response.body().getContent());
                            editUser.setText(response.body().getUser());
                            editGroup.setText(response.body().getGroup());
                        } else {
                            Toast.makeText(ChangeActivity.this, "Пустые данные", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostsModel> call, Throwable t) {
                        Toast.makeText(ChangeActivity.this, "Проблемы с сервером!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void init() {
        model = new PostsModel();
    }
}
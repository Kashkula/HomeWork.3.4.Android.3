package com.example.lesson1android3.ui.view;

import android.os.Bundle;
import android.view.View;
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

public class SendActivity extends AppCompatActivity {

    @BindView(R.id.editTitle_sendActivity)
    EditText editTitle;
    @BindView(R.id.editContent_sendActivity)
    protected EditText editContent;
    @BindView(R.id.editGroup_sendActivity)
    protected EditText editGroup;
    @BindView(R.id.editUser_sendActivity)
    protected EditText editUser;

    protected PostsModel postsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        init();
        ButterKnife.bind(this);
    }

    private void init() {
        postsModel = new PostsModel();
    }

    public void onClick(View view) {
        if (!editTitle.getText().toString().isEmpty() &&
                !editContent.getText().toString().isEmpty() &&
                !editUser.getText().toString().isEmpty() &&
                !editGroup.getText().toString().isEmpty()) {
            postsModel.setTitle(editTitle.getText().toString().trim());
            postsModel.setContent(editContent.getText().toString().trim());
            postsModel.setUser(editUser.getText().toString().trim());
            postsModel.setGroup(editGroup.getText().toString().trim());
            //noinspection NullableProblems
            PostApiService.getInstance().getPostApi().postData(postsModel).enqueue(new Callback<PostsModel>() {
                @Override
                public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                    Toast.makeText(SendActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<PostsModel> call, Throwable t) {
                    Toast.makeText(SendActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
            finish();
        } else {
            editTitle.setError("Заполните поле!");
            editContent.setError("Заполните поле!");
            editUser.setError("Заполните поле!");
            editGroup.setError("Заполните поле!");
        }
    }
}
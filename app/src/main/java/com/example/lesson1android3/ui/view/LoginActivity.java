package com.example.lesson1android3.ui.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.local.PreferenceUtils;
import com.example.lesson1android3.data.model.UserModel;
import com.example.lesson1android3.data.network.AuthApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.editLogin_loginActivity)
    EditText editLogin;

    @BindView(R.id.editPassword_loginActivity)
    EditText editPassword;

    @BindView(R.id.btnEnter_loginActivity)
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        onClick();
    }

    private void onClick() {
        btnEnter.setOnClickListener(view -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            if (!login.isEmpty() && !password.isEmpty()) {
                String id = Credentials.basic(login, password);
                //noinspection NullableProblems
                AuthApiService.getInstance().getAuthApi().getUser(id).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        Toast.makeText(LoginActivity.this, "id sand", Toast.LENGTH_SHORT).show();
                        PreferenceUtils.init(LoginActivity.this);
                        PreferenceUtils.saveLoginLine(login);
                        PreferenceUtils.savePasswordLine(password);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "id didn't send", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                editLogin.setError("Заполните поле!");
                editPassword.setError("Заполните поле!");
            }
        });
    }
}
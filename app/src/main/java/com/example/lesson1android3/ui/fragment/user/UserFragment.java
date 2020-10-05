package com.example.lesson1android3.ui.fragment.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.SetCurrentItemListener;
import com.example.lesson1android3.data.local.PreferenceUtils;

public class UserFragment extends Fragment {

    private EditText editLogin, editPassword;
    private SetCurrentItemListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        onClickEnter(view);
    }

    private void onClickEnter(View view) {
        view.findViewById(R.id.btnEnter_userFragment).setOnClickListener(view12 -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            if (!login.isEmpty() && !password.isEmpty()) {
                view12.findViewById(R.id.btnEnter_userFragment).setOnClickListener(view1 -> {
                    PreferenceUtils.init(requireContext());
                    PreferenceUtils.saveLoginLine(login);
                    PreferenceUtils.savePasswordLine(password);
                    Toast.makeText(getContext(), "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show();
                    listener.openPostFragment();
                });
            } else {
                editLogin.setError("Заполните поле!");
                editPassword.setError("Заполниет поле!");
            }
        });

    }

    private void init(View view) {
        editLogin = view.findViewById(R.id.editLogin_userFragment);
        editPassword = view.findViewById(R.id.editPassword_userFragment);
        listener = (SetCurrentItemListener) getContext();
    }

}
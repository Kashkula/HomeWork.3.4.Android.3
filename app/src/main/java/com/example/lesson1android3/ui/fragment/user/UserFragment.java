package com.example.lesson1android3.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1android3.R;
import com.example.lesson1android3.data.interfaces.OnItemClickListener;
import com.example.lesson1android3.data.interfaces.OpenActivityListener;
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.data.network.PostApiService;
import com.example.lesson1android3.ui.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class UserFragment extends Fragment implements OnItemClickListener {

    protected Unbinder unbinder;
    @BindView(R.id.recyclerView_userFragment)
    RecyclerView recyclerView;
    protected OpenActivityListener listener;
    protected UserAdapter userAdapter;
    protected ArrayList<PostsModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUsers();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getUsers();
    }

    private void getUsers() {
        PostApiService.getInstance().getPostApi().getUsers().enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                list.clear();
                if (response.isSuccessful() && response.body() != null) {
                    list.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        list = new ArrayList<>();
        userAdapter = new UserAdapter(list,this);
        listener = (OpenActivityListener) requireActivity();
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onLongItemView(int position) {

    }

    @Override
    public void onItemViewPostFragment(int position) {
        String id = list.get(position).getUser();
        listener.openListUserActivity(id);
    }

    @Override
    public void onItemViewUserFragment(int position) {
    }
}
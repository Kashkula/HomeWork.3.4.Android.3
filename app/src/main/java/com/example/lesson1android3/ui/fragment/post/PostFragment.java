package com.example.lesson1android3.ui.fragment.post;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.lesson1android3.data.model.PostsModel;
import com.example.lesson1android3.data.network.PostApiService;
import com.example.lesson1android3.data.interfaces.OnItemClickListener;
import com.example.lesson1android3.ui.adapter.PostAdapter;
import com.example.lesson1android3.data.interfaces.OpenActivityListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class PostFragment extends Fragment implements OnItemClickListener {
    protected ArrayList<PostsModel> listModel;
    protected PostsModel model;
    protected RecyclerView recyclerView;
    protected PostAdapter adapter;
    protected OpenActivityListener listener;
    protected OnItemClickListener onItemClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setAdapter();
        getPost();
        sendPost(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPost();
    }

    private void sendPost(View view) {
        view.findViewById(R.id.btnAdd_postFragment).setOnClickListener(view1 -> listener.openSendActivity());
    }

    public void getPost() {
        PostApiService.getInstance()
                .getPostApi()
                .getPosts().enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                listModel.clear();
                if (response.isSuccessful() && response.body() != null)
                    listModel.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Миссия провалена!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        recyclerView.setAdapter(adapter);
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        listModel = new ArrayList<>();
        adapter = new PostAdapter(listModel, this);
        model = new PostsModel();
        listener = (OpenActivityListener) requireActivity();
        onItemClickListener = this;
    }

    @Override
    public void onLongItemView(int position) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deletePost(position);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you want to delete?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    public void onItemView(int position) {
        Integer id = Integer.valueOf(listModel.get(position).getId());
        listener.openChangeActivity(id);
        Toast.makeText(getContext(), "onItemView включён!", Toast.LENGTH_SHORT).show();
    }

    private void deletePost(int position) {
        Integer id = Integer.valueOf(listModel.get(position).getId());
        PostApiService.getInstance().getPostApi().deletePost(id).enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                getPost();
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Toast.makeText(getContext(), "Not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
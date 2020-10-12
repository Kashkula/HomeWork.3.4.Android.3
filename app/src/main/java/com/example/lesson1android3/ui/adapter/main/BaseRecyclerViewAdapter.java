package com.example.lesson1android3.ui.adapter.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    private ArrayList<T> list;


    public BaseRecyclerViewAdapter(ArrayList<T> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(viewType, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bind(list.get(position), holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract void bind(T t, BaseViewHolder holder);

    protected static class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

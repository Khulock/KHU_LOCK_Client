package com.example.knock_knock.Component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knock_knock.DTO.LogInfo;
import com.example.knock_knock.databinding.ItemLogBinding;
import com.example.knock_knock.databinding.ItemLogBindingImpl;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogHolder> {

    List<LogInfo> mLogs = new ArrayList<>();

    public LogAdapter(List<LogInfo> mLogs) {
        this.mLogs = mLogs;
        if (mLogs == null) {
            this.mLogs = new ArrayList<>();
        }
    }

    public void setLogs(List<LogInfo> logs) {
        mLogs = logs;
    }


    @NonNull
    @Override
    public LogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LogHolder(ItemLogBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LogHolder holder, int position) {
        holder.bind(mLogs.get(mLogs.size()-position-1));
    }

    @Override
    public int getItemCount() {
        return mLogs.size();
    }

    public class LogHolder extends RecyclerView.ViewHolder{
        private final ItemLogBinding mBinding;

        public LogHolder(@NonNull ItemLogBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(LogInfo loginfo) {
            mBinding.setInout(loginfo.getInout());
            mBinding.setTime(loginfo.getTime());
        }
    }
}

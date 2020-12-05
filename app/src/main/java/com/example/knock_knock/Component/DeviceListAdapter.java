package com.example.knock_knock.Component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.databinding.DeviceItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>{

    private final Context mContext;
    private List<DeviceInfo> mList;
    private ControlDialogInterface dialogInterface;

    public DeviceListAdapter(List<DeviceInfo> mList, Context context, ControlDialogInterface dialogInterface) {
        this.mList = mList;
        this.mContext = context;
        this.dialogInterface = dialogInterface;

        if (mList == null) {
            this.mList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceViewHolder(DeviceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        private final DeviceItemBinding mBinding;

        public DeviceViewHolder(DeviceItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.btnSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    new ControlDialog(mContext, dialogInterface).show(Const.CONTROL_LIGHT);
                }
            });
        }

        public void bind(DeviceInfo info) {
            mBinding.setName(info.getName());
        }
    }
}

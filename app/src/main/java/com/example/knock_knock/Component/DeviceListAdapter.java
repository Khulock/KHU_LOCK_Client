package com.example.knock_knock.Component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.R;
import com.example.knock_knock.databinding.ItemDeviceBinding;

import java.util.ArrayList;
import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>{

    private final Context mContext;
    private final AppViewModel mViewModel;
    private List<DeviceInfo> mList;
    private ControlDialogInterface dialogInterface;

    public DeviceListAdapter(List<DeviceInfo> mList, Context context, ControlDialogInterface dialogInterface, AppViewModel viewModel) {
        this.mList = mList;
        this.mContext = context;
        this.dialogInterface = dialogInterface;
        this.mViewModel = viewModel;


        if (mList == null) {
            this.mList = new ArrayList<>();
        }
    }

    public void setDeviceList(List<DeviceInfo> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeviceViewHolder(ItemDeviceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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

        private final ItemDeviceBinding mBinding;

        public DeviceViewHolder(@NonNull ItemDeviceBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        public void bind(DeviceInfo info) {
            mBinding.btnSwitch.setOnClickListener(view -> {
                mViewModel.setCurDevice(info);
                new ControlDialog(mContext, dialogInterface).show("CONTROL",Const.CONTROL_LIGHT, mBinding.getRoot());
            });
            mBinding.setName(info.getName());
            mBinding.setState(info.getLevel() == 0 ? "OFF" : "state : " + String.valueOf(info.getLevel()));
            switch (info.getType()) {
                case "light": {
                    mBinding.icon.setImageResource(R.drawable.ic_baseline_wb_incandescent_24);
                    break;
                }

                case "motor": {
                    mBinding.icon.setImageResource(R.drawable.ic_outline_motor_24);
                    break;
                }

                default: {
                    break;
                }
            }
        }
    }
}

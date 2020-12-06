package com.example.knock_knock.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knock_knock.Component.AppViewModel;
import com.example.knock_knock.Component.Const;
import com.example.knock_knock.Component.ControlDialog;
import com.example.knock_knock.Component.ControlDialogInterface;
import com.example.knock_knock.Component.DeviceListAdapter;
import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.Internet.CallApiServer;
import com.example.knock_knock.databinding.FragmentExitBinding;

import java.util.List;
import java.util.stream.Collectors;

public class ExitFragment extends Fragment implements ControlDialogInterface {

    private FragmentExitBinding mBinding;
    private AppViewModel mViewModel;
    private Context mContext;
    private List<DeviceInfo> mCandidateOutDeviceList;
    private CallApiServer mApiServer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiServer = new CallApiServer(requireActivity());
        mViewModel = new ViewModelProvider(requireActivity(),
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                .get(AppViewModel.class);
        mContext = requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentExitBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        configOutDeviceRecycler();
        configAddOutDeviceBtn();
        configOutBtn();
    }

    private void configOutBtn() {
        mBinding.btnExit.setOnClickListener(view -> {

            mViewModel.getOutDeviceList()
                    .forEach(outDevice -> { mApiServer.callToggleDevice(outDevice); });

            mApiServer.callOutDoor(mViewModel.getUserInfo());
        });
    }

    private void configOutDeviceRecycler() {
        // out device recycler view bind
        mBinding.recyclerDeviceItem.setLayoutManager(new LinearLayoutManager(requireContext()));
        DeviceListAdapter listAdapter = new DeviceListAdapter(mViewModel.getOutDeviceList(), requireContext(), this);
        mBinding.recyclerDeviceItem.setAdapter(listAdapter);
        mViewModel.observeOutDeviceList().observe(this, deviceInfos -> {
            listAdapter.setDeviceList(deviceInfos);
            listAdapter.notifyDataSetChanged();
        });
    }

    private void configAddOutDeviceBtn() {
        mBinding.btnAdd.setOnClickListener(v -> {

            ControlDialog controlDialog = new ControlDialog(mContext, this);

            mCandidateOutDeviceList = mViewModel.getDeviceInfoList()
                    .stream()
                    .filter(deviceInfo -> !mViewModel.getOutDeviceList().contains(deviceInfo))
                    .collect(Collectors.toList());

            List<String> collect = mCandidateOutDeviceList.stream()
                    .map(DeviceInfo::getName)
                    .collect(Collectors.toList());

            controlDialog.show("DEVICE",collect, mBinding.getRoot());
        });
    }

    @Override
    public boolean callbackControlDialog(String tag, int index) {

        if (index < 0) { return false; }

        switch (tag) {
            case "DEVICE": {

                DeviceInfo selected = mCandidateOutDeviceList.get(index);
                List<DeviceInfo> outDeviceList = mViewModel.getOutDeviceList();
                outDeviceList.add(selected);
                mViewModel.setOutDeviceList(outDeviceList);
                mCandidateOutDeviceList.remove(selected);

                break;
            }

            case "CONTROL": {
                mViewModel.getCurDevice().setLevel(Integer.parseInt((String) Const.CONTROL_LIGHT[index]));
                break;
            }
        }


        return true;
    }
}
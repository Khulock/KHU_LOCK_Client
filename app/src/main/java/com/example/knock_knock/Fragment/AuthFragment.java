package com.example.knock_knock.Fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.knock_knock.Component.AuthManager;
import com.example.knock_knock.Component.BluetoothService;
import com.example.knock_knock.Component.AppViewModel;
import com.example.knock_knock.IndexActivity;
import com.example.knock_knock.Internet.CallApiServer;
import com.example.knock_knock.databinding.FragmentAuthBinding;
import com.google.android.material.snackbar.Snackbar;

public class AuthFragment extends Fragment {

    private CallApiServer callApiServer;
    private FragmentAuthBinding mBinding;
    private AppViewModel mViewModel;
    private MutableLiveData<Boolean> isDoorFound;
    private BluetoothService mBluetoothService;

    public AuthFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity(),
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
        ).get(AppViewModel.class);

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        requireActivity().registerReceiver(receiver, intentFilter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentAuthBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        callApiServer = new CallApiServer(requireActivity());

        isDoorFound = new MutableLiveData<>();

        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        defaultAdapter.startDiscovery();

//        mBluetoothService = new BluetoothService(isDoorFound);
//        mBluetoothService.startScan();
        Snackbar.make(mBinding.getRoot(), "도어락을 찾습니다.", Snackbar.LENGTH_LONG)
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                .show();


        isDoorFound.observe(this, isFound -> {
            if(isFound) {
                mBinding.btnEnter.setVisibility(View.VISIBLE);
                requireActivity().unregisterReceiver(receiver);
                Snackbar.make(mBinding.getRoot(), "도어락을 찾았습니다. 생체인증을 진행해주세요.", Snackbar.LENGTH_LONG)
                        .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                        .show();
            }
        });

        mBinding.btnEnter.setVisibility(View.GONE);
        mBinding.btnEnter.setOnClickListener(view -> {
//            new AuthManager(requireContext(), callApiServer).runAuth();
            callApiServer.callOpenDoor();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isDoorFound.setValue(true);
    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (device.getName() == null) {
                    return;
                }

                if (device.getName().contains("Khu")) {
                    mViewModel.setMacAddress(device.getName().split("_")[1]);
                    isDoorFound.setValue(true);
                }
            }
        }
    };

}
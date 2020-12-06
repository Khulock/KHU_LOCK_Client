package com.example.knock_knock.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

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

        isDoorFound = new MutableLiveData<Boolean>();

        //TODO un anno (emul)
//        mBluetoothService = new BluetoothService(isDoorFound);
//        mBluetoothService.startScan();

        isDoorFound.observe(this, isFound -> {
            if(isFound) {
                Snackbar.make(mBinding.getRoot(), "서버 인증되었습니다. 문이 열립니다.", Snackbar.LENGTH_LONG)
                        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                        .show();
                ((IndexActivity)getActivity()).changeFragment("HOME");
            }
        });

        mBinding.btn.setOnClickListener(view -> {
            new AuthManager(requireContext(), callApiServer).runAuth();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isDoorFound.setValue(true);
    }
}
package com.misknet.tabseet.marketer.featuers.settingFragment.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.ChangePaswordActivity.view.ChangePasswordActivity;
import com.misknet.tabseet.marketer.featuers.LoginAvtivity.view.LoginActivity;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.Logout;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private static final String TAG = SettingFragment.class.getSimpleName();
    TextView tv_change_password, tv_change_language, tv_call_us, tv_log_out;
    PreferinceHelper preferinceHelper;
    GenerlNetworking generlNetworking;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferinceHelper = new PreferinceHelper();
        tv_change_password = view.findViewById(R.id.tv_en_language);
        tv_change_language = view.findViewById(R.id.tv_ar_language);
        tv_call_us = view.findViewById(R.id.tv_call_us);
        tv_log_out = view.findViewById(R.id.tv_log_out);

        tv_change_password.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            getActivity();
        });

        tv_log_out.setOnClickListener(v -> {
            GenerlNetworking.getInstance().Logout();
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            preferinceHelper.clerData();

            getActivity().finish();

        });

        tv_call_us.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), ContactUsAvtivity.class));
            getActivity();
        });
        tv_change_language.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Language.class));
            getActivity();
        });
    }
}

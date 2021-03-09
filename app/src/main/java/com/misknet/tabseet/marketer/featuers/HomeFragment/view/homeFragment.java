package com.misknet.tabseet.marketer.featuers.HomeFragment.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.ConfirmThePayment.view.ComfirmPaymentActivity;
import com.misknet.tabseet.marketer.featuers.GoogleMap.view.MapsActivity;
import com.misknet.tabseet.marketer.featuers.HomeFragment.adapter.HomeFragmentAdapter;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;
import com.misknet.tabseet.marketer.featuers.HomeFragment.presenter.HomeFragmentPrsenter;
import com.misknet.tabseet.marketer.featuers.ResidentialPaymentConfirmation.view.ResidentialPaymentConfirmationActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment implements IHomeFragment {


    RecyclerView recyclerView;
    ArrayList<DataToMvisits> items;
    LinearLayout nodata;
    ProgressBar progres;
    FrameLayout homeFragment;
    RelativeLayout no_inter_net;
    Button btn_refresh;
    public final int REQUESTPERMASSIN = 100;
    HomeFragmentAdapter homeFragmentAdapter;
    HomeFragmentPrsenter homeFragmentPrsenter;
    IHomeFragment view;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        view = this;
        homeFragmentPrsenter = new HomeFragmentPrsenter(this, view, getActivity());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        items = new ArrayList<>();
        progres = view.findViewById(R.id.avi);
        nodata = view.findViewById(R.id.no_data);
        homeFragment = view.findViewById(R.id.home_fragment);
        no_inter_net = view.findViewById(R.id.layout_no_internet);
        btn_refresh = view.findViewById(R.id.btn_refresh);
        showLoading();

        recyclerView = view.findViewById(R.id.recyclerView);
        homeFragmentAdapter = new HomeFragmentAdapter(items, new HomeFragmentAdapter.OnItemVistsListenr() {
            @Override
            public void onItemVisitsClicked(DataToMvisits data) {
                sendDataToActivity(data);
            }

            @Override
            public void onImageLocationClicked(String late, String longe, String city, String neighborhood) {
                Intent intentMap = new Intent(getActivity(), MapsActivity.class);
                intentMap.putExtra("latitude", late);
                intentMap.putExtra("longitude", longe);
                intentMap.putExtra("neighborhoodNamehi", neighborhood);
                intentMap.putExtra("cityNamehi", city);
                startActivity(intentMap);
            }

            @Override
            public void onImageCallClicked(String mobile) {

                String uri = "tel:" + mobile;

                if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse(uri));
                    startActivity(intentCall);


                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUESTPERMASSIN);
                }


            }
        }, getActivity());
       // NetworkingParent.isNetworkConnected();
        checkWithGetData();
        btn_refresh.setOnClickListener(v ->
                checkWithGetData()
        );


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUESTPERMASSIN) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    @Override
    public void getDataSuccee(ArrayList<DataToMvisits> data) {
        items.addAll(data);
        homeFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void sendDataToActivity(DataToMvisits data) {
        if (data.getSubscription().getBuildingCat().getId() == 1) {
            Intent intent = new Intent(getActivity(), ResidentialPaymentConfirmationActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("cityName", data.getSubscription().getCity().getCityName());
            intent.putExtra("date", data.getScheduledPaymentDate());
            intent.putExtra("amount", data.getAmount());
            intent.putExtra("buildingCatName", data.getSubscription().getBuildingCat().getBuildingCatName());
            intent.putExtra("addressDetails", data.getSubscription().getAddressDetails());
            intent.putExtra("neighborhoodName", data.getSubscription().getNeighborhood().getNeighborhoodName());
            intent.putExtra("Qr_code", data.getQrCode());
            intent.putExtra("longitude", data.getSubscription().getLongitude());
            intent.putExtra("latitude", data.getSubscription().getLatitude());
            intent.putExtra("mobile", data.getSubscription().getClient().getMobile());
            intent.putExtra("groupid", data.getSubscription().getGroup().getName());
            getActivity().startActivity(intent);
        } else if (data.getSubscription().getBuildingCat().getId() == 2) {
            Intent intent = new Intent(getActivity(), ComfirmPaymentActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("cityName", data.getSubscription().getCity().getCityName());
            intent.putExtra("date", data.getScheduledPaymentDate());
            intent.putExtra("amount", data.getAmount());
            intent.putExtra("buildingCatName", data.getSubscription().getBuildingCat().getBuildingCatName());
            intent.putExtra("addressDetails", data.getSubscription().getAddressDetails());
            intent.putExtra("Qr_code", data.getQrCode());
            intent.putExtra("groupid", data.getSubscription().getGroup().getName());
            getActivity().startActivity(intent);

        }
    }

    @Override
    public void onGetDataFail(String message) {
        Log.e("Sohaib", message);
    }

    @Override
    public void hideLoading() {
        progres.setVisibility(View.GONE);
        progres.setBackgroundColor(Color.RED);
    }

    @Override
    public void showLoading() {
        progres.setVisibility(View.VISIBLE);
    }

    public void checkWithGetData() {
        if (NetworkingParent.myConnection) {

            recyclerView.setVisibility(View.VISIBLE);
            no_inter_net.setVisibility(View.GONE);
            homeFragmentPrsenter.getData(nodata);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(homeFragmentAdapter);

        } else {
            homeFragment.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
            recyclerView.setVisibility(View.GONE);
            no_inter_net.setVisibility(View.VISIBLE);
            hideLoading();

        }
    }

}

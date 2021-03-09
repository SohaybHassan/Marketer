package com.misknet.tabseet.marketer.featuers.RecoedFragment.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
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
import com.misknet.tabseet.marketer.Network.utiles.PagingRequestListener;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.ConfirmThePayment.view.ComfirmPaymentActivity;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.adapter.PaginationScrollListener;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.adapter.RecordFragmentAdapter;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyOldVisits;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.presenter.RecoedFragmentPresenter;
import com.misknet.tabseet.marketer.featuers.ResidentialPaymentConfirmation.view.ResidentialPaymentConfirmationActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment implements IRecordFragment {

    private RecyclerView recyclerView;
    private ArrayList<MyoldItemData> list;
    private LinearLayout layout;
    FrameLayout recordFragment;
    RelativeLayout no_inter_net;
    Button btn_refresh;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES = 3; //your total page
    private int currentPage = PAGE_START;

    private MyOldVisits myOldVisits;

    private int page = 1;
    private int page_count;
    private ProgressBar progres;


    RecoedFragmentPresenter presenter;
    IRecordFragment view;


    LinearLayoutManager linearLayoutManager;
    RecordFragmentAdapter recordFragmentAdapter;

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record, container, false);
        view = this;
        presenter = new RecoedFragmentPresenter(this, view,getActivity());
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myOldVisits = new MyOldVisits();
        progres = view.findViewById(R.id.avi);
        layout = view.findViewById(R.id.no_data);
        recyclerView = view.findViewById(R.id.recyclerView);
        recordFragment = view.findViewById(R.id.record_fragment);
        no_inter_net = view.findViewById(R.id.layout_no_internet);
        btn_refresh = view.findViewById(R.id.btn_refresh);
        init();
        checkNetworke();


    }

    public void init() {
        list = new ArrayList<>();
        recordFragmentAdapter = new RecordFragmentAdapter(list, new RecordFragmentAdapter.OnItemRegordListenr() {
            @Override
            public void onItemRegordClicked(MyoldItemData data) {
                sendDataToActivity(data);
            }
        }, getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (!isLoading && page <= page_count) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && totalItemCount >= 0) {
                        getPage(page+1);
                        isLoading = true;
                    }
                }
            });


        }
        recyclerView.setAdapter(recordFragmentAdapter);

    }

    @Override
    public void onGetDataSuccee(ArrayList<MyoldItemData> data, int page, int pageCount) {
        isLoading = false;
        if (page == 1) {
            list.clear();
            list.addAll(data);
            recordFragmentAdapter.notifyDataSetChanged();
        } else {
            list.addAll(data);
            recordFragmentAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onGetDataFail(String message) {

    }

    @Override
    public void sendDataToActivity(MyoldItemData data) {
        if (data.getSubscription().getBuildingCat().getId() == 1) {
            Intent intent = new Intent(getActivity(), ResidentialPaymentConfirmationActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("flageView","flage");
            intent.putExtra("cityName", data.getSubscription().getCity().getCityName());
            intent.putExtra("date", data.getScheduledPaymentDate());
            intent.putExtra("amount", data.getAmount());
            intent.putExtra("buildingCatName", data.getSubscription().getBuildingCat().getBuildingCatName());
            intent.putExtra("addressDetails", data.getSubscription().getAddressDetails());
            intent.putExtra("neighborhoodName", data.getSubscription().getNeighborhood().getNeighborhoodName());
            intent.putExtra("Qr_code", data.getQrCode());
            //intent.putExtra("groupid", data.getSubscription().getGroup().getName());
            intent.putExtra("longitude", data.getSubscription().getLongitude());
            intent.putExtra("latitude", data.getSubscription().getLatitude());
            getActivity().startActivity(intent);


        } else if (data.getSubscription().getBuildingCat().getId() == 2) {
            Intent intent = new Intent(getActivity(), ComfirmPaymentActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("flageView","flage");
            intent.putExtra("cityName", data.getSubscription().getCity().getCityName());
            intent.putExtra("date", data.getScheduledPaymentDate());
            intent.putExtra("amount", data.getAmount());
            intent.putExtra("buildingCatName", data.getSubscription().getBuildingCat().getBuildingCatName());
            intent.putExtra("addressDetails", data.getSubscription().getAddressDetails());
            intent.putExtra("Qr_code", data.getQrCode());
            //intent.putExtra("groupid", data.getSubscription().getGroup().getName());
            getActivity().startActivity(intent);

        }
    }

    @Override
    public void hideLoading() {
        progres.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progres.setVisibility(View.VISIBLE);
    }

    @Override
    public void getPage(int page) {
        this.page = page;
    }

    @Override
    public void getPagecount(int pageConte) {
        this.page_count = pageConte;
    }


    public void checkNetworke(){
        if (NetworkingParent.myConnection){
            presenter.getData(page, layout);
        }else{
            recordFragment.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
            recyclerView.setVisibility(View.GONE);
            no_inter_net.setVisibility(View.VISIBLE);
        }

        btn_refresh.setOnClickListener(v -> {
            if (NetworkingParent.myConnection){
                recyclerView.setVisibility(View.VISIBLE);
                no_inter_net.setVisibility(View.GONE);
                init();
                presenter.getData(page, layout);

            }else{
                recordFragment.setBackground(getResources().getDrawable(R.drawable.splash_logo, null));
                recyclerView.setVisibility(View.GONE);
                no_inter_net.setVisibility(View.VISIBLE);
            }
        });
    }


}



package com.misknet.tabseet.marketer.featuers.HomeFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;

import java.util.ArrayList;


public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyHolder> {
    public interface OnItemVistsListenr {
        void onItemVisitsClicked(DataToMvisits data);

        void onImageLocationClicked(String late, String longe,String city,String neighborhood);

        void onImageCallClicked(String mobile);
    }

    private OnItemVistsListenr onItemVistsListenr;
    private ArrayList<DataToMvisits> items;
    Context context;

    public HomeFragmentAdapter(ArrayList<DataToMvisits> items, OnItemVistsListenr onItemVistsListenr, Context context) {
        this.items = items;
        this.onItemVistsListenr = onItemVistsListenr;
        this.context=context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visitor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.bind(items.get(position), onItemVistsListenr);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_numberOfVistor;
        TextView tv_data;
        TextView tv_entry_fee;
        ImageView btn_location;
        ImageView btn_Calls;
        View viewshape;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_numberOfVistor = itemView.findViewById(R.id.numper_of_visitor);
            tv_data = itemView.findViewById(R.id.date_old);
            tv_entry_fee = itemView.findViewById(R.id.entry_fee);
            btn_location = itemView.findViewById(R.id.my_location_item);
            btn_Calls = itemView.findViewById(R.id.my_phone_item);
            viewshape=itemView.findViewById(R.id.view);

        }

        public void bind(final DataToMvisits data, final OnItemVistsListenr listenr) {
            tv_data.setText(data.getScheduledPaymentDate());
            tv_numberOfVistor.setText(context.getString(R.string.number_of_vistor)+" "+ data.getId());
            tv_entry_fee.setText(context.getString(R.string.number_of_entry_fee)+" "+ data.getAmount() + context.getString(R.string.r_s));
            itemView.setOnClickListener(v -> listenr.onItemVisitsClicked(data));

            if (PreferinceHelper.getAppLanguge().equals("ar")){
                viewshape.setBackground(context.getDrawable(R.drawable.bacground_tint_ar));
            }else if (PreferinceHelper.getAppLanguge().equals("en")){
                viewshape.setBackground(context.getDrawable(R.drawable.bacground_tint));

            }
            btn_location.setOnClickListener(v -> listenr.onImageLocationClicked(
                    data.getSubscription().getLatitude(),
                    data.getSubscription().getLongitude(),
                    data.getSubscription().getCity().getCityName(),
                    data.getSubscription().getNeighborhood().getNeighborhoodName()));

            btn_Calls.setOnClickListener(v -> listenr.onImageCallClicked(data.getSubscription().getClient().getMobile()));
        }
    }
}


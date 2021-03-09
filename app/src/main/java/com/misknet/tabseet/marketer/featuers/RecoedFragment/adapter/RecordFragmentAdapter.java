package com.misknet.tabseet.marketer.featuers.RecoedFragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.misknet.tabseet.marketer.Network.asp.models.PreferinceHelper;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.GoogleMap.view.MapsActivity;
import com.misknet.tabseet.marketer.featuers.HomeFragment.adapter.HomeFragmentAdapter;
import com.misknet.tabseet.marketer.featuers.HomeFragment.model.DataToMvisits;
import com.misknet.tabseet.marketer.featuers.RecoedFragment.model.MyoldItemData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecordFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnItemRegordListenr {
        void onItemRegordClicked(MyoldItemData data);
    }

    OnItemRegordListenr onItemRegordListenr;
    Context context;
    private ArrayList<MyoldItemData> items;
    private String getYesterdayDateString;
    private String today;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean isLastPage = false;


    public RecordFragmentAdapter(ArrayList<MyoldItemData> items, OnItemRegordListenr onItemRegordListenr, Context context) {
        this.items = items;
        this.onItemRegordListenr = onItemRegordListenr;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, layoutInflater);
                break;
            case LOADING:
                View v2 = layoutInflater.inflate(R.layout.item_progres, parent, false);
                break;
        }

        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_record, parent, false);
        viewHolder = new MyHolder(v1);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case ITEM:
                MyHolder myHolder = (MyHolder) holder;
                myHolder.bind(onItemRegordListenr, items.get(position));
                myHolder.tv_data.setText(items.get(position).getScheduledPaymentDate());
                myHolder.tv_numberOfVistor.setText(context.getString(R.string.number_of_vistor) + " " + items.get(position).getId());
                myHolder.tv_entry_fee.setText(context.getString(R.string.number_of_entry_fee) + " " + items.get(position).getAmount() + " " + context.getString(R.string.r_s));
                getYesterdayDateString = getYesterdayDateString();
                today = getDate();
                checkdate(position, myHolder);
                //
                break;
            case LOADING:
                //Do nothing
                break;
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == items.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    // Method Helper


    public void addLoadingFooter() {
        isLoadingAdded = true;
        items.add(new MyoldItemData());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = items.size() - 1;
        MyoldItemData result = items.get(position);

        if (result != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }


    // View Holder
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_numberOfVistor;
        TextView tv_data;
        TextView tv_show_date;
        TextView tv_entry_fee;
        View viewshape;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_numberOfVistor = itemView.findViewById(R.id.number_of_visterr);
            tv_data = itemView.findViewById(R.id.dater);
            tv_entry_fee = itemView.findViewById(R.id.entry_feer);
            tv_show_date = itemView.findViewById(R.id.tv_show_date);
            viewshape = itemView.findViewById(R.id.view);
        }

        public void bind(OnItemRegordListenr lisener, MyoldItemData data) {
            if (PreferinceHelper.getAppLanguge().equals("ar")) {
                viewshape.setBackground(context.getDrawable(R.drawable.bacground_tint_ar));
            } else if (PreferinceHelper.getAppLanguge().equals("en")) {
                viewshape.setBackground(context.getDrawable(R.drawable.bacground_tint));

            }
            itemView.setOnClickListener(v -> lisener.onItemRegordClicked(data));
        }

    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String g = dateFormat.format(cal.getTime());
        return g;
    }

    public String getDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);

        return formattedDate;
    }

    public void checkdate(int position, MyHolder myHolder) {
        if (position == 0 && items.get(position).getPaymentDate().equals(today)) {
            myHolder.tv_show_date.setText("To day");
        } else if (items.get(position).getPaymentDate().equals(today)) {
            myHolder.tv_show_date.setVisibility(View.GONE);
        } else if (position == 0 &&  items.get(position).getPaymentDate().equals(getYesterdayDateString)) {
            myHolder.tv_show_date.setText("yesterday");
        } else if (items.get(position).getPaymentDate().equals(getYesterdayDateString)) {
            myHolder.tv_show_date.setVisibility(View.GONE);
         //   Log.d("this date", items.get(position).getPaymentDate());
        } else if (items.get(position).getPaymentDate().equals(items.get(position - 1).getPaymentDate())) {
            myHolder.tv_show_date.setVisibility(View.GONE);
        } else {
            myHolder.tv_show_date.setVisibility(View.VISIBLE);
            myHolder.tv_show_date.setText(items.get(position).getPaymentDate());
        }
    }
}

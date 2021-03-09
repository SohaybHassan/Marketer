package com.misknet.tabseet.marketer.featuers.settingFragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.Notifications.model.NotificationsItem;


import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<NotificationsItem> items;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    public NotificationAdapter(ArrayList<NotificationsItem> items){
        this.items=items;
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
        View v1 = inflater.inflate(R.layout.item_notifications, parent, false);
        viewHolder = new NotificationAdapter.MyHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case ITEM:
                NotificationAdapter.MyHolder myHolder = (NotificationAdapter.MyHolder) holder;
                myHolder.text_notification.setText(items.get(position).getTitle());
                break;
            case LOADING:
                //Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        TextView text_notification;
        TextView tv_time;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text_notification = itemView.findViewById(R.id.notification_text);
            tv_time = itemView.findViewById(R.id.time_of_notification);
        }


    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}

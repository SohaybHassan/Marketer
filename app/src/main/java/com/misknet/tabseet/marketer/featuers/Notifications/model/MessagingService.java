package com.misknet.tabseet.marketer.featuers.Notifications.model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.Notifications.view.NotificationsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //todo: handle notification

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ


        JSONObject jsonObject = new JSONObject(remoteMessage.getData());

        Log.e("Ayman","onMessageReceived") ;
        try {
            Log.d("Ayman", "From: " + jsonObject.getJSONObject("items").getString("object_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("Ayman", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Ayman", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Intent intent = new Intent(this, NotificationsActivity.class);
            showNotificationMessage(intent,remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

//            NetworkShared.getAsp().getGeneral().notread(new RequestListener<Integer>() {
//                @Override
//                public void onSuccess(Integer data) {
//                    AppSharedData.setNOTIFICATION_COUNTER(data);
//                }
//
//                @Override
//                public void onFail(String message, int code) {
//
//                }
//            });
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    public void showNotificationMessage (Intent intent ,String title ,String body){

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "some_channel_id1";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
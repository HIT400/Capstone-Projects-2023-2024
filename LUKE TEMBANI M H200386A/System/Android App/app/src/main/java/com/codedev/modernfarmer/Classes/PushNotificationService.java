package com.codedev.modernfarmer.Classes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.codedev.modernfarmer.API.ApiService;
import com.codedev.modernfarmer.API.RetrofitClient;
import com.codedev.modernfarmer.Activities.SecondActivity;
import com.codedev.modernfarmer.Constants;
import com.codedev.modernfarmer.Dao.PlacementListDAO;
import com.codedev.modernfarmer.Database.MFDBClient;
import com.codedev.modernfarmer.Database.ModernFarmerDB;
import com.codedev.modernfarmer.R;
import com.codedev.modernfarmer.Utils.GetTokenFCM;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PushNotificationService extends FirebaseMessagingService {
    public static final String FCM_PARAM = "picture";
    private static final String CHANNEL_NAME = "FCM";
    private static final String CHANNEL_DESC = "Firebase Cloud Messaging";
    private int numMessages = 0;

    private SharedPreferences sharedPreferences;

    private Context context;

    private Activity activity;

    public PushNotificationService(){
    }

    public PushNotificationService(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        String status = message.getData().get("status");
        String placement_id = message.getData().get("placement_id");
        RemoteMessage.Notification notification = message.getNotification();
        Map<String, String> data = message.getData();

        if(Objects.equals(status, "accepted")){
            updateOrderoNPushNotification(placement_id,"Approved");
        } else if (Objects.equals(status,"revoked")) {
            updateOrderoNPushNotification(placement_id,"Revoked");
        }
        sendNotification(notification, data);

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("CUSTOMER_NAME","Default");
        GetTokenFCM getTokenFCM = new GetTokenFCM(context);
        getTokenFCM.updateToken(token,username);
    }


    private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data) {

        Bundle bundle = new Bundle();
        bundle.putString(FCM_PARAM, data.get(FCM_PARAM));

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);



        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
                .setContentIntent(pendingIntent)
                .setContentInfo("Hello")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setColor(getColor(R.color.teal_200))
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setNumber(++numMessages)
                .setSmallIcon(R.drawable.ic_notification);

        try {
            String picture = data.get(FCM_PARAM);
            if (picture != null && !"".equals(picture)) {
                URL url = new URL(picture);
                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(
                        new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(notification.getBody())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    getString(R.string.notification_channel_id), CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESC);
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});

            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void updateOrderoNPushNotification(String placement_id, String status){
        Log.i("Update Placement","Updating Placement ID: "+placement_id);
        ModernFarmerDB database = ((MFDBClient) getApplication()).getDatabase();
        PlacementListDAO placementListDAO = database.placementListDAO();
        CompletableFuture.runAsync(() -> placementListDAO.updatePlacement(status,placement_id));
    }


}

package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private static final int NOTIFICATION_ID= 1;
    private static final String CHANNEL_ID = "my_channel_id";
    MainActivity mainActivity;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ShowToast("Servis Başlatıldı");
        showNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void ShowToast(final String message)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNotification()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"My Channel", notificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Channel Description");
            notificationManager.createNotificationChannel(channel);
        }
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Kullanıcı Güvenlik Butonu")
                .setContentText("Buton Durumu: ")
                .setSmallIcon(R.drawable.ic_circle_notifications)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(NOTIFICATION_ID,notification);
    }
}

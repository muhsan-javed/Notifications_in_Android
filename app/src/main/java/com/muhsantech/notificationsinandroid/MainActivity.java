package com.muhsantech.notificationsinandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQ_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.brave, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;

        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQ_CODE, iNotify, PendingIntent.FLAG_UPDATE_CURRENT);

        //Big Picture Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) (ResourcesCompat.getDrawable(getResources(), R.drawable.img, null))).getBitmap())
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Image Sent by Javed")
                .setSummaryText("Image Message");

        // Inbox Style
        Notification.InboxStyle inboxStyle  = new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("V")
                .addLine("C")
                .addLine("D")
                .addLine("F")
                .addLine("S")
                .addLine("o")
                .addLine("p")
                .addLine("W")
                .addLine("Q")
                .setBigContentTitle("Full MessGE ")
                .setSummaryText("Message From Ali Hyder");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon )
                    .setSmallIcon(R.drawable.brave)
                    .setContentText("New Message")
                    .setSubText("New Message from Muhsan")
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(false)
                    .setOngoing(true)
                    .setChannelId(CHANNEL_ID)
                    .build();
            manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channels ", NotificationManager.IMPORTANCE_HIGH));

        }else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon )
                    .setSmallIcon(R.drawable.brave)
                    .setContentText("New Message")
                    .setSubText("New Message from Muhsan")
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(false)
                    .build();
        }
        manager.notify(NOTIFICATION_ID, notification);
    }
}
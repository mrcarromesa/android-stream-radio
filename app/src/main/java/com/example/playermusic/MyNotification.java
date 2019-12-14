package com.example.playermusic;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


//https://stackoverflow.com/questions/12526228/how-to-put-media-controller-button-on-notification-bar
public class MyNotification extends Notification {

    private Context ctx;
    private NotificationManager mNotificationManager;

    @SuppressLint("NewApi")
    public MyNotification(Context ctx){
        super();
        this.ctx=ctx;
        String ns = Context.NOTIFICATION_SERVICE;
        mNotificationManager = (NotificationManager) ctx.getSystemService(ns);
        CharSequence tickerText = "Shortcuts";
        long when = System.currentTimeMillis();
        Notification.Builder builder = new Notification.Builder(ctx);

        Notification notification=builder.build();
        notification.when=when;
        notification.tickerText=tickerText;
        notification.icon=R.mipmap.ic_launcher;

        /*Intent notificationIntent = new Intent(ctx, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(ctx, 0,
                notificationIntent, 0);*/

        RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notification_layout);

        //set the button listeners
        setListeners(contentView);

        notification.contentView = contentView;
        //notification.contentIntent = intent;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        CharSequence contentTitle = "From Shortcuts";
        mNotificationManager.notify(548853, notification);
    }

    public void setListeners(RemoteViews view){
        //radio listener
        /*Intent radio=new Intent(ctx,HelperActivity.class);
        radio.putExtra("DO", "radio");
        PendingIntent pRadio = PendingIntent.getActivity(ctx, 0, radio, 0);
        view.setOnClickPendingIntent(R.id.radio, pRadio);

        //volume listener
        Intent volume=new Intent(ctx, HelperActivity.class);
        volume.putExtra("DO", "volume");
        PendingIntent pVolume = PendingIntent.getActivity(ctx, 1, volume, 0);
        view.setOnClickPendingIntent(R.id.volume, pVolume);

        //reboot listener
        Intent reboot=new Intent(ctx, HelperActivity.class);
        reboot.putExtra("DO", "reboot");
        PendingIntent pReboot = PendingIntent.getActivity(ctx, 5, reboot, 0);
        view.setOnClickPendingIntent(R.id.reboot, pReboot);

        //top listener
        Intent top=new Intent(ctx, HelperActivity.class);
        top.putExtra("DO", "top");
        PendingIntent pTop = PendingIntent.getActivity(ctx, 3, top, 0);
        view.setOnClickPendingIntent(R.id.top, pTop); */

        //app listener
        Intent snoozeIntent = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        //snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra("DO", "app");
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(ctx, 0, snoozeIntent, 0);
        view.setOnClickPendingIntent(R.id.btn1, snoozePendingIntent);

        /*Intent app = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        app.putExtra("DO", "app");
        PendingIntent pApp = PendingIntent.getActivity(ctx, 4, app, 0);
        view.setOnClickPendingIntent(R.id.btn1, pApp);*/


        Intent app2 = new Intent(ctx, com.example.playermusic.HelperActivity.class);
        app2.putExtra("DO", "stop");
        PendingIntent pApp2 = PendingIntent.getBroadcast(ctx, 5, app2, 0);
        view.setOnClickPendingIntent(R.id.btn2, pApp2);


    }

}

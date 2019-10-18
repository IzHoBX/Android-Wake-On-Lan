package com.teamgy.wakeonlan;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.teamgy.wakeonlan.sendWol.WOLService;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyNotificationListener extends NotificationListenerService {
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getString(R.string.notification_listener_service_name), "onBind");
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i(getString(R.string.notification_listener_service_name), "received notification");
        if(sbn.getPackageName().equals(getString(R.string.ifttt_package_name)) && sbn.getNotification().extras.get("android.text").equals("PC Turn ON")) {
            //these following lines are copied from MainActivity and getEnabledMacs() args are copied from PCListHolderFragment
            ArrayList<String> enabledMacs = com.teamgy.wakeonlan.utils.Tools.getEnabledMacs(com.teamgy.wakeonlan.utils.PCInfoDatabaseHelper.getsInstance(this).getAllPCInfos());
            Intent serviceIntent = new Intent(getApplicationContext(), WOLService.class);
            serviceIntent.putStringArrayListExtra("macAdresses", enabledMacs);
            startService(serviceIntent);
            Log.i(getString(R.string.notification_listener_service_name), "sending wol packet");
        }
        super.onNotificationPosted(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getString(R.string.notification_listener_service_name), "service started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(getString(R.string.notification_listener_service_name), "service destroyed");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_vector)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("notificationListenerService destroyed")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        super.onDestroy();
    }
}

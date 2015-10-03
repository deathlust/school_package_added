package ru.spb.deathlust.packageupdatedapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PackageUpdatedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(new Intent(context, MainActivity.class)
                .setData(intent.getData())
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}

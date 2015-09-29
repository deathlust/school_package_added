package ru.spb.deathlust.packageupdatedapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class PackageUpdatedReceiver extends BroadcastReceiver {
    public PackageUpdatedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageStr = intent.getDataString();
        Intent signal = new Intent(MainActivity.INTENT);
        signal.putExtra(MainActivity.PACKAGE, packageStr);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(signal);
    }
}

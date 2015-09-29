package ru.spb.deathlust.packageupdatedapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT = "ru.spb.deathlust.packageupdatedapp.PACKAGE_ADDED";
    public static final String PACKAGE = "package";
    private String packageStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packageStr = "com.google.android.gm";
        setContentView(R.layout.activity_main);
        updateControls();
        LocalBroadcastManager.
                getInstance(getApplicationContext()).
                registerReceiver(messageReceiver, new IntentFilter(INTENT));
    }

    private void updateControls() {
        TextView textView = (TextView)findViewById(R.id.packageTextView);
        textView.setText(packageStr);
        Button button = (Button)findViewById(R.id.googlePlayButton);
        button.setEnabled(!packageStr.equals(""));
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String prefix = "package:";
            String data = intent.getStringExtra(PACKAGE);
            if (data.startsWith(prefix)) {
                packageStr = data.substring(prefix.length());
                updateControls();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(messageReceiver);
    }

    public void googlePlayButtonClick(View view) {
        String url;

        try {
            getPackageManager().getPackageInfo("com.android.vending", 0);

            url = "market://details?id=" + packageStr;
        } catch ( final Exception e ) {
            url = "http://play.google.com/store/apps/details?id=" + packageStr;
        }

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}

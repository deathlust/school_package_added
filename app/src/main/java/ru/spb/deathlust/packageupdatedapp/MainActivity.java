package ru.spb.deathlust.packageupdatedapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String packageStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateControls();
    }

    private void updateControls() {
        TextView textView = (TextView)findViewById(R.id.packageTextView);
        textView.setText(packageStr);
        Button button = (Button)findViewById(R.id.googlePlayButton);
        button.setEnabled(!"".equals(packageStr));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ("package".equals(intent.getScheme())) {
            packageStr = intent.getData().getSchemeSpecificPart();
            setIntent(intent);
            updateControls();
        }
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

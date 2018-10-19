package com.lemon.deeplinktest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("uri", getIntentUri());
        startActivity(intent);
        finish();
    }

    private Uri getIntentUri() {
        Intent intent = getIntent();
        Uri uri = null;
        if (null != intent) {
            uri = intent.getData();
        }
        return uri;
    }
}

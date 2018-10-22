package com.lemon.deeplinktest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Uri uri = getIntent().getParcelableExtra("uri");
        Uri uri = getIntentUri();
        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
            ((TextView) findViewById(R.id.textView)).setText("AF Deeplink url = " + uri.toString());
        } else {
            ((TextView) findViewById(R.id.textView)).setText("Not AF Deeplink");
        }


        AppsFlyerConversionListener conversionDataListener =
                new AppsFlyerConversionListener() {
                    @Override
                    public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
                        Log.i(AppsFlyerLib.LOG_TAG, "onInstallConversionDataLoaded");
                        for (String attrName : conversionData.keySet()) {
                            Log.i(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                        }
                        parseAfData(conversionData);
                    }

                    @Override
                    public void onInstallConversionFailure(String errorMessage) {
                        Log.e(AppsFlyerLib.LOG_TAG, "error getting conversion data: " + errorMessage);
                    }

                    @Override
                    public void onAppOpenAttribution(Map<String, String> map) {
                        Log.i(AppsFlyerLib.LOG_TAG, "onAppOpenAttribution");
                        for (String attrName : map.keySet()) {
                            Log.i(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + map.get(attrName));
                        }
                    }

                    @Override
                    public void onAttributionFailure(String errorMessage) {
                        Log.e(AppsFlyerLib.LOG_TAG, "error onAttributionFailure : " + errorMessage);
                    }
                };
        AppsFlyerLib.getInstance().registerConversionListener(this, conversionDataListener);
    }

    private Uri getIntentUri() {
        Intent intent = getIntent();
        Uri uri = null;
        if (null != intent) {
            uri = intent.getData();
        }
        return uri;
    }

    private void parseAfData(Map<String, String> map) {
        try {
            Log.i(AppsFlyerLib.LOG_TAG, "parseAfData");
            boolean isFirstLaunch = Boolean.parseBoolean(map.get("is_first_launch"));
            if (isFirstLaunch) {

            }
            for (String attrName : map.keySet()) {
                Log.i(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + map.get(attrName));
            }
        } catch (Exception e) {
            Log.e(AppsFlyerLib.LOG_TAG, "parseAfData error, " + e);
        }
    }
}

package com.lemon.deeplinktest;

import android.app.Application;
import android.util.Log;


import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;


public class AFApplication extends Application {

    private static final String TAG = "appsflyer";
    private static final String AF_DEV_KEY = "wiMmKJ9xudwzNqJW6HoM2g";

    @Override
    public void onCreate() {
        super.onCreate();
        AppsFlyerLib.getInstance().setLogLevel(AFLogger.LogLevel.VERBOSE);
        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null);
        AppsFlyerLib.getInstance().startTracking(this);
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
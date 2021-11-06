package com.example.recruiter.others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class CheckInternet {
    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetInfo =
                connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiNetInfo != null && wifiNetInfo.isConnected() || (mobileNetInfo != null && mobileNetInfo.isConnected()))){
            return true;
        }
        else {
            return false;
        }
    }
}

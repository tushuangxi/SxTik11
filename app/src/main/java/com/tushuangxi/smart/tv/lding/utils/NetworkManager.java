package com.tushuangxi.smart.tv.lding.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;


/**
 * @class NetworkManager
 * @describe
 */
public class NetworkManager {

    public static boolean isConnected(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo && networkInfo.isConnected()) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

}

package com.app.tosstraApp.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.app.tosstraApp.services.Interface;
import com.app.tosstraApp.services.TosstraAppInstance;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommonUtils {
    private static CommonUtils commonUtils;
    public static final String base_url="http://tosstra.tosstra.com/api/";

    public static CommonUtils getInstance() {
        if (commonUtils == null) {
            commonUtils = new CommonUtils();
        }
        return commonUtils;
    }

    public static Interface retroInit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Interface.class);
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public static void closeKeyBoard(FragmentActivity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }
    }


    public static void myLog(String tag, String message) {
        boolean isDebuggerOn = true;
        if (isDebuggerOn) {
            try {
                Log.d(tag, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void showSmallToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}

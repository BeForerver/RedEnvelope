package com.example.accessabilitylearn.utils;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.accessabilitylearn.activities.MainActivity;

public class AppUtil {


    public static String getVersion(Context context, String packageName){
        try {
           return context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("InvalidWakeLockTag")
    public static void weakUpScreen(){
        PowerManager pm = (PowerManager)MainActivity.AppContext.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "WakeLock");
        wakeLock.acquire();
        wakeLock.release();

        KeyguardManager km = (KeyguardManager) MainActivity.AppContext.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = km.newKeyguardLock("unlock");
        lock.disableKeyguard();
    }

    public static void makeToast(String text){
        Toast.makeText(MainActivity.AppContext, text, Toast.LENGTH_SHORT).show();
    }

    //获取版本号
    public static String getAppVersion(Context context){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    //创建context
    public static Context getContext(Context t, String packageName){
        Context context = null;
        try {
            context = t.createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return context;
    }

}

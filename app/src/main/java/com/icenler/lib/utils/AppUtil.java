package com.icenler.lib.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.icenler.lib.R;
import com.icenler.lib.ui.AppConfig;
import com.icenler.lib.ui.base.BaseApplication;
import com.icenler.lib.utils.helper.SharedPrefsHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by iCenler - 2015/7/14.
 * Description：Android 开发常用操作工具类
 */
public class AppUtil {

    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @return 应用版本号
     */
    public static int getAppVersionCode(Context context) {
        PackageInfo info = null;
        int versionCode = 1;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * @return 应用版本名称
     */
    public static String getAppVersionName(Context context) {
        PackageInfo info = null;
        String versionName = "1.0.0";
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * @return 应用名称
     */
    public String getAppName(Context context) {
        String applicationName;
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (Exception e) {
            applicationName = context.getString(R.string.app_name);
        }

        return applicationName;
    }

    /**
     * 获取当前SDK版本
     */
    public static int getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备认证码
     */
    public static String getIMEI(Context context) {
        requestPermission(Manifest.permission.READ_PHONE_STATE);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        return tm == null ? null : tm.getDeviceId();
    }

    /**
     * 当前线程是否为主线程
     */
    public static boolean isMainLooper() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * context 是否是 Activity 例例
     */
    public static boolean isActivityContext(Context context) {
        return context instanceof Activity;
    }

    /**
     * 检测服务是否运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : servicesList) {
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    /**
     * 查看特殊权限否否申明
     */
    public static void requestPermission(String permission) {
        Context context = BaseApplication.getInstance();
        if (PackageManager.PERMISSION_GRANTED != context.getPackageManager().checkPermission(permission, context.getPackageName())) {
            throw new UnsupportedOperationException("missing permission \"" + "android.permission.READ_PHONE_STATE " + "\" in manifest.xml!");
        }
    }

    /**
     * 获取终端唯一标识
     */
    public static String getDeviceID(Context context) {
        String deviceID = SharedPrefsHelper.get(AppConfig.PREFS_DEVICE_ID, "");
        if (!TextUtils.isEmpty(deviceID)) {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            // Use the Android ID unless it's broken, in which case fallback on deviceId,
            // unless it's not available, then fallback on a random number which we store
            // to a prefs file
            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    deviceID = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
                } else {
                    deviceID = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    deviceID = deviceID != null ? UUID.nameUUIDFromBytes(deviceID.getBytes("utf8")).toString() : UUID.randomUUID().toString();
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            SharedPrefsHelper.put(AppConfig.PREFS_DEVICE_ID, deviceID);
        }

        return deviceID;
    }


    /**
     * 获取应用唯一标识
     *
     * @param context
     * @param appName
     * @return
     */
    public static String getDeviceUUID(Context context, String appName) {
        String pullToken = SharedPrefsHelper.get(AppConfig.PREFS_DEVICE_UUID, "");
        if (pullToken == null) {
            UUID uuid;
            final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            // Use the Android ID unless it's broken, in which case fallback on deviceId,
            // unless it's not available, then fallback on a random number which we store
            // to a prefs file
            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes((androidId + appName).getBytes("utf8"));
                } else {
                    final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    uuid = deviceId != null ? UUID.nameUUIDFromBytes((deviceId + appName).getBytes("utf8")) : UUID.randomUUID();
                }
            } catch (Exception e) {
                uuid = UUID.randomUUID();
            }
            pullToken = uuid.toString();
            SharedPrefsHelper.put(AppConfig.PREFS_DEVICE_UUID, pullToken);
        }

        return pullToken;
    }
}

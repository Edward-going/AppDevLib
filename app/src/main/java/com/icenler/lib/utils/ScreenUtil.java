package com.icenler.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;

import com.icenler.lib.base.BaseApplication;

import java.lang.reflect.Field;

/**
 * Created by iCenler - 2015/7/13.
 * Description： Android 屏幕相关工具类：
 * 1、 常用单位转换
 * 2、 屏幕密度、尺寸、分辨率
 * 3、 状态栏高度
 * 4、 当前屏幕截图
 */
public class ScreenUtil {

    private ScreenUtil() { throw new UnsupportedOperationException("cannot be instantiated"); }

    private static int mWidthPixels = 0;            // 屏幕宽度
    private static int mHeightPixels = 0;           // 屏幕高度
    private static final DisplayMetrics metrics;    // 屏幕密度

    static {
        metrics = BaseApplication.getInstance().getResources().getDisplayMetrics();
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }

    /**
     * @param dpVal
     * @return 根据设备的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(int dpVal) {
        return (int) (dpVal * metrics.density + 0.5f);
    }

    /**
     * @param pxVal
     * @return 根据设备的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(int pxVal) {
        return (int) (pxVal * metrics.density + 0.5f);
    }

    /**
     * @param spVal
     * @return 根据设备的分辨率从 ps 的单位 转成为 px(像素)
     */
    public static int sp2px(int spVal) {
        return (int) (spVal * metrics.density + 0.5f);
    }

    /**
     * @param pxVal
     * @return 根据设备的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(int pxVal) {
        return (int) (pxVal * metrics.scaledDensity + 0.5f);
    }

    /**
     * @return 当前设备屏幕密度
     */
    public static DisplayMetrics getDisPlayMetrics() {
        return metrics;
    }

    /**
     * @return 当前设备屏幕宽度 （单位：像素）
     */
    public static int getDisplayWidth() {
        return mWidthPixels;
    }

    /**
     * @return 当前设备屏幕高度 （单位：像素）
     */
    public static int getDisplayHeight() {
        return mHeightPixels;
    }

    /**
     * @return 当前设备分辨率 （Example:1080x1920）
     */
    public static String getDeviceResolution() {
        return getDeviceResolution("x");
    }

    /**
     * @param linkMark 连接符号
     * @return 当前设备分辨率
     */
    public static String getDeviceResolution(String linkMark) {
        return String.format("%d%s%d", mWidthPixels, linkMark, mHeightPixels);
    }

    /**
     * @param context
     * @return 手机状态栏高度
     */
    public static int getStatusBarHeigth(Context context) {
        Class clazz = null;
        Object obj = null;
        Field field = null;

        int resId = 0, sbarH = 0;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            obj = clazz.newInstance();
            field = clazz.getField("status_bar_height");
            resId = Integer.valueOf(field.get(obj).toString());
            sbarH = context.getResources().getDimensionPixelSize(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sbarH;
    }

    /**
     * @param activity
     * @return 当前 Activity 界面截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();

        int sbarH = getStatusBarHeigth(activity);
        Bitmap srcBmp = decorView.getDrawingCache();
        Bitmap dstBmp = Bitmap.createBitmap(srcBmp, 0, sbarH, getDisplayWidth(), getDisplayHeight() - sbarH);
        decorView.destroyDrawingCache();

        return dstBmp;
    }

    /**
     * @param activity
     * @return 当前 Activity 界面完整截图
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();

        Bitmap srcBmp = decorView.getDrawingCache();
        Bitmap dstBmp = Bitmap.createBitmap(srcBmp, 0, 0, getDisplayWidth(), getDisplayHeight());
        decorView.destroyDrawingCache();

        return dstBmp;
    }

}

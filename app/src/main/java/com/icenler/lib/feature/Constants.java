package com.icenler.lib.feature;

import android.graphics.Typeface;

import com.icenler.lib.BuildConfig;
import com.icenler.lib.utils.AppUtil;

/**
 * Created by iCenler - 2015/7/17.
 * Description：App 全局配置
 */
public class Constants {

    public static boolean DEBUG = BuildConfig.DEBUG;

    /* SharedPrefrens */
    public static String PREFS_DEVICE_ID = "device_id";
    public static String PREFS_UUID = "device_uuid";
    public static String PREFS_MAC_ADDRESS = "mac_address";

    /* Config Params */
    public static String APP_NAME = AppUtil.getAppVersionName(App.getInstance());

    /* Font Typeface */
    public static Typeface FZHHJT;// 方正行黑简体
    public static Typeface MIUI_EN;// MIUI 英文
    public static Typeface MIUI_CN;// MIUI 中文
    public static Typeface FA_ICON;// Font-Awesome 图形字体
    public static Typeface AGENCY;// 小清新

    static {
        // 字体库初始化加载
        // AssetManager assets = BaseApplication.getInstance().getAssets();
        // FZHHJT = Typeface.createFromAsset(assets, "fonts/fangzhengjianti.ttf");
        // MIUI_EN = Typeface.createFromAsset(assets, "fonts/Roboto-Regular.ttf");
        // MIUI_CN = Typeface.createFromAsset(assets, "fonts/DroidSansFallback.ttf");
        // FA_ICON = Typeface.createFromAsset(assets, "fonts/fontawesome-webfont.ttf");
    }

}

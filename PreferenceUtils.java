package com.shapoorjipallonji.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vijay on 24/1/2017.
 */
public class PreferenceUtils {


    /**
     * SharedPrefrence's Name
     */
    public String APP_PREF = "spr_preference";

    /**
     * Common Tags
     */
    // Cannot clear token as it is generated only once by FCM
    public final String KEY_DEVICE_TOKEN = "device_token";

    public final String USER_ID = "UserID";
    public final String USER_NAME = "Name";
    public final String USER_EMAILID = "EmailID";
    public final String USER_MOBILE_NO = "MobileNo";
    public final String USER_DOB = "DOB";
    public final String USER_AltMobileNo = "AltMobileNo";
    public final String USER_MaritalStatus = "MaritalStatus";

    public final String IS_MIS_SENT = "MIS_SENT";
    public final String IS_OTP_VERIFIED = "IS_OTP_VERIFIED";
    public final String IS_DEVICE_TOKEN_SENT = "DEVICE_TOKEN_SENT" ;
    public final String IS_PROJECT_DIALOG_SHOWN = "PROJECT_DIALOG_SHOWN";

    protected Context mContext;
    protected SharedPreferences mSettings;
    protected SharedPreferences.Editor mEditor;

    public PreferenceUtils(Context mContext) {

        this.mContext = mContext;

        mSettings = mContext.getSharedPreferences(APP_PREF,
                Context.MODE_PRIVATE);
        mEditor = mSettings.edit();
    }

    /**
     * Set a string value for the key
     */
    public void setStringValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key - key of the sharedpreferenec
     * @return string value of the key from shared preference
     */
    public String getStringValue(String key) {
        return mSettings.getString(key, "");
    }

    /**
     * Set a integer value for the key
     */
    public void setIntegerValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key          - key of the sharedpreferenec
     * @param defaultValue - Default value for the key, if one is not found.
     * @return integer value of the key from shared preference
     */
    public int getIntegerValue(String key, int defaultValue) {
        return mSettings.getInt(key, defaultValue);
    }

    /**
     * Set a double value for the key
     */
    public void setDoubleValue(String key, double value) {
        setStringValue(key, Double.toString(value));
    }

    /**
     * Set a Long value for the key
     */
    public void setLongValue(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * Get an integer value for the key
     *
     * @param key          - key of the sharedpreferenec
     * @param defaultValue - Default value for the key, if one is not found.
     * @return Long value of the key from shared preference
     */
    public long getLongValue(String key, long defaultValue) {
        return mSettings.getLong(key, defaultValue);
    }

    /**
     * Set a Boolean value for the key
     */
    public void setBooleanValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     * Gets the value from the preferences stored natively on the device.
     *
     * @param key      - key of the sharedpreferenec
     * @param defValue - Default value for the key, if one is not found.
     */
    public boolean getBooleanValue(String key, boolean defValue) {
        return mSettings.getBoolean(key, defValue);
    }


    /****
     * Clear all the preferences
     ****/
    public void clear() {
        mEditor.clear().commit();
    }

    /**
     * Removes preference entry for the given key.
     *
     * @param key
     */
    public void removeValue(String key) {
        if (mEditor != null) {
            mEditor.remove(key).commit();
        }
    }
}

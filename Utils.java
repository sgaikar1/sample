package com.shapoorjipallonji.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shapoorjipallonji.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vijay on 18/1/2017.
 */
public class Utils {

    public static final String BASE_URL = "http://vm2.dopoints.in/SP_WS/SP_Webservice.svc/";

    public String Device = "android";
    public String Devicedownload = "androiddownload";

//    public static final String sProjectCode = "SPR";
//    public static final String Device = "android";
//    public static final String Devicedownload = "androiddownload";
//
//    public static final String Prospect = "Prospect";
//    public static final String Buyer = "Buyer";
//    public static final String Employee = "Employee";
//    public static final String Broker = "Broker";
//
//    public static final String USER_ID = "USER_ID";
//    public static final String USER_TYPE = "USER_TYPE";
//
//    public static final String FACEBOOK_SHARE = "FACEBOOK_SHARE";
//    public static final String TWITTER_SHARE = "TWITTER_SHARE";
//    public static final String LINKEDIN_SHARE = "LINKEDIN_SHARE";
//    public static final String WHATSAPP_SHARE = "WHATSAPP_SHARE";
//    public static final String NO_INTERNET = "NO_INTERNET";

    public Utils() {
    }

    /**
     * Method for logging the message
     *
     * @param tag     - tag for log
     * @param message - message for log
     **/
    public void logMe(String tag, String message) {
        Log.e(tag, message);
    }

    public void getWebserviceLog(String tag, Object obj) {
        Gson gson = new Gson();
        logMe(tag, gson.toJson(obj));
    }

    /**
     * Method for toast the message
     *
     * @param mContext - context of activity
     * @param message  - message to toast
     */
    public void toastMe(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
     public void toastAlert(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
    /**
     * Method for load the images from url using picasso or Bitmap
     *
     * @param mContext - context of Activity
     * @param filepath - image URL to load image in imageview
     * @param view     - Imageview where to load the image from url
     */
    public void loadImageInImageview(Context mContext, String filepath, ImageView view) {

        try {
            if (filepath != null) {
                Picasso.with(mContext)
                        .load(filepath)
                        .placeholder(R.drawable.no_image_available)
                        .error(R.drawable.no_image_available)
                        .into(view);
            } else {
                Picasso.with(mContext)
                        .load(R.drawable.no_image_available)
                        .placeholder(R.drawable.no_image_available)
                        .error(R.drawable.no_image_available)
                        .into(view);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Method to check the entered email address is valid or not
     *
     * @param email - email address to check
     * @return true / false accroding to email address
     */
    public boolean isEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * Mehtod to get device's unique device id
     *
     * @return unique device id
     */
    public String getUniqueIdOfDevice() {
        String deviceId = null;
        String serial = null;

        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);


        try {
            serial = Build.class.getField("SERIAL").get(null)
                    .toString();
            logMe("unique-1", serial);
            logMe("unique-2", m_szDevIDShort);
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                    .toString();
        } catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        deviceId = new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();

        return deviceId;
    }

    /**
     * Alert Dialog Layout
     */

    public AlertDialog createAlertDialog(Context mContext) {
        AlertDialog mAlertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.FullHeightDialog);
        mAlertDialog = builder.create();
        mAlertDialog.setCancelable(true);
        WindowManager.LayoutParams wlmp = mAlertDialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.BOTTOM;
        wlmp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlmp.height = WindowManager.LayoutParams.MATCH_PARENT;
        mAlertDialog.getWindow().setAttributes(wlmp);
        return mAlertDialog;
    }

    /**
     * Convert URL to Bitmap using AsyncTask
     */
    public class GetBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }

    public void openCallScreen(Context context, String phoneNo) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNo));
        context.startActivity(callIntent);
    }

    public static void openShareIntent(Activity activity, String message) {
        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("text/plain")
//                  .addEmailTo(getString(R.string.support_email_id))
//                  .setSubject("")
                    .setText(message)
//                  .setHtmlText(body) //If you are using HTML in your body text
                    .setChooserTitle("Share")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openShareMailIntent(Activity activity, String Recipient) {
        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("message/rfc822")
                    .addEmailTo(Recipient)
                    .setSubject("")
                    .setText("")
                    .setChooserTitle("Choose")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    public String getCurrentDate() {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss", Locale.US);
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public ProgressDialog showProgressDialog(Activity activity) {
        ProgressDialog mProgressDialog = ProgressDialog.show(activity, "", "Please wait...", true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        return mProgressDialog;
    }

    public void dismissProgressDialog(ProgressDialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void dismissDialog(Dialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}

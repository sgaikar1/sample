package com.ilovengr.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.ilovengr.R;
import com.ilovengr.login.LogInActivity;
import com.ilovengr.profile.ProfileRevisedActivity;
import com.ilovengr.profile_redirection.ProfileRedirectionActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Creat6ed by administrator on 5/1/16.
 */
public class Utils {

    //    public static final String BASE_URL = "http://ilovenagar.com/ngr/v2/api/";
    public static final String BASE_URL = "http://test.sundaymobility.com/ILoveNGR/v3/";
    public static final String BASE_URL_WEATHER = "https://query.yahooapis.com/v1/public/";
    public static final String BASE_URL_FILESTACK = "http://www.filepicker.io/api/store/";
    public static final String COMMON_SHARE_URL = "Click here to download I Love NGR App http://bit.ly/2dlNOJ7";
    public static final String COMMON_SHARE_ONLY_URL = "http://bit.ly/2dlNOJ7";
    public static final String Exception_text = "Sorry for inconvenience. Please try again later";
    public static final String Location_exception = "Kindly enable your location services";
    public static final int MAXLISTSIZE = 6;
    public static final int MAXIMAGECOUNT = 4;
    public static final int MAXVIDEOCOUNT = 2;
    public static final int MAXGALLARYSELECTION = 6;//Do not change if file handling for multiple items not handled inside CameraAndGalleryBottomSheetDialogFragmen.java file
    public static final boolean MULTISELECTIONINGALLARY = true;
    public static final int VIDEOSIZE = 20;//provide size limit for video
    public static final int MAXVIDEODURATION = 3;//Maximum video duration (It changes resolution of video)
    public static final String BASE_URL_RSS = "http://www.lokmat.com/";

    public static String MAIN_ACTION = "com.ilovengr.action.main";
    public static final String WEATHER_URL = "yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"ahmadnagar, in\")&format=json&env=store://datatables.org/alltableswithkeys";
    public static final String FilePickerKey = "Ao5Q6ccNdTLyaZpf33G8qz";
    public static final String RSS_URL = "feeds.php?news=20";

    public static String STARTPOSTUPLOAD = "com.ilovengr.action.startpostupload";
    public static String STARTCODEREDUPLOAD = "com.ilovengr.action.startcoderedupload";
    public static String STOPFOREGROUND_ACTION = "com.ilovengr.action.stopforeground";

    public static int FOREGROUND_SERVICE = 101;
    private final static int KEYBOARD_VISIBLE_THRESHOLD_DP = 100;

    public static String Common_Refresh_Flag = "false";

    public static final String Nagrik = "Nagrik";
    public static final String SuperAdmin = "Superadmin";
    public static final String CodeRed = "Code red";

    public static final String ACTION_APPROVE = "Approve";
    public static final String ACTION_REJECT = "Reject";
    public static final String ACTION_CLOSE = "Close";
    public static final String ACTION_DELETE = "Delete";

    public static final String TYPE_NEWS = "News";
    public static final String TYPE_CIVIC_ISSUE = "Issue";
    public static final String TYPE_CODE_RED = "Code red";
    public static final String TYPE_ONE_VOICE = "One voice";
    public static final String TYPE_EVENTS = "Events";
    public static final String TYPE_TOURISM = "Tourism spot";
    public static final String TYPE_SPORTS = "Sports";
    public static final String TYPE_FACES = "Faces";
    public static final String TYPE_POLL = "Poll";
    public static final String TYPE_POST = "Post";
    public static final String TYPE_OPEN = "Open";
    public static final String TYPE_ClOSED = "Closed";
    public static final String TYPE_FRIEND_ACCEPTED = "Friend accepted";
    public static final String TYPE_FRIEND_REQUEST = "Friend request";
    public static final String TYPE_TOURISM_WITHOUT_SPOT = "Tourism";
    public static final String TYPE_CODE = "Code red";

    public static final String FILTER_WITH_TYPE_APPROVED = "Approved";
    public static final String FILTER_WITH_TYPE_PENDING = "Pending";
    public static final String FILTER_WITH_TYPE_CLOSED_BY = "Closed by";

//    {"access_token":"Access token", "cnfu":"like_user_list", "id":"1","type":"News/Post/Issue/Code red/Poll/Events/Sports/Expert speak / Faces",
//            "detail_required":"like/dislike/namaskar/agree/disagree/support/sympathy/sad/angry","limit":"0/10"}

    // Reactions
    public static final String REACTIONS_LIKE = "like";
    public static final String REACTIONS_DISLIKE = "dislike";
    public static final String REACTIONS_NAMASKAR = "namaskar";
    public static final String REACTIONS_AGREE = "agree";
    public static final String REACTIONS_DISAGREE = "disagree";
    public static final String REACTIONS_SUPPORT = "support";
    public static final String REACTIONS_SYMPATHY = "sympathy";
    public static final String REACTIONS_SAD = "sad";
    public static final String REACTIONS_ANGRY = "angry";

    // CHAT Section
    public static final String CHAT_NAME = "CHAT_NAME";
    public static final String CHAT_IMAGE = "CHAT_IMAGE";
    public static final String CHAT_ID = "CHAT_ID";
    public static final String CHAT_FRIEND_ID = "CHAT_FRIEND_ID";
    public static final String IS_FIRST_TIME_CHAT = "IS_FIRST_TIME_CHAT";
    public static final String NOTIFY_CHATS = "Chat";
    public static final String CHATS = "chats";
    public static final String NOTIFICATIONS = "Notifications";
    public static final String UNREAD_COUNT = "UnreadCount";

    public static final String SHARE_CONTENT_TITLE = "Welcome to I Love NGR - an app for the civilians!";

    public static final String ACTION_RESP = "com.bramhacorp.intent.action.MESSAGE_PROCESSED";

    public static final String SHARE_URL = "http://test.sundaymobility.com/ILoveNGRweb/Shared/post";
//    public static final String SHARE_URL = "http://ilovenagar.com/ngr/website/Shared/post/";

    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static void showToastLowNetwork(Context context) {
        Toast t = Toast.makeText(context, "Low network connectivity",
                Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
    }

//    public static int getNotificationSmallIcon() {
//        boolean whiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//        return whiteIcon ? R.drawable.ic_launcher
//                : R.drawable.ic_launcher;
//    }

    public static void backToLogin(final Context context) {

        if (context != null) {
//        // Clear all notifications
            NotificationManager nMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nMgr.cancelAll();

//        SharedPreferences mPrefs;
//        mPrefs = context.getSharedPreferences("myData", context.MODE_PRIVATE);
            Intent in = new Intent(context, LogInActivity.class);
//        SharedPreferences.Editor editor = mPrefs.edit();
//        editor.putString("skipLoginFlag", "false");
//        editor.commit();
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            in.putExtra("clearSocialMedia", true);
            context.startActivity(in);
            SessionManager session = new SessionManager(context);
            session.clearPreferences();
        }
    }


    public static String ChangeDateFormat(String DateToConvert) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        SimpleDateFormat sdftarget = new SimpleDateFormat("dd MMM yyyy");

        Date datenew = new Date();
        String StrNewDate = "";
        try {
            datenew = sdf.parse(DateToConvert);
            StrNewDate = sdftarget.format(datenew);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StrNewDate;
    }

    public static String yyyyMMdd_format_ddMMMMyyyy(String DateToConvert) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdftarget = new SimpleDateFormat("dd MMMM yyyy");

        Date datenew = new Date();
        String StrNewDate = "";
        try {
            datenew = sdf.parse(DateToConvert);
            StrNewDate = sdftarget.format(datenew);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StrNewDate;
    }

    public static String ddMMMMyyyy_format_yyyyMMdd(String DateToConvert) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdftarget = new SimpleDateFormat("yyyy-MM-dd");

        Date datenew = new Date();
        String StrNewDate = "";
        try {
            datenew = sdf.parse(DateToConvert);
            StrNewDate = sdftarget.format(datenew);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StrNewDate;
    }

    public static void makeLog(String tag, String msg) {
        Log.e(tag, "" + msg);
    }

    public static void makeToast(final Context context, final String text) {

        if (context != null) {
            Activity activity = (Activity) context;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

//    public static String getAccountEmailID(Activity context) {
//        String sEmailID = "";
//        ArrayList<String> mEmailIDs = new ArrayList<String>();
//        try {
//            Account[] accounts = AccountManager.get(context)
//                    .getAccounts();
//            for (Account account : accounts) {
//                // Check here for the type and name to find the email
//                if (account.type.equalsIgnoreCase("com.google")) {
//                    mEmailIDs.add(account.name);
//                }
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        sEmailID = mEmailIDs.toString().replace("[", "").replace("]", "");
//
//        return sEmailID;
//    }

    // Regular expression validation for emailid
    public static boolean isEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getPsuedoUniqueID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their phone, there will be a
        // duplicate entry
        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null)
                    .toString();
            Log.e("unique-1", serial);
            Log.e("unique-2", m_szDevIDShort);
            // Go ahead and return the serial for api => 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                    .toString();
        } catch (Exception e) {
            // String needs to be initialized
            serial = "serial"; // some value
        }

        // Thanks @Joe!
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to
        // create a unique identifier
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode())
                .toString();

    }

    public static void LogMe(String key, String value) {
        Log.e(key, value);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 40;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static void CloseKeyBoard(Activity context) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(context
                            .getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {

        }
    }

    public static String getSystemDate() {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }

//    public static void show_confrimation_popup(Context context, final PopupClickInterface popup, String message) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//
//        View dialogLayout = inflater.inflate(R.layout.forgot_pwd_dialog,
//                null);
//
//        final AlertDialog dialog = builder.create();
//
//        dialog.setView(dialogLayout, 0, 0, 0, 0);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
//        WindowManager.LayoutParams wlmp = dialog.getWindow().getAttributes();
//        wlmp.gravity = Gravity.CENTER;
//
//        TextView tv_msg = (TextView) dialogLayout.findViewById(R.id.tv_msg);
//
//        tv_msg.setText(message);
//
//        Button btnok = (Button) dialogLayout.findViewById(R.id.btn_ok);
//
//        btnok.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                popup.okClick();
//                dialog.dismiss();
//
//            }
//        });
//
//        dialog.show();
//    }
//
//    public static void setImageInImageView(Context c, String filepath, ImageView i, String from, final ProgressWheel progress_wheel) {
//
//        if (from.equalsIgnoreCase("on")) {
//            Picasso.with(c)
//                    .load(filepath)
//                    .error(R.drawable.placeholder_image)
//                    //.skipMemoryCache()
//                    .into(i, new Callback() {
//
//                        @Override
//                        public void onSuccess() {
//                            // TODO Auto-generated method stub
//                            if (progress_wheel.isSpinning()) {
//                                progress_wheel.setVisibility(View.GONE);
//                            }
//                        }
//
//                        @Override
//                        public void onError() {
//                            // TODO Auto-generated method stub
//                            if (progress_wheel.isSpinning()) {
//                                progress_wheel.setVisibility(View.GONE);
//                            }
//                        }
//                    });
//        } else if (from.equalsIgnoreCase("off")) {
//            Picasso.with(c)
//                    .load(filepath)
//                    //.skipMemoryCache()
//                    .into(i, new Callback() {
//
//                        @Override
//                        public void onSuccess() {
//                            // TODO Auto-generated method stub
//                            if (progress_wheel.isSpinning()) {
//                                progress_wheel.setVisibility(View.GONE);
//                            }
//                        }
//
//                        @Override
//                        public void onError() {
//                            // TODO Auto-generated method stub
//                            if (progress_wheel.isSpinning()) {
//                                progress_wheel.setVisibility(View.GONE);
//                            }
//                        }
//                    });
//        }
//
//
//    }
//
//    public static void setImageInImageViewSmall(Context c, String filepath, ImageView i) {
//
//        Picasso.with(c)
//                .load(filepath).resize(200, 200).centerCrop()
//                //.skipMemoryCache()
//                .into(i, new Callback() {
//
//                    @Override
//                    public void onSuccess() {
//                        // TODO Auto-generated method stub
//                    }
//
//                    @Override
//                    public void onError() {
//                        // TODO Auto-generated method stub
//                    }
//                });
//
//    }
//
//    public static void setImageInImageView(Context c, String filepath, ImageView i, String from) {
//
//        if (from.equalsIgnoreCase("on")) {
//            Picasso.with(c)
//                    .load(filepath)
//                    //.skipMemoryCache()
//                    .into(i, new Callback() {
//
//                        @Override
//                        public void onSuccess() {
//                            // TODO Auto-generated method stub
//                        }
//
//                        @Override
//                        public void onError() {
//                            // TODO Auto-generated method stub
//                        }
//                    });
//        } else if (from.equalsIgnoreCase("off")) {
//            Picasso.with(c)
//                    .load(filepath)
//                    //.skipMemoryCache()
//                    .into(i, new Callback() {
//
//                        @Override
//                        public void onSuccess() {
//                            // TODO Auto-generated method stub
//                        }
//
//                        @Override
//                        public void onError() {
//                            // TODO Auto-generated method stub
//                        }
//                    });
//        }
//    }
//
//    public static void setImageInImageViewProcessed(final Context c, String filepath, final ImageView img) {
//        Picasso.with(c)
//                .load(filepath)
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                        //Set it in the ImageView
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inPurgeable = true;
//                        final int maxSize = 640;
//                        int outWidth;
//                        int outHeight;
//                        int inWidth = bitmap.getWidth();
//                        int inHeight = bitmap.getHeight();
//                        if (inWidth > inHeight) {
//                            outWidth = maxSize;
//                            outHeight = (inHeight * maxSize) / inWidth;
//                        } else {
//                            outHeight = maxSize;
//                            outWidth = (inWidth * maxSize) / inHeight;
//                        }
//                        Bitmap processImages = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight, true);
//
//                        img.setImageBitmap(processImages);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
//    }
//
//    public static void setImageInImageViewBitmap(final Context c, String filepath, final ImageView img) {
//        Picasso.with(c)
//                .load(filepath)
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                        //Set it in the ImageView
//                        if (Build.VERSION.SDK_INT >= 21) {
//                            Drawable drawableFromUrl = new BitmapDrawable(c.getResources(), bitmap);
//                            RippleDrawable rippledImage = new RippleDrawable(ColorStateList.valueOf(c.getResources().getColor(R.color.colorPrimary)), drawableFromUrl, null);
//                            img.setImageDrawable(rippledImage);
//                        } else {
//                            img.setImageBitmap(bitmap);
//                        }
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
//    }
//
//    public static void setImageInImageViewBitmap(final Context c, String filepath, final ImageView img, final ProgressWheel progress_wheel) {
//
//        Picasso.with(c)
//                .load(filepath)
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                        //Set it in the ImageView
//                        if (Build.VERSION.SDK_INT >= 21) {
//                            Drawable drawableFromUrl = new BitmapDrawable(c.getResources(), bitmap);
//                            RippleDrawable rippledImage = new RippleDrawable(ColorStateList.valueOf(c.getResources().getColor(R.color.colorPrimary)), drawableFromUrl, null);
//                            img.setImageDrawable(rippledImage);
//                        } else {
//                            img.setImageBitmap(bitmap);
//                        }
//                        if (progress_wheel.isSpinning()) {
//                            progress_wheel.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//                        if (progress_wheel.isSpinning()) {
//                            progress_wheel.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
//    }

    public static String printDifference(Date startDate, Date endDate) {
        String time_string = "";
        ArrayList<Long> time_array;
        ArrayList<String> time_value;
        time_array = new ArrayList<Long>();
        time_value = new ArrayList<String>();

        // milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        if (elapsedDays == 1) {
            time_array.add(elapsedDays);
            time_value.add("day ago");
        } else if (!(elapsedDays == 0)) {
            time_array.add(elapsedDays);
            time_value.add("days ago");
        }

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        if (elapsedHours == 1) {
            time_array.add(elapsedHours);
            time_value.add("hr ago");
        } else if (!(elapsedHours == 0)) {
            time_array.add(elapsedHours);
            time_value.add("hrs ago");
        }

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        if (elapsedMinutes == 1) {
            time_array.add(elapsedMinutes);
            time_value.add("min ago");
        } else if (!(elapsedMinutes == 0)) {
            time_array.add(elapsedMinutes);
            time_value.add("mins ago");

        }

        long elapsedSeconds = different / secondsInMilli;
        different = different % secondsInMilli;

        if (!(elapsedSeconds == 0)) {
            time_array.add(elapsedSeconds);
            time_value.add("seconds ago");

        }

        if (!(time_array.isEmpty())) {
            if (time_value.get(0).contains("seconds ago")) {
                time_string = "Just Now";
            } else {
                time_string = String.valueOf(time_array.get(0)) + " "
                        + time_value.get(0);
            }
            // Log.e("difference", "" + elapsedDays + " " + elapsedHours + " "
            // + elapsedMinutes + " " + elapsedSeconds);
        }

        return time_string;
    }

    public static InputFilter filter() {
        EditText editText;
        final String blockCharacterSet = "1234567890~#^|$%&*!()-_+=`'.,<>?/[]{}\\;:÷€£¥¤¡¿×\"₩☆♡《》" +
                " ";

        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                if (source != null && blockCharacterSet.contains(("" + source))) {
                    return "";
                }
                return null;
            }
        };
        return filter;
    }

    private static Snackbar colorSnackBar(Activity activity, int colorId,
                                          View snackBarView, String message, boolean isActionRequired) {

        final Snackbar mSnackbar = Snackbar.make(snackBarView, message, Snackbar.LENGTH_LONG);
        View view = mSnackbar.getView();
        view.setBackgroundColor(colorId);
        if (isActionRequired) {
            mSnackbar.setActionTextColor(activity.getResources().getColor(R.color.white));
            mSnackbar.setAction("DONE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSnackbar.dismiss();
                }
            });
        }
        mSnackbar.show();
        return mSnackbar;
    }

    private static Snackbar colorSnackBarInfo(Activity activity, int colorId,
                                              View snackBarView, String message, boolean isActionRequired) {

        final Snackbar mSnackbar = Snackbar.make(snackBarView, message, Snackbar.LENGTH_LONG);
        View view = mSnackbar.getView();
        view.setBackgroundColor(colorId);
        if (isActionRequired) {
            mSnackbar.setActionTextColor(activity.getResources().getColor(R.color.white));
            mSnackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSnackbar.dismiss();
                }
            });
        }
        mSnackbar.show();
        return mSnackbar;
    }

    public static void getWebserviceLog(String tag, Object obj) {
        Gson gson = new Gson();
        Utils.makeLog(tag, gson.toJson(obj));
    }

    public static Snackbar noInternetSnack(Activity activity, View view) {
        try {
            return colorSnackBar(activity, activity.getResources().getColor(R.color.statusbarcolor), view, activity.getString(R.string.no_internet_connection), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Snackbar info(Activity activity, View view, String message, boolean isActionRequired) {
        try {
            return colorSnackBarInfo(activity, activity.getResources().getColor(R.color.statusbarcolor), view, message, isActionRequired);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    public static String getFilePickerURI(Context context, String strFileName, String strFileExtention) {

        String FILEPICKER_API_KEY = Utils.FilePickerKey;

//        https://www.filepicker.io/api/store/S3?key=AW2OVH36nQnW2aSKPaCbtz&&access=
//        // public&base64decode=true&mimetype=image/jpeg&filename=%@&store=S3&path=%@

        String str = "http://www.filepicker.io/api/store/S3?key="
                + FILEPICKER_API_KEY
                + "&base64decode=true&access=public&mimetype=image/"
                + strFileExtention + "&filename=" + strFileName + "."
                + strFileExtention + "&store=S3&path=" + strFileName + "."
                + strFileExtention;


        return str;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static void setImageInImageView(Context c, String filepath, final ImageView i) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {
            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.placeholder_ngr)
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                            i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                            try {
                                i.setImageResource(R.drawable.placeholder_ngr);
                            } catch (Exception | OutOfMemoryError e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            i.setImageResource(R.drawable.placeholder_ngr);
        }

    }

    public static void setImageInEmergencyImageView(Context c, String filepath, final ImageView i) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {
            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.ic_code_red_normal)
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
//                            i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                            i.setImageResource(R.drawable.placeholder_ngr);
                        }
                    });
        } else {
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            i.setImageResource(R.drawable.ic_code_red_normal);
        }

    }

    public static void setImageInSlideImageView(Context c, String filepath, final ImageView i) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {
            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.placeholder_ngr)
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                            i.setImageResource(R.drawable.placeholder_ngr);
                        }
                    });
        } else {
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            i.setImageResource(R.drawable.placeholder_ngr);
        }

    }

    public static void setImageInImageViewNotification(Context c, String filepath, final ImageView i) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {

            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.ic_empty_notifications)
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                            i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                        }
                    });
        } else {
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            i.setImageResource(R.drawable.ic_empty_notifications);
        }

    }

    public static void setImageInImageViewFromPath(Context c, Uri filepath, final ImageView i) {

        if (filepath != null) {
            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.ic_empty_notifications).fit()
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
//                            i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                        }
                    });
        } else {
            i.setScaleType(ImageView.ScaleType.FIT_CENTER);
            i.setImageResource(R.drawable.ic_empty_notifications);
        }

    }

    public static void setImageInProfileImageView(Context c, String filepath, ImageView i) {

        if (filepath != null && URLUtil.isValidUrl(filepath)) {
            Picasso.with(c)
                    .load(filepath).placeholder(R.drawable.default_avatar)
                    //.skipMemoryCache()
                    .into(i, new Callback() {

                        @Override
                        public void onSuccess() {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                        }
                    });
        } else {
            i.setImageResource(R.drawable.default_avatar);
        }

    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isKeyboardVisible(Activity activity) {
        Rect r = new Rect();

        View activityRoot = getActivityRoot(activity);
        int visibleThreshold = Math
                .round(convertDpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP));

        activityRoot.getWindowVisibleDisplayFrame(r);

        int heightDiff = activityRoot.getRootView().getHeight() - r.height();

        return heightDiff > visibleThreshold;
    }

    private static View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static float convertDpToPx(Context context, float dp) {
        Resources res = context.getResources();

        return dp * (res.getDisplayMetrics().densityDpi / 160f);
    }

    public static LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    public static String getPincode(Context context, LatLng latlng) {
        String pincode = "";
        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
            if (!addresses.isEmpty())
                pincode = addresses.get(0).getPostalCode();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return pincode;
    }

    public static String getCurrentAddress(Context context, LatLng latlng) {
        String addressLoc = "";
        Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                ArrayList<String> addressFragments = new ArrayList<String>();

                // Fetch the address lines using getAddressLine,
                // join them, and send them to the thread.
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressLoc = addressLoc + address.getAddressLine(i);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return addressLoc;
    }

    public static void openSlidingPagerActivity(Activity context, ArrayList<String> mUrl, int position) {

        Intent in = new Intent(context, ImageSlidingActivity.class);
        in.putExtra("mUrl", mUrl);
        in.putExtra("position", position);
        context.startActivity(in);

    }

    public static void shareAppLink(final Activity context, RelativeLayout rlShare) {

        rlShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                // Link to open in the browser
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                context.startActivity(browserIntent);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, COMMON_SHARE_URL);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

    }

    public static String makeTitleCamelCase(String mString) {
        if (mString != null) {
            String output = "";
            if (mString.length() > 0) {
                output = mString.substring(0, 1).toUpperCase() + mString.substring(1);
            }
            return output;
        } else {
            return "";
        }
    }

    public static void openUserProFile(Context context, String friendID) {

        SessionManager session = new SessionManager(context);
        String user_id_static = session.getUserIdForChat();

        if (!user_id_static.equalsIgnoreCase("") && !user_id_static.equalsIgnoreCase(friendID)) {
            Intent in = new Intent(context, ProfileRedirectionActivity.class);
            in.putExtra("friendID", friendID);
            context.startActivity(in);
        } else {

            if (!user_id_static.equalsIgnoreCase("")) {
                // Redirect to own's profile
                context.startActivity(new Intent(context, ProfileRevisedActivity.class));
            }
        }
    }

    public static void shareSectionURL(Activity activity, String title, String description, String postID, String postType) {

        if (title == null) {
            title = "";
        }
        if (description == null) {
            description = "";
        }


        String mPostType = postType.replace(" ", "%20");
//        String mShareURL = title + "\n\n" + description + "\n\n" + SHARE_URL + "/" + postID + "/" + mPostType;
        String mShareURL = title + "\n\n" + SHARE_URL + "/" + postID + "/" + mPostType;
//        String query="";
//        try {
//            query = URLEncoder.encode(mShareURL, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        Uri.Builder builder = new Uri.Builder();
//        builder.scheme("http")
//                .authority("test.sundaymobility.com")
//                .appendPath("ILoveNGRweb")
//                .appendPath("Shared")
//                .appendPath("post")
//                .appendPath(postID)
//                .appendPath(postType);
//
//        String myUrl = builder.build().toString();
//        String mShareURL = title + "\n\n" + myUrl;

        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("text/plain")
//                  .addEmailTo(getString(R.string.support_email_id))
                    .setSubject(SHARE_CONTENT_TITLE)
                    .setText(mShareURL)
//                  .setHtmlText(body) //If you are using HTML in your body text
                    .setChooserTitle("Choose")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, mShareURL);
//        sendIntent.setType("text/plain");
//        activity.startActivity(sendIntent);
    }

    public static void shareSectionURL(Activity activity, String description, String postID, String postType) {

        if (description == null) {
            description = "";
        }

        String mPostType = postType.replace(" ", "%20");
        String mShareURL = description + "\n\n" + SHARE_URL + "/" + postID + "/" + mPostType;

//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, mShareURL);
//        sendIntent.setType("text/plain");
//        activity.startActivity(sendIntent);

        try {
            ShareCompat.IntentBuilder.from(activity)
                    .setType("text/plain")
//                  .addEmailTo(getString(R.string.support_email_id))
                    .setSubject(SHARE_CONTENT_TITLE)
                    .setText(mShareURL)
//                  .setHtmlText(body) //If you are using HTML in your body text
                    .setChooserTitle("Choose")
                    .startChooser();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentDate() {
        String formattedDate = "";
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.US);
            formattedDate = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            formattedDate = "";
        }
        return formattedDate;
    }

    public static void setAppLanguage(Activity context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    /**
     * Converts fahrenheit temperature to celsius * * @param fahrenheit * @return
     */
    public static String toCelsius(String fahrenheitString) {
        String celsiusString = "NA";

        try {
            int celsius = (Integer.parseInt(fahrenheitString) - 32) * 5 / 9;


            celsiusString = String.valueOf(celsius) + Html.fromHtml("<sup>°</sup>");
        } catch (Exception e) {

        }

        return celsiusString;
    }

    public static String getDayFromDate(String input_date) {
        String finalDay = "";

        try {
            SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            Date dt1 = format1.parse(input_date);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            finalDay = format2.format(dt1);
        } catch (Exception e) {
            Utils.makeLog("weather", e.toString());
        }

        return finalDay;
    }

    public static String getDayFromDateShort(String input_date) {
        String finalDay = "";

        try {
//            input_date = "01/08/2012";
            SimpleDateFormat format1 = new SimpleDateFormat("EEE");
            Date dt1 = format1.parse(input_date);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            finalDay = format2.format(dt1);
        } catch (Exception e) {
            Utils.makeLog("weather", e.toString());
        }

        return finalDay;
    }

    public static String getGreetingFromTime() {
        String greetings = "";

        try {
            Calendar c = Calendar.getInstance();
            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

            if (timeOfDay >= 0 && timeOfDay < 12) {
                greetings = "Good Morning, ";
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                greetings = "Good Afternoon, ";
            } else if (timeOfDay >= 16 && timeOfDay < 19) {
                greetings = "Good Evening, ";
            } else if (timeOfDay >= 19 && timeOfDay < 24) {
                greetings = "Good Night, ";
            }
        } catch (Exception e) {
            Utils.makeLog("weather", e.toString());
        }

        return greetings;
    }

    public static String getMimeType(Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = getApplicationContext().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    /**
     * Retrieve video frame image from given video path
     *
     * @param p_videoPath the video file path
     * @return Bitmap - the bitmap is video frame image
     * @throws Throwable
     */
    @SuppressLint("NewApi")
    public static Bitmap retriveVideoFrameFromVideo(String p_videoPath)
            throws Throwable {
        Bitmap m_bitmap = null;
        MediaMetadataRetriever m_mediaMetadataRetriever = null;
        try {
            m_mediaMetadataRetriever = new MediaMetadataRetriever();
            m_mediaMetadataRetriever.setDataSource(p_videoPath);
            m_bitmap = m_mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception m_e) {
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String p_videoPath)"
                            + m_e.getMessage());
        } finally {
            if (m_mediaMetadataRetriever != null) {
                m_mediaMetadataRetriever.release();
            }
        }
        return m_bitmap;
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        String path = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        } catch (Exception e) {
            Utils.makeLog("IMAGE URI THUMB", e.getMessage());
        }
        return Uri.parse(path);
    }

    public static long getFolderSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFolderSize(file);
            }
        } else {
            size = f.length();
        }
        return size;
    }

}

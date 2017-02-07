package com.ilovengr.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ilovengr.R;
import com.ilovengr.code_red.CodeRedListingActivity;

import org.greenrobot.eventbus.EventBus;

import io.filepicker.Filepicker;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by administrator on 29/12/15.
 */
public class BaseActivity extends AppCompatActivity {

    protected TextView tv_toolbar;
    protected ImageView img_back;
    protected ImageView img_search, img_search_close;
    protected ImageView img_alert_code_red;
    protected EditText edt_search;
    protected LinearLayout ll_toolbar_main;
    protected RelativeLayout rl_toolbar_back;
    public ConnectionDetector cd;
    public ProgressDialog pDialog;
    public SessionManager mSession;
    public String userid, userIdForChat, usertype, username, profile_image, MISFlag, deviceToken;

    /**
     * View used for showing Snackbar in the view.
     **/
    public View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.slide_in_from_right,
                R.anim.slide_out_to_left);

        cd = new ConnectionDetector(this);
        pDialog = new ProgressDialog(this);
        mSession = new SessionManager(this);

        userid = mSession.getUserId();
        userIdForChat = mSession.getUserIdForChat();
        usertype = mSession.getUsertype();
        username = mSession.getKeyUsername();
        profile_image = mSession.getKeyProfileImage();
        MISFlag = mSession.getMISFlag();
        deviceToken = mSession.getDeviceToken();
        mView = findViewById(android.R.id.content);

        // FileStack API initialization
        Filepicker.setKey(Utils.FilePickerKey);

    }

    protected void setHeading(String title) {
        if (tv_toolbar == null)
            tv_toolbar = (TextView) findViewById(R.id.tv_screen_title);
        if (tv_toolbar != null)
            tv_toolbar.setText(title);

        tv_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected void goBack() {

        img_alert_code_red = (ImageView) findViewById(R.id.img_alert_code_red);
        switch (usertype) {
            case Utils.SuperAdmin:
                img_alert_code_red.setVisibility(View.VISIBLE);
                break;
            case Utils.CodeRed:
                img_alert_code_red.setVisibility(View.VISIBLE);
                break;
            case Utils.Nagrik:
                img_alert_code_red.setVisibility(View.VISIBLE);
                break;
        }

        if (mSession.getIsCodeRedNotification()) {
            img_alert_code_red.setImageResource(R.drawable.ic_code_red_ignited);
            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.code_red_blink);
            img_alert_code_red.startAnimation(pulse);
        } else {
            img_alert_code_red.setImageResource(R.drawable.ic_code_red_normal);
        }

        img_alert_code_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(BaseActivity.this, CodeRedListingActivity.class);
                startActivity(in);
            }
        });

        // Implementing the search functionality
        rl_toolbar_back = (RelativeLayout) findViewById(R.id.rl_toolbar_back);
        edt_search = (EditText) findViewById(R.id.edt_search);
        ll_toolbar_main = (LinearLayout) findViewById(R.id.ll_toolbar_main);
        img_search = (ImageView) findViewById(R.id.img_search);
        img_search_close = (ImageView) findViewById(R.id.img_search_close);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    doEnterAnim();
                }

                overridePendingTransition(0, 0);

                ll_toolbar_main.setVisibility(View.GONE);
                edt_search.setVisibility(View.VISIBLE);
                img_search_close.setVisibility(View.VISIBLE);
                edt_search.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

            }
        });

        img_back = (ImageView) findViewById(R.id.img_back_toobar);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rl_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        img_search_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (getCurrentFocus() != null) {
                        InputMethodManager inputManager = (InputMethodManager) BaseActivity.this
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(BaseActivity.this
                                .getCurrentFocus().getWindowToken(), 0);
                    }
                } catch (Exception e) {

                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    if (ll_toolbar_main.getVisibility() == View.VISIBLE) {
                        ActivityCompat.finishAfterTransition(BaseActivity.this);
                    } else {
                        edt_search.setVisibility(View.GONE);
                        img_search_close.setVisibility(View.GONE);
                        edt_search.setText("");
                        ll_toolbar_main.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (ll_toolbar_main.getVisibility() == View.VISIBLE) {
                        ActivityCompat.finishAfterTransition(BaseActivity.this);
                    } else {
                        edt_search.setVisibility(View.GONE);
                        img_search_close.setVisibility(View.GONE);
                        edt_search.setText("");
                        ll_toolbar_main.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void shareAppLink(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, Utils.COMMON_SHARE_URL);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (img_alert_code_red != null) {
            if (mSession.getIsCodeRedNotification()) {
                img_alert_code_red.setImageResource(R.drawable.ic_code_red_ignited);
                Animation pulse = AnimationUtils.loadAnimation(this, R.anim.code_red_blink);
                img_alert_code_red.startAnimation(pulse);
            } else {
                img_alert_code_red.setImageResource(R.drawable.ic_code_red_normal);
                img_alert_code_red.clearAnimation();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (isFinishing()) {
//            overridePendingTransition(0, 0);
//        }
    }


    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }

    @Override
    public void onBackPressed() {
        dismiss(null);
        overridePendingTransition(R.anim.slide_in_from_left,
                R.anim.slide_out_to_right);
    }

    public void dismiss(View view) {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager inputManager = (InputMethodManager) BaseActivity.this
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(BaseActivity.this
                        .getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {

        }

        if (!Utils.isKeyboardVisible(this)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                doExitAnim();
                if (ll_toolbar_main.getVisibility() == View.VISIBLE) {

                    ActivityCompat.finishAfterTransition(BaseActivity.this);
                } else {
                    edt_search.setVisibility(View.GONE);
                    img_search_close.setVisibility(View.GONE);
                    edt_search.setText("");
                    ll_toolbar_main.setVisibility(View.VISIBLE);
                }
            } else {
                if (ll_toolbar_main.getVisibility() == View.VISIBLE) {
                    ActivityCompat.finishAfterTransition(BaseActivity.this);
                } else {
                    edt_search.setVisibility(View.GONE);
                    img_search_close.setVisibility(View.GONE);
                    edt_search.setText("");
                    ll_toolbar_main.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * On Lollipop+ perform a circular reveal animation (an expanding circular mask) when showing
     * the search panel.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void doEnterAnim() {
        // Next perform the circular reveal on the search panel
        final View searchPanel = findViewById(R.id.edt_search);
        if (searchPanel != null) {
            // We use a view tree observer to set this up once the view is measured & laid out
            searchPanel.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            searchPanel.getViewTreeObserver().removeOnPreDrawListener(this);
                            // As the height will change once the initial suggestions are delivered by the
                            // loader, we can't use the search panels height to calculate the final radius
                            // so we fall back to it's parent to be safe
                            int revealRadius = ((ViewGroup) searchPanel.getParent()).getHeight();
                            // Center the animation on the top right of the panel i.e. near to the
                            // search button which launched this screen.
                            Animator show = ViewAnimationUtils.createCircularReveal(searchPanel,
                                    searchPanel.getRight(), searchPanel.getTop(), 0f, revealRadius);
                            show.setDuration(250L);
                            show.setInterpolator(AnimationUtils.loadInterpolator(BaseActivity.this,
                                    android.R.interpolator.fast_out_slow_in));
                            show.start();
                            return false;
                        }
                    });
        }
    }

    /**
     * On Lollipop+ perform a circular animation (a contracting circular mask) when hiding the
     * search panel.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void doExitAnim() {
        final View searchPanel = findViewById(R.id.edt_search);
        // Center the animation on the top right of the panel i.e. near to the search button which
        // launched this screen. The starting radius therefore is the diagonal distance from the top
        // right to the bottom left
        int revealRadius = (int) Math.sqrt(Math.pow(searchPanel.getWidth(), 2)
                + Math.pow(searchPanel.getHeight(), 2));
        // Animating the radius to 0 produces the contracting effect
        Animator shrink = ViewAnimationUtils.createCircularReveal(searchPanel,
                searchPanel.getRight(), searchPanel.getTop(), revealRadius, 0f);
        shrink.setDuration(200L);
        shrink.setInterpolator(AnimationUtils.loadInterpolator(BaseActivity.this,
                android.R.interpolator.fast_out_slow_in));
        shrink.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                searchPanel.setVisibility(View.INVISIBLE);
            }
        });
        shrink.start();
    }


}

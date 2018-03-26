package com.demoapp.utilities;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.activities.ImageCropActivity;
import com.demoapp.interfaces.DialogCallBackListener;
import com.demoapp.interfaces.SetImageListener;
import com.demoapp.singleton.ShowDialogUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Utilities {

    private static Utilities singleton = new Utilities();

    public synchronized static Utilities getInstance() {
        return singleton;
    }

    private static final int REQUEST_CODE_UPDATE_PIC = 0x1;

    private ProgressDialog dialog;
    private Gson gson = new Gson();
    private String job_name;
    private int IS_PROFILE; // the signatures are taking for the profile or not
    private boolean APPLY_ANIMATION; // should animation apply or not on steps screen
    private boolean SHOW_MAP_OFFLINE; // should we show the map route offline or not
    private String MAP_DIRECTION; // temporarily store the map path.
    private String ORDER_NUMBER; // store the order number of the currently running job for showing the map route offline for that job
    private boolean IS_FROM_LIST; // This will recognize the user gone to job details from the list or from the steps
    private int STEPS; // store the back stack fragment count.
    private boolean IS_LAST_FRAGMENT; // check that is this the last fragment in the stack or not

    private String practiceJobType;
    private String practiceJobName;
    private String practiceHaulOffCustomerSignature;
    private String practiceHaulOnCustomerSignature;
    private String practiceTicketImage;
    private String practiceRejectReason;
    private String practiceHaulOffSignatory;
    private String practiceHaulOnSignatory;
    private boolean practiceHaulOffIsCustomerPresent;
    private boolean practiceHaulOnIsCustomerPresent;
    private int practiceJobStep;
    private String practiceStartTime;
    private String practiceTotalTime;


    public String getPracticeTotalTime() {
        return practiceTotalTime;
    }

    public String getORDER_NUMBER() {
        return ORDER_NUMBER;
    }

    public void setORDER_NUMBER(String ORDER_NUMBER) {
        this.ORDER_NUMBER = ORDER_NUMBER;
    }

    public void setPracticeTotalTime(String practiceTotalTime) {
        this.practiceTotalTime = practiceTotalTime;
    }

    public boolean IS_LAST_FRAGMENT() {
        return IS_LAST_FRAGMENT;
    }

    public void setIS_LAST_FRAGMENT(boolean IS_LAST_FRAGMENT) {
        this.IS_LAST_FRAGMENT = IS_LAST_FRAGMENT;
    }

    public String getPracticeStartTime() {
        return practiceStartTime;
    }

    public int getSTEPS() {
        return STEPS;
    }

    public void setSTEPS(int STEPS) {
        this.STEPS = STEPS;
    }

    public String getPracticeHaulOffSignatory() {
        return practiceHaulOffSignatory;
    }

    public void setPracticeHaulOffSignatory(String practiceHaulOffSignatory) {
        this.practiceHaulOffSignatory = practiceHaulOffSignatory;
    }

    public String getPracticeHaulOnSignatory() {
        return practiceHaulOnSignatory;
    }

    public boolean ISFROMLIST() {
        return IS_FROM_LIST;
    }

    public void setIS_FROM_LIST(boolean IS_FROM_LIST) {
        this.IS_FROM_LIST = IS_FROM_LIST;
    }

    public void setPracticeHaulOnSignatory(String practiceHaulOnSignatory) {
        this.practiceHaulOnSignatory = practiceHaulOnSignatory;
    }

    public String getMAP_DIRECTION() {
        return MAP_DIRECTION;
    }


    public void setMAP_DIRECTION(String MAP_DIRECTION) {
        this.MAP_DIRECTION = MAP_DIRECTION;
    }

    public String getPracticeRejectReason() {
        return practiceRejectReason;
    }

    public void setPracticeRejectReason(String practiceRejectReason) {
        this.practiceRejectReason = practiceRejectReason;
    }

    public String getPracticeHaulOffCustomerSignature() {
        return practiceHaulOffCustomerSignature;
    }

    public void setPracticeHaulOffCustomerSignature(String practiceHaulOffCustomerSignature) {
        this.practiceHaulOffCustomerSignature = practiceHaulOffCustomerSignature;
    }

    public String getPracticeHaulOnCustomerSignature() {
        return practiceHaulOnCustomerSignature;
    }

    public void setPracticeHaulOnCustomerSignature(String practiceHaulOnCustomerSignature) {
        this.practiceHaulOnCustomerSignature = practiceHaulOnCustomerSignature;
    }

    public String getPracticeTicketImage() {
        return practiceTicketImage;
    }

    public void setPracticeTicketImage(String practiceTicketImage) {
        this.practiceTicketImage = practiceTicketImage;
    }

    public boolean isPracticeHaulOffIsCustomerPresent() {
        return practiceHaulOffIsCustomerPresent;
    }

    public void setPracticeHaulOffIsCustomerPresent(boolean practiceHaulOffIsCustomerPresent) {
        this.practiceHaulOffIsCustomerPresent = practiceHaulOffIsCustomerPresent;
    }

    public boolean isPracticeHaulOnIsCustomerPresent() {
        return practiceHaulOnIsCustomerPresent;
    }

    public void setPracticeHaulOnIsCustomerPresent(boolean practiceHaulOnIsCustomerPresent) {
        this.practiceHaulOnIsCustomerPresent = practiceHaulOnIsCustomerPresent;
    }

    public int getPracticeJobStep() {
        return practiceJobStep;
    }

    public void setPracticeJobStep(int practiceJobStep) {
        this.practiceJobStep = practiceJobStep;
    }

    public String getPracticeJobType() {
        return practiceJobType;
    }

    public void setPracticeJobType(String practiceJobType) {
        this.practiceJobType = practiceJobType;
    }

    public String getPracticeJobName() {
        return practiceJobName;
    }

    public void setPracticeJobName(String practiceJobName) {
        this.practiceJobName = practiceJobName;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }


    public boolean isSHOW_MAP_OFFLINE() {
        return SHOW_MAP_OFFLINE;
    }

    public void setSHOW_MAP_OFFLINE(boolean SHOW_MAP_OFFLINE) {
        this.SHOW_MAP_OFFLINE = SHOW_MAP_OFFLINE;
    }


    public int getIS_PROFILE() {
        return IS_PROFILE;
    }

    public void setIS_PROFILE(int IS_PROFILE) {
        this.IS_PROFILE = IS_PROFILE;
    }


    public boolean isApplyAnimation() {
        return APPLY_ANIMATION;
    }

    private SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * @description This Method will return the date format
     */
    public SimpleDateFormat getFormatter1() {
        return formatter1;
    }

    /**
     * @description This Method will return the Gson object
     */
    public Gson getGson() {
        return gson;
    }


    /**
     * @param context
     * @param view
     * @description This Method will hide the soft keyboard
     */
    public void hideKeyboard(Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception exp) {
        }
    }

    /**
     * @param imageView
     * @param Path
     * @param isRotate
     * @description This method will set the image from the path on the image view and rotate the image if needed
     */
    public void setImage(ImageView imageView, String Path, boolean isRotate) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap myBitmap = BitmapFactory.decodeFile(Path, bmOptions);
        if (isRotate) {
            imageView.setImageBitmap(rotateImage(myBitmap, 270));
        } else {
            imageView.setImageBitmap(myBitmap);
        }
    }


    /**
     * @param source
     * @param angle
     * @description this method will rotate the image from portrait to landscape
     */
    public Bitmap rotateImage(Bitmap source, float angle) {
        if (source.getHeight() > source.getWidth()) {
            Bitmap retVal;
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
            return retVal;
        } else {
            return source;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @description This method is used to get time difference between to dates or times
     */
    public String getDifferenceInMins(String startTime, String endTime) {
        String difference = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse(startTime);
            endDate = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long different = 0;
        if (startDate != null && endDate != null) {
            different = endDate.getTime() - startDate.getTime();
        }
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        String elapsedHours = String.valueOf(different / hoursInMilli);
        different = different % hoursInMilli;
        String elapsedMinutes = String.valueOf(different / minutesInMilli);
        different = different % minutesInMilli;
        String elapsedSeconds = String.valueOf(different / secondsInMilli);
        Log.e("days", ":" + elapsedDays);
        Log.e("hour", ":" + elapsedHours);
        Log.e("mins", ":" + elapsedMinutes);
        Log.e("secs", ":" + elapsedSeconds);
        difference = String.valueOf(((elapsedDays * 24 * 60) + (Long.parseLong(elapsedHours) * 60)) + Long.parseLong(elapsedMinutes));
        Log.e("difference_in_mins", ": " + difference);
        return difference;
    }

    /**
     * @param mins
     * @description This Method will convert the minutes to hours and minutes
     */
    public String getHoursFromMinutes(String mins) {
        int hours = Integer.parseInt(mins) / 60; //since both are ints, you get an int
        int minutes = Integer.parseInt(mins) % 60;
        Log.e("diffrence", ":" + hours + ":" + minutes);
        String hrs = String.valueOf(hours);
        String mints = String.valueOf(minutes);
        if (hours < 10 && hours > 0)
            hrs = "0" + hours;
        if (hours > 0 || hours < 0) {
            if (minutes < 10 && minutes >= 0) {
                mints = "0" + minutes;
            }
            return hrs + ":" + mints + " hours";
        } else {

            return mints + " mins";

        }
    }


    /**
     * @param activity
     * @param prevStartTime
     * @param prevEndTime
     * @param hour
     * @param minute
     * @param textView
     * @param total_time
     * @param isStart
     * @description This method will check the time we are selecting is lies between the time range needed or not
     */
    public void changeTime(Activity activity, String prevStartTime, String prevEndTime, String hour, String minute, TextView textView, TextView total_time, boolean isStart) {
        Date date = new Date();
        String startDate[] = prevStartTime.split(" ");
        String endDate[] = getFormatter1().format(date).split(" ");
        String startTime[] = startDate[1].split(":");
        String endTime[] = endDate[1].split(":");
        Log.e("end_time", "final: " + endTime[1]);
        Log.e("hour", "final: " + minute);
        if (Integer.parseInt(startTime[0]) > Integer.parseInt(hour)) {
            ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning)
                    , activity.getResources().getString(R.string.start_time_exception) + " " + startTime[0] + ":" + startTime[1]);
        } else if (Integer.parseInt(startTime[0]) == Integer.parseInt(hour) && Integer.parseInt(startTime[1]) > Integer.parseInt(minute)) {
            ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning)
                    , activity.getResources().getString(R.string.start_time_exception) + " " + startTime[0] + ":" + startTime[1]);
        } else if (Integer.parseInt(endTime[0]) < Integer.parseInt(hour)) {
            ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning)
                    , activity.getResources().getString(R.string.end_time_exception) + " " + endTime[0] + ":" + endTime[1]);
        } else if (Integer.parseInt(endTime[0]) == Integer.parseInt(hour) && Integer.parseInt(endTime[1]) < Integer.parseInt(minute)) {
            ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getString(R.string.warning)
                    , activity.getResources().getString(R.string.end_time_exception) + " " + endTime[0] + ":" + endTime[1]);
        } else {
            if (Integer.parseInt(hour) < 10) {
                hour = "0" + hour;
            }
            if (Integer.parseInt(minute) < 10) {
                minute = "0" + minute;
            }
            textView.setText(hour + ":" + minute);
            Log.e("start_timeeee", ":" + startDate[0] + " " + hour + ":" + minute + ":00");
            Log.e("end_timeeee", ":" + prevEndTime);
            Log.e("prev_timeeee", ":" + prevStartTime);
            if (total_time != null) {
                if (isStart) {
                    total_time.setText(getHoursFromMinutes(getDifferenceInMins(startDate[0] + " " + hour + ":" + minute + ":00", prevEndTime)));
                } else {
                    total_time.setText(getHoursFromMinutes(getDifferenceInMins(prevStartTime, endDate[0] + " " + hour + ":" + minute + ":00")));
                }
            }
        }
    }

    /**
     * @param time
     * @description This Method will return the hour and minutes from the date
     */
    public String getHoursMinutes(String time) {
        Log.e("time", ":: " + time);
        String s1[] = time.split(" ");
        String s2[] = s1[1].split(":");
        return s2[0] + ":" + s2[1];
    }

    /**
     * @param mapView
     * @param savedInstanceState
     * @param activity
     * @description This method is used to load the map on map view
     */
    public GoogleMap intialiseMap(MapView mapView, Bundle savedInstanceState, Activity activity) {
        GoogleMap map = null;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
        // Gets the MapView from the XML layout and creates it
        MapsInitializer.initialize(activity);
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity)) {
            case ConnectionResult.SUCCESS:
                mapView.onCreate(savedInstanceState);
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setCompassEnabled(true);
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    if (ActivityCompat.checkSelfPermission(activity,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                    PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return map;
                    }
                    map.setMyLocationEnabled(true);
                    map.getUiSettings().setRotateGesturesEnabled(true);
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning), "SERVICE MISSING");
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning), "UPDATE REQUIRED");
                break;
            default:
                ShowDialogUtils.getInstance().showAlertDialog(activity, activity.getResources().getString(R.string.warning), String.valueOf(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity)));

        }
        return map;
    }


    /**
     * @param activity
     * @param view
     * @param animation
     * @description This method is used to animate the view
     */
    public void setAnimation(Activity activity, View view, int animation) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(activity, animation);
        view.startAnimation(fadeInAnimation);
    }


    /**
     * @description Dismiss a dialog if already showing on this class's dialog's instance.
     */
    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    /**
     * @param activity
     * @description this method will ask the user to select photo from camera or gallery
     */
    public void choosePhotoDialog(final Activity activity) {
        final Dialog alertDialog = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_confirm);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationzoom;
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button mCamera = (Button) alertDialog.findViewById(R.id.camera);
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission(activity, Manifest.permission.CAMERA) && checkPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Intent intent = new Intent(activity, ImageCropActivity.class);
                    intent.putExtra("ACTION", "action-camera");
                    activity.startActivityForResult(intent, REQUEST_CODE_UPDATE_PIC);
                    alertDialog.dismiss();
                }
                alertDialog.dismiss();
            }
        });
        Button mGallery = (Button) alertDialog.findViewById(R.id.gallery);
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission(activity, Manifest.permission.CAMERA) && checkPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Intent intent = new Intent(activity, ImageCropActivity.class);
                    intent.putExtra("ACTION", "action-gallery");
                    activity.startActivityForResult(intent, REQUEST_CODE_UPDATE_PIC);
                }
                alertDialog.dismiss();
            }
        });
        TextView mCancel = (TextView) alertDialog.findViewById(R.id.cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * @param activity
     * @param firstLayout
     * @param secondLayout
     * @param firstButton
     * @param secondButton
     * @param isFirst
     * @description This method will change the view of layout and colors of top button
     */
    public void changeView(Activity activity, LinearLayout firstLayout, RelativeLayout secondLayout
            , Button firstButton, Button secondButton, boolean isFirst) {
        if (isFirst) {
            firstLayout.setVisibility(View.VISIBLE);
            firstButton.setBackgroundColor(ContextCompat.getColor(activity, R.color.blue_text));
            firstButton.setTextColor(Color.WHITE);
            secondLayout.setVisibility(View.GONE);
            secondButton.setBackgroundColor(Color.TRANSPARENT);
            secondButton.setTextColor(ContextCompat.getColor(activity, R.color.blue_text));
        } else {
            firstLayout.setVisibility(View.GONE);
            firstButton.setBackgroundColor(Color.TRANSPARENT);
            firstButton.setTextColor(ContextCompat.getColor(activity, R.color.blue_text));
            secondLayout.setVisibility(View.VISIBLE);
            secondButton.setBackgroundColor(ContextCompat.getColor(activity, R.color.blue_text));
            secondButton.setTextColor(Color.WHITE);
        }
    }

    /**
     * @param context
     * @return True, if device is having a Internet connection available.
     */
    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * @param key          The key from you want to get the value.
     * @param defaultValue Default value, if nothing is found on that key.
     * @param context
     * @description To get the value from a preference file on the specified key.
     */
    public String getValueFromSharedPreference(String key, String defaultValue, Context context) {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("AllianceGoFile", 0);
        return prefs.getString(key, defaultValue);
    }


    /**
     * @param key     On which key you want to save the value.
     * @param value   The value which needs to be saved.
     * @param context
     * @description To save the value to a saved preference file on the specified key.
     */
    public void saveValueToSharedPreference(String key, boolean value, Context context) {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("AllianceGoFile", 0);
        SharedPreferences.Editor saveValue = prefs.edit();
        saveValue.putBoolean(key, value);
        saveValue.apply();
    }

    /**
     * @param key     On which key you want to save the value.
     * @param value   The value which needs to be saved.
     * @param context
     * @description To save the value to a preference file on the specified key.
     */
    public void saveValueToSharedPreference(String key, String value,
                                            Context context) {
        if (context != null) {
            Log.e("here", ": 1");
            SharedPreferences prefs;
            prefs = context.getSharedPreferences("AllianceGoFile", 0);
            SharedPreferences.Editor saveValue = prefs.edit();
            saveValue.putString(key, value);
            saveValue.apply();
        } else {
            Log.e("here", ": 2");
        }
    }

    /**
     * @param activity
     * @description This Method will check the gps is active or not
     */
    public boolean locationEnabled(Activity activity) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(activity.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_MODE);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    /**
     * @param activity
     * @description This method will ask the user to enable the gps location from the settings
     */
    public void enableGps(final Activity activity) {
        ShowDialogUtils.getInstance().showAlertDialogCallback(activity,
                activity.getResources().getString(R.string.warning), activity.getResources().getString(R.string.ask_location_permission), new DialogCallBackListener() {
                    @Override
                    public void callBack() {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(myIntent);
                    }
                }, false);
    }


    /**
     * @param signature
     * @param imageListener
     * @param activity
     * @param imageName
     * @description This method will save the signatures
     */
    public void saveImage(Bitmap signature, SetImageListener imageListener, Activity activity, String imageName) {
        String root = Environment.getExternalStorageDirectory().toString();
        // the directory where the signature will be saved
        File myDir = new File(root + "/.alliancego/");
        // make the directory if it does not exist yet
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
//        String fname = Utilities.getValueFromSharedPreference(Constants.ORDER_NO,"",activity)+ ".png";
        // in our case, we delete the previous file, you can remove this
        File file = new File(myDir, imageName + ".png");
        /*if (file.exists()) {
            file.delete();
        }*/
        // save the signature
        try {
            FileOutputStream out = new FileOutputStream(file);
            signature.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Log.e("path", "file:  " + file.getPath());
            Log.e("path", ":absolute" + file.getAbsolutePath());
            Log.e("profile", ": " + getIS_PROFILE());
            if (getIS_PROFILE() == 0)
                imageListener.setProofImage(file.getPath());
            else if (getIS_PROFILE() == 1)
                imageListener.setProfileImage(file.getPath());
            else if (getIS_PROFILE() == 2)
                imageListener.setJobSignature(file.getPath());
            activity.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param feild
     * @description this method is used to check String is null or not
     */
    public String checkNull(String feild) {
        if (feild != null && feild.length() > 0) {
            return feild;
        } else {
            return "";
        }
    }

    /**
     * @param activity
     * @param permission
     * @description This method is used to check loaction permisssion
     */
    public boolean checkPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission, permission}, 10);
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param context
     * @param doneStep
     * @param doneCheckImage
     * @param nextStepLayout
     * @param nextStepTItle
     * @param nextStep
     * @description This Method is used to move to next job step
     */
    public void setStepBackgroud(Context context, TextView doneStep, ImageView doneCheckImage, LinearLayout nextStepLayout, TextView nextStepTItle, TextView nextStep) {
        doneStep.setVisibility(View.GONE);
        doneCheckImage.setVisibility(View.VISIBLE);
        nextStepLayout.setBackgroundResource(R.drawable.shadow_white);
        nextStepTItle.setTextColor(ContextCompat.getColor(context, R.color.blue_text));
        nextStep.setTextColor(ContextCompat.getColor(context, R.color.blue_text));
    }


    /**
     * @param result
     * @description This method wil check the first four alphabet of QR code and return that QR code is valid or not
     */
    public boolean isQRValid(String result) {
        if (result.substring(0, 1).matches("\\d")
                && result.substring(1, 2).matches("\\d")
                && result.substring(2, 3).matches("\\d")
                && result.substring(3, 4).matches("\\d")) {
            Log.e("right", "::" + result.substring(0, 1));
            Log.e("right", "::" + result.substring(1, 2));
            Log.e("right", "::" + result.substring(2, 3));
            Log.e("right", "::" + result.substring(3, 4));

            int hour1 = Integer.parseInt(result.substring(0, 1) + result.substring(1, 2));
            int minute1 = Integer.parseInt(result.substring(2, 3) + result.substring(3, 4));
            int total_nimnutes1 = (hour1 * 60) + minute1;
            Log.e("hour:", ":: " + hour1 + " minutes:: " + minute1);

            Date date = new Date();
            String s1[] = Utilities.getInstance().getFormatter1().format(date).split(" ");
            Log.e("hour:", ":: " + Utilities.getInstance().getFormatter1().format(date));
            String s2[] = s1[1].split(":");


            int hour2 = Integer.parseInt(s2[0]);
            int minute2 = Integer.parseInt(s2[1]);
            int total_nimnutes2 = (hour2 * 60) + minute2;
            Log.e("hour:", ":: " + s2[0] + " minutes:: " + s2[1]);

            int difference = total_nimnutes1 - total_nimnutes2;
            Log.e("difference", ":" + difference);
            if (Math.abs(difference) <= 5) {
                return true;
            } else {
                return false;
            }
        } else {
            Log.e("wrong", "::" + result.substring(0, 1));
            Log.e("wrong", "::" + result.substring(1, 2));
            Log.e("wrong", "::" + result.substring(2, 3));
            Log.e("wrong", "::" + result.substring(3, 4));
            return false;
        }
    }

    /**
     * @param activity
     * @param number
     * @description This method is used to display the number to the dialing screen.
     */
    public void makeCall(Activity activity, String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        activity.startActivity(callIntent);
    }

    public void setPracticeStartTime(String practiceStartTime) {
        this.practiceStartTime = practiceStartTime;
    }

    public void setApplyAnimation(boolean applyAnimation) {
        APPLY_ANIMATION = applyAnimation;
    }

}
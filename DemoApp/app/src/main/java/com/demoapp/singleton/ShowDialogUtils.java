package com.demoapp.singleton;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.demoapp.R;
import com.demoapp.interfaces.DialogCallBackListener;
import com.demoapp.interfaces.StringCallbackListner;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Created by android5 on 21/7/16.
 */
public class ShowDialogUtils {

    private static ShowDialogUtils singleton = new ShowDialogUtils();

    public synchronized static ShowDialogUtils getInstance() {
        return singleton;
    }

    /**
     * @description This method will display the ticket number
     */
    public void showImageDialog(Activity activity, String imagePath) {
        final Dialog alertDialog = initiateDialog(activity);
        alertDialog.setContentView(R.layout.dialog_view_image);
        ImageView imageView = (ImageView) alertDialog.findViewById(R.id.image_view);
        Button btnDone = (Button) alertDialog.findViewById(R.id.done_button);
        Utilities.getInstance().setImage(imageView, imagePath, true);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showAlertDialog(Activity activity, String title, String message) {
        final Dialog alertDialog = initiateDialog(activity);
        alertDialog.setContentView(R.layout.dialog_message_alert);
        final TextView titleText = (TextView) alertDialog.findViewById(R.id.title_text);
        final TextView messageText = (TextView) alertDialog.findViewById(R.id.message_text);
        final TextView okText = (TextView) alertDialog.findViewById(R.id.ok_text);
        titleText.setText(title);
        messageText.setText(message);
        okText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showAlertDialogCallback(Activity activity, String title, String message, final DialogCallBackListener dialogCallBackListener, boolean isCancelable) {
        final Dialog alertDialog = initiateDialog(activity);
        alertDialog.setContentView(R.layout.dialog_message_alert);
        if (!isCancelable)
            alertDialog.setCancelable(false);
        final TextView titleText = (TextView) alertDialog.findViewById(R.id.title_text);
        final TextView messageText = (TextView) alertDialog.findViewById(R.id.message_text);
        final TextView okText = (TextView) alertDialog.findViewById(R.id.ok_text);
        titleText.setText(title);
        messageText.setText(message);

        okText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                dialogCallBackListener.callBack();
            }
        });
        alertDialog.show();
    }


    public void askDialog(Activity activity, String message, final DialogCallBackListener dialogCallBackListener) {
        final Dialog alertDialog = initiateDialog(activity);
        alertDialog.setCancelable(false);
        alertDialog.setContentView(R.layout.dialog_cancel_alert);
        final TextView messageText = (TextView) alertDialog.findViewById(R.id.message);
        messageText.setText(message);
        final Button yesBtn = (Button) alertDialog.findViewById(R.id.yes_button);
        final Button noBtn = (Button) alertDialog.findViewById(R.id.no_button);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBackListener.callBack();
                alertDialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public Dialog initiateDialog(Activity activity) {
        final Dialog alertDialog = new Dialog(activity);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationzoom;
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.dismiss();
        return alertDialog;
    }
}
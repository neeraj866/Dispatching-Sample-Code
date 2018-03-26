package com.demoapp.abstract_classes;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.demoapp.R;
import com.demoapp.interfaces.StringCallbackListner;
import com.demoapp.singleton.ShowDialogUtils;


/**
 * Created by android5 on 21/7/16.
 */
public abstract class RejectDialog implements StringCallbackListner {

    private String rejectReason;

    public void openRejectReasonDialog(Activity activity) {

        final Dialog alertDialog = ShowDialogUtils.getInstance().initiateDialog(activity);
        alertDialog.setContentView(R.layout.dialog_reject_reson);

        final RadioButton wrongDelivery = (RadioButton) alertDialog.findViewById(R.id.wrong_delivery);
        final RadioButton wrongMaterial = (RadioButton) alertDialog.findViewById(R.id.wrong_material);
        final RadioButton poorQuality = (RadioButton) alertDialog.findViewById(R.id.poor_quality);
        final RadioButton customerRefused = (RadioButton) alertDialog.findViewById(R.id.customer_refused);
        final RadioButton otherReason = (RadioButton) alertDialog.findViewById(R.id.other);

        final EditText enterReason = (EditText) alertDialog.findViewById(R.id.reject_reason);

        Button btnNo = (Button) alertDialog.findViewById(R.id.no_button);
        Button btnYes = (Button) alertDialog.findViewById(R.id.yes_button);

        rejectReason = wrongDelivery.getText().toString().trim();

        wrongDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rejectReason = wrongDelivery.getText().toString().trim();
                }
            }
        });
        wrongMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rejectReason = wrongMaterial.getText().toString().trim();
                }
            }
        });
        poorQuality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rejectReason = poorQuality.getText().toString().trim();
                }
            }
        });
        customerRefused.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rejectReason = customerRefused.getText().toString().trim();
                }
            }
        });
        otherReason.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enterReason.setVisibility(View.VISIBLE);
                } else {
                    enterReason.setVisibility(View.GONE);
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectReason = "";
                alertDialog.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otherReason.isChecked()) {
                    rejectReason = enterReason.getText().toString().trim();
                }
                receiveData(rejectReason);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public abstract void receiveData(String result);
}

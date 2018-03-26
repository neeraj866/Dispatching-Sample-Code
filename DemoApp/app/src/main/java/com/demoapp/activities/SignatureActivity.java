package com.demoapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.demoapp.R;
import com.demoapp.interfaces.SetImageListener;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;


/**
 * Created by android5 on 21/6/16.
 */
public class SignatureActivity extends FragmentActivity implements View.OnClickListener {

    private static SetImageListener imageListener;
    private ImageButton mImBtnCancel;
    private ImageButton mImBtnReload;
    private ImageButton mImBtnSave;

    private SignatureView mSignatureView;

    public void callBack(SetImageListener setImageListener) {
        imageListener = setImageListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_signature);
        init();
    }

    /**
     * @description This method is used to find views from layout
     */
    private void init() {
        mImBtnCancel = (ImageButton) findViewById(R.id.cancel);
        LinearLayout mLltSignatureLayout = (LinearLayout) findViewById(R.id.signature_layout);
        mImBtnReload = (ImageButton) findViewById(R.id.reload);
        mImBtnSave = (ImageButton) findViewById(R.id.save);

        mSignatureView = new SignatureView(SignatureActivity.this);
        mLltSignatureLayout.addView(mSignatureView);

        setOnClickListener();
        mImBtnCancel.setOnClickListener(this);
        mImBtnReload.setOnClickListener(this);
        mImBtnSave.setOnClickListener(this);
    }

    private void setOnClickListener() {
        mImBtnCancel.setOnClickListener(this);
        mImBtnReload.setOnClickListener(this);
        mImBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                finish();
                break;

            case R.id.reload:
                mSignatureView.clearSignature();
                break;

            case R.id.save:
                Utilities.getInstance().saveImage(mSignatureView.getSignature(), imageListener, SignatureActivity.this, Constants.IMAGE_NAME);
                break;
        }
    }
}

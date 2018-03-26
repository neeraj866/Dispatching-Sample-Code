package com.demoapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.demoapp.R;
import com.demoapp.interfaces.StringCallbackListner;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by android5 on 23/7/16.
 */
public class QRCodeActivity extends Activity implements View.OnClickListener, BarcodeCallback {

    private static StringCallbackListner stringCallbackListner;

    private CaptureManager capture;

    private CompoundBarcodeView barcodeScannerView;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);
        init(savedInstanceState);
    }

    /**
     * @description This method is used to initialize the StringCallbackListener
     */
    public void initializeListener(StringCallbackListner stringCallbackListner) {
        QRCodeActivity.stringCallbackListner = stringCallbackListner;
    }

    /**
     * @description This method is used to find views from the layout & initialize the camera for QR code scanning
     */
    private void init(Bundle savedInstanceState) {
        barcodeScannerView = (CompoundBarcodeView) findViewById(R.id.zxing_barcode_scanner);
        RelativeLayout mBack = (RelativeLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        barcodeScannerView.decodeContinuous(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void barcodeResult(BarcodeResult result) {
        Log.e("result", ":: " + result);
        if (count < 1) {
            stringCallbackListner.receiveData(String.valueOf(result));
            count++;
        }
        finish();
    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {
    }
}
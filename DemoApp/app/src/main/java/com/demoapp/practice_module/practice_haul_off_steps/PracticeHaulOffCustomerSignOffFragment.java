package com.demoapp.practice_module.practice_haul_off_steps;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.activities.QRCodeActivity;
import com.demoapp.activities.SignatureActivity;
import com.demoapp.interfaces.SetImageListener;
import com.demoapp.interfaces.StringCallbackListner;
import com.demoapp.map.MapFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.logging.Logger;


/**
 * Created by android5 on 21/7/16.
 */
public class PracticeHaulOffCustomerSignOffFragment extends Fragment implements View.OnClickListener, RatingBar.OnRatingBarChangeListener, CompoundButton.OnCheckedChangeListener, SetImageListener, View.OnFocusChangeListener, StringCallbackListner {

    private Button mBtnDone;
    private Button mBtnJobDetails;
    private Button mBtnMapView;

    private TextView mTvMaterialType;
    private TextView mTvFromAddress;
    private TextView mTvToAddress;
    private TextView mTvSignatureDummyText;
    private TextView mTvFromAddressMap;
    private TextView mTvToAddressMap;
    private TextView mTvHaulOffPo;
    private TextView mTvMaterialQuantity;
    private TextView mTvPickupTime;
    private TextView mTvAlliancePoLabel;
    private TextView mTvFromCustomerName;
    private TextView mTvFromJobSite;
    private TextView mTvToCustomerName;
    private TextView mTvToJobSite;

    private CheckBox mChkNoCustomer;

    private EditText mEtSignerName;

    private ImageView mImCustomerSignature;

    private RelativeLayout mRltScanQrCode;
    private RelativeLayout mRltSignatureLayout;
    private RelativeLayout mRltNoCustomer;
    private RelativeLayout mRltMapView;

    private LinearLayout mLltScanQrCodeLayout;
    private LinearLayout mLltJobDetails;
    private LinearLayout mLltSignerLayout;
    private LinearLayout mLltRatingBarLayout;
    private LinearLayout mLltStepsLayout;
    private LinearLayout mLltDropOffStepsLayout;

    private RatingBar mRatingBar;

    private String mStrQrCodeScannedResult;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return init(inflater, container);
    }

    /**
     * @description This method is used to find views from the layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.fragment_customer_sign_off_layout, container, false);

        mTvMaterialType = (TextView) view.findViewById(R.id.material_type);
        mTvFromAddress = (TextView) view.findViewById(R.id.from_address);
        mTvToAddress = (TextView) view.findViewById(R.id.to_address);
        mTvFromAddressMap = (TextView) view.findViewById(R.id.from_map_address);
        mTvToAddressMap = (TextView) view.findViewById(R.id.to_map_address);
        mTvHaulOffPo = (TextView) view.findViewById(R.id.haul_off_po);
        mTvPickupTime = (TextView) view.findViewById(R.id.pick_up_time);
        mTvSignatureDummyText = (TextView) view.findViewById(R.id.dummytext);
        mTvMaterialQuantity = (TextView) view.findViewById(R.id.material_qty);
        mTvAlliancePoLabel = (TextView) view.findViewById(R.id.alliance_po_label);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.from_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.from_job_site);
        mTvToCustomerName = (TextView) view.findViewById(R.id.to_customer_name);
        mTvToJobSite = (TextView) view.findViewById(R.id.to_job_site);

        mBtnDone = (Button) view.findViewById(R.id.done_button);
        mBtnJobDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mChkNoCustomer = (CheckBox) view.findViewById(R.id.no_customer);

        mEtSignerName = (EditText) view.findViewById(R.id.first_signer_name);

        mImCustomerSignature = (ImageView) view.findViewById(R.id.signature_image);

        mRltScanQrCode = (RelativeLayout) view.findViewById(R.id.scan_qr_code);
        mLltScanQrCodeLayout = (LinearLayout) view.findViewById(R.id.qr_code_layout);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingbar);
        mRltSignatureLayout = (RelativeLayout) view.findViewById(R.id.signature_layout);
        mRltNoCustomer = (RelativeLayout) view.findViewById(R.id.no_customer_layout);

        mLltJobDetails = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltSignerLayout = (LinearLayout) view.findViewById(R.id.signer_layout);
        mLltRatingBarLayout = (LinearLayout) view.findViewById(R.id.ratingbar_layout);
        mLltStepsLayout = (LinearLayout) view.findViewById(R.id.steps_layout);
        mLltDropOffStepsLayout = (LinearLayout) view.findViewById(R.id.drop_off_steps_layout);

        mRltMapView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SignatureActivity signatureActivity = new SignatureActivity();
        signatureActivity.callBack(this);
        Utilities.getInstance().setSTEPS(3);
        setOnClickListener();
        QRCodeActivity qrCodeActivity = new QRCodeActivity();
        qrCodeActivity.initializeListener(this);
        getData();
    }

    /**
     * @description This method is used to set click listeners
     */
    private void setOnClickListener() {
        mBtnDone.setOnClickListener(this);
        mBtnJobDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mRltScanQrCode.setOnClickListener(this);
        mImCustomerSignature.setOnClickListener(this);
        mRatingBar.setOnRatingBarChangeListener(this);
        mChkNoCustomer.setOnCheckedChangeListener(this);
        mEtSignerName.setOnFocusChangeListener(this);
        mLltJobDetails.setOnClickListener(this);
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {


        mTvPickupTime.setBackgroundResource(R.drawable.rounded_corner);
        mTvPickupTime.setClickable(false);


        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_off))) {
            mLltStepsLayout.setVisibility(View.VISIBLE);
            mLltDropOffStepsLayout.setVisibility(View.GONE);

            mTvFromJobSite.setText(getResources().getString(R.string.job_site));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));

            mTvFromCustomerName.setText("Alliance Go Trucking");


            mTvToJobSite.setText(getResources().getString(R.string.drop_address));
            mTvToAddress.setText(getResources().getString(R.string.to_address));

            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Andrew");

        } else {
            mLltStepsLayout.setVisibility(View.GONE);
            mLltDropOffStepsLayout.setVisibility(View.VISIBLE);

            mTvFromJobSite.setText(getResources().getString(R.string.job_site1));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));

            mTvFromCustomerName.setText("Alliance Go Trucking");

            mTvToJobSite.setText(getResources().getString(R.string.job_site2));
            mTvToAddress.setText(getResources().getString(R.string.to_address));

            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Alliance Go Trucking");
        }


        mTvMaterialType.setText(getResources().getString(R.string.default_material));


        mTvMaterialQuantity.setText(getResources().getString(R.string.one_load) + " " + getResources().getString(R.string.load));
        mTvMaterialQuantity.setFocusable(false);
        mTvMaterialQuantity.setFocusableInTouchMode(false);
        mTvMaterialQuantity.setHint("");

        mTvFromAddressMap.setText(getResources().getString(R.string.from_address));
        mTvToAddressMap.setText(getResources().getString(R.string.to_address));

        mTvAlliancePoLabel.setText(getResources().getString(R.string.alliance_po));
        mTvHaulOffPo.setText("1234");

        String s1[] = Utilities.getInstance().getPracticeStartTime().split(" ");
        String s2[] = s1[1].split(":");
        mTvPickupTime.setText(s2[0] + ":" + s2[1]);

        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.START_LATITUDE, getResources().getString(R.string.from_lati));
        bundle.putSerializable(Constants.START_LONGITUDE, getResources().getString(R.string.from_longi));
        bundle.putSerializable(Constants.END_LATITUDE, getResources().getString(R.string.to_lati));
        bundle.putSerializable(Constants.END_LONGITUDE, getResources().getString(R.string.to_longi));
        mapFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.map_frame, mapFragment).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        Utilities.getInstance().hideKeyboard(getActivity(), v);
        switch (v.getId()) {
            case R.id.details_button:
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapView, mBtnJobDetails, mBtnMapView, true);
                break;

            case R.id.map_button:
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapView, mBtnJobDetails, mBtnMapView, false);
                break;

            case R.id.signature_image:
                Constants.IMAGE_NAME = getResources().getString(R.string.haul_off) + getResources().getString(R.string.customer_signature) + "1234";
                if (!Utilities.getInstance().checkPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    return;
                }
                Utilities.getInstance().setIS_PROFILE(2);
                Intent intent = new Intent(getActivity(), SignatureActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.scan_qr_code:
                new IntentIntegrator(getActivity()).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES).setOrientationLocked(true).setCaptureActivity(QRCodeActivity.class).initiateScan();
                break;

            case R.id.done_button:
                if (!mChkNoCustomer.isChecked()) {
                    if (TextUtils.isEmpty(mStrQrCodeScannedResult)) {
                        if (TextUtils.isEmpty(mEtSignerName.getText().toString().trim())) {
                            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_signer_name));
                            return;
                        }
                        if (TextUtils.isEmpty(Utilities.getInstance().getPracticeHaulOffCustomerSignature())) {
                            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.enter_customer_sign));
                            return;
                        }
                    }
                    Utilities.getInstance().setPracticeHaulOffSignatory(mEtSignerName.getText().toString().trim());
                    Utilities.getInstance().setPracticeHaulOffIsCustomerPresent(true);
                } else {
                    Utilities.getInstance().setPracticeHaulOffIsCustomerPresent(false);
                }
                Utilities.getInstance().setPracticeJobStep(2);

                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mRatingBar.setRating(rating);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int visibility = View.VISIBLE;
        if (isChecked) {
            visibility = View.GONE;
        }
        mLltSignerLayout.setVisibility(visibility);
        mRltSignatureLayout.setVisibility(visibility);
        mLltScanQrCodeLayout.setVisibility(visibility);
        mLltRatingBarLayout.setVisibility(visibility);
    }

    @Override
    public void setProofImage(String Path) {
    }

    @Override
    public void setProfileImage(String Path) {
    }

    @Override
    public void setJobSignature(String Path) {
        Utilities.getInstance().setPracticeHaulOffCustomerSignature(Path);
        mTvSignatureDummyText.setVisibility(View.GONE);
        Utilities.getInstance().setImage(mImCustomerSignature, Path, true);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.first_signer_name:
                if (!hasFocus) {
                    Utilities.getInstance().hideKeyboard(getActivity(), v);
                }
                break;
        }
    }


    @Override
    public void receiveData(String result) {
        Log.e("QR_CODE", ":" + result);
        if (Utilities.getInstance().isQRValid(result)) {
            mStrQrCodeScannedResult = result;
            mLltSignerLayout.setVisibility(View.GONE);
            mRltSignatureLayout.setVisibility(View.GONE);
            mRltNoCustomer.setVisibility(View.GONE);
        } else {
            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources()
                    .getString(R.string.warning), getResources().getString(R.string.invalid_qr_code));
        }
    }
}
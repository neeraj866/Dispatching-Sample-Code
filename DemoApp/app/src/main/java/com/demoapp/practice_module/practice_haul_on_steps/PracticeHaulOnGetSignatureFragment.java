package com.demoapp.practice_module.practice_haul_on_steps;

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
import com.demoapp.abstract_classes.RejectDialog;
import com.demoapp.activities.QRCodeActivity;
import com.demoapp.activities.SignatureActivity;
import com.demoapp.interfaces.DialogCallBackListener;
import com.demoapp.interfaces.SetImageListener;
import com.demoapp.interfaces.StringCallbackListner;
import com.demoapp.map.MapFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;
import com.google.zxing.integration.android.IntentIntegrator;



/**
 * Created by android5 on 17/7/16.
 */
public class PracticeHaulOnGetSignatureFragment extends Fragment implements View.OnClickListener, SetImageListener, RatingBar.OnRatingBarChangeListener, CompoundButton.OnCheckedChangeListener, StringCallbackListner {

    private Button mBtnDone;
    private Button mBtnJobDetails;
    private Button mBtnMapView;

    private TextView mTvMaterialType;
    private TextView mTvFromAddress;
    private TextView mTvToAddress;
    private TextView mTvSignatureDummyText;
    private TextView mTvFromAddressMap;
    private TextView mTvToAddressMap;
    private TextView mTvStartTime;
    private TextView mTvFromCustomerName;
    private TextView mTvFromJobSite;
    private TextView mTvToCustomerName;
    private TextView mTvToJobSite;
    private TextView mTvUnits;
    private TextView mTvEndTime;
    private TextView mTvPickupLabel;
    private TextView mTvPickupTime;

    private CheckBox mChkNoCustomer;

    private EditText mEtMaterialQuantity;
    private EditText mEtSignerName;

    private ImageView mImCustomerSignature;

    private RelativeLayout mRltScanQrCode;
    private RelativeLayout mRltRejectLoad;
    private RelativeLayout mRltAcceptLoad;
    private RelativeLayout mRltSignatureLayout;
    private RelativeLayout mRltNoCustomer;
    private RelativeLayout mRltMapView;

    private LinearLayout mLltScanQrCodeLayout;
    private LinearLayout mLltJobDetails;
    private LinearLayout mLltSignerLayout;
    private LinearLayout mLltRatingBarLayout;
    private LinearLayout mLltStepsLayout;
    private LinearLayout mLltDropOffStepsLayout;
    private LinearLayout mLltBreakLayout;
    private LinearLayout mLltTimeLayout;

    private RatingBar mRatingBar;

    private String mStrQrCodeScannedResult;
    private String mStrRejectReason;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utilities.getInstance().setSTEPS(3);
        SignatureActivity signatureActivity = new SignatureActivity();
        signatureActivity.callBack(this);
        setOnClickListener();
        QRCodeActivity qrCodeActivity = new QRCodeActivity();
        qrCodeActivity.initializeListener(this);
        getData();
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_get_signature_layout, container, false);

        mBtnDone = (Button) view.findViewById(R.id.done_button);
        mBtnJobDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mTvMaterialType = (TextView) view.findViewById(R.id.material_type);
        mTvFromAddress = (TextView) view.findViewById(R.id.from_address);
        mTvFromAddressMap = (TextView) view.findViewById(R.id.from_map_address);
        mTvToAddress = (TextView) view.findViewById(R.id.to_address);
        mTvToAddressMap = (TextView) view.findViewById(R.id.to_map_address);
        mTvSignatureDummyText = (TextView) view.findViewById(R.id.dummytext);
        mTvStartTime = (TextView) view.findViewById(R.id.start_time);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.from_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.from_Job_site);
        mTvToCustomerName = (TextView) view.findViewById(R.id.to_customer_name);
        mTvToJobSite = (TextView) view.findViewById(R.id.to_job_site);
        mTvUnits = (TextView) view.findViewById(R.id.units);
        mTvEndTime = (TextView) view.findViewById(R.id.end_time);
        mTvPickupLabel = (TextView) view.findViewById(R.id.pick_up);
        mTvPickupTime = (TextView) view.findViewById(R.id.pick_up_time);

        mEtMaterialQuantity = (EditText) view.findViewById(R.id.material_qty);
        mEtSignerName = (EditText) view.findViewById(R.id.first_signer_name);

        mLltJobDetails = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltSignerLayout = (LinearLayout) view.findViewById(R.id.signer_layout);
        mLltRatingBarLayout = (LinearLayout) view.findViewById(R.id.ratingbar_layout);
        mLltStepsLayout = (LinearLayout) view.findViewById(R.id.steps_layout);
        mLltDropOffStepsLayout = (LinearLayout) view.findViewById(R.id.drop_off_steps_layout);
        mLltBreakLayout = (LinearLayout) view.findViewById(R.id.break_layout);
        mLltTimeLayout = (LinearLayout) view.findViewById(R.id.time_layout);

        mRltScanQrCode = (RelativeLayout) view.findViewById(R.id.scan_qr_code);
        mRltSignatureLayout = (RelativeLayout) view.findViewById(R.id.signature_layout);
        mLltScanQrCodeLayout = (LinearLayout) view.findViewById(R.id.qr_code_layout);
        mRltRejectLoad = (RelativeLayout) view.findViewById(R.id.reject_load);
        mRltAcceptLoad = (RelativeLayout) view.findViewById(R.id.accept_layout);
        mRltNoCustomer = (RelativeLayout) view.findViewById(R.id.no_customer_layout);

        mImCustomerSignature = (ImageView) view.findViewById(R.id.signature_image);

        mRatingBar = (RatingBar) view.findViewById(R.id.ratingbar);

        mChkNoCustomer = (CheckBox) view.findViewById(R.id.no_customer);

        return view;
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {
        mTvMaterialType.setText(getResources().getString(R.string.default_material));
        mTvUnits.setText("");
        mEtMaterialQuantity.setText(getResources().getString(R.string.one_load) + " " + getResources().getString(R.string.load));
        mEtMaterialQuantity.setBackgroundResource(R.drawable.rounded_corner);
        mEtMaterialQuantity.setFocusable(false);
        mEtMaterialQuantity.setHint("");
        mLltTimeLayout.setVisibility(View.GONE);
        mLltBreakLayout.setVisibility(View.GONE);
        mTvPickupLabel.setVisibility(View.VISIBLE);
        mTvPickupTime.setVisibility(View.VISIBLE);
        mTvPickupTime.setText(Utilities.getInstance().getHoursMinutes(Utilities.getInstance().getPracticeStartTime()));
        mTvFromAddress.setText(getResources().getString(R.string.from_address));
        mTvToAddress.setText(getResources().getString(R.string.to_address));
        mTvFromAddressMap.setText(getResources().getString(R.string.from_address));
        mTvToAddressMap.setText(getResources().getString(R.string.to_address));

        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_on))) {
            mTvFromJobSite.setText(getResources().getString(R.string.pit_address));
            mTvFromCustomerName.setVisibility(View.VISIBLE);
            mTvFromCustomerName.setText("John");
            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToJobSite.setText(getResources().getString(R.string.job_site));
            mTvToCustomerName.setText("Alliance Go Trucking");
        } else {
            mTvPickupLabel.setVisibility(View.GONE);
            mTvPickupTime.setVisibility(View.GONE);
            mTvFromJobSite.setText(getResources().getString(R.string.job_site1));
            mTvFromCustomerName.setVisibility(View.VISIBLE);
            mTvFromCustomerName.setText("Alliance Go Trucking");
            mTvToJobSite.setText(getResources().getString(R.string.job_site2));
            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Alliance Go Trucking");
        }

        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_on))) {
            mLltStepsLayout.setVisibility(View.VISIBLE);
            mLltDropOffStepsLayout.setVisibility(View.GONE);
        } else {
            mLltStepsLayout.setVisibility(View.GONE);
            mLltDropOffStepsLayout.setVisibility(View.VISIBLE);
        }
        mTvStartTime.setText("");
        mRltMapView.setVisibility(View.GONE);

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

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mBtnDone.setOnClickListener(this);
        mBtnJobDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mRltRejectLoad.setOnClickListener(this);
        mRltAcceptLoad.setOnClickListener(this);
        mRltScanQrCode.setOnClickListener(this);
        mImCustomerSignature.setOnClickListener(this);
        mRatingBar.setOnRatingBarChangeListener(this);
        mChkNoCustomer.setOnCheckedChangeListener(this);
        mLltJobDetails.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
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

            case R.id.done_button:
                if (!mChkNoCustomer.isChecked()) {
                    if (!TextUtils.isEmpty(mStrRejectReason)) {
                        Utilities.getInstance().setPracticeRejectReason(mStrRejectReason);
                    }
                    if (TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason()) && TextUtils.isEmpty(mStrQrCodeScannedResult)) {
                        if (TextUtils.isEmpty(mEtSignerName.getText().toString().trim())) {
                            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_signer_name));
                            return;
                        }
                        if (TextUtils.isEmpty(Utilities.getInstance().getPracticeHaulOnCustomerSignature())) {
                            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.enter_customer_sign));
                            return;
                        }
                    }
                    Utilities.getInstance().setPracticeHaulOnSignatory(mEtSignerName.getText().toString().trim());
                    Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(true);
                } else {
                    Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(false);
                }
                if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                    if (TextUtils.isEmpty(mEtSignerName.getText().toString().trim())) {
                        ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_signer_name));
                        return;
                    }
                }
                if (!Utilities.getInstance().locationEnabled(getActivity())) {
                    Utilities.getInstance().enableGps(getActivity());
                    return;
                }

                goBack();
                break;

            case R.id.reject_load:
                RejectDialog dialogueUtils = new RejectDialog() {
                    @Override
                    public void receiveData(String result) {
                        Log.e("reason", ": " + result);

                        mStrRejectReason = result;
                        if (TextUtils.isEmpty(result)) {
                            mRltRejectLoad.setVisibility(View.VISIBLE);
                            mRltAcceptLoad.setVisibility(View.GONE);
                            mLltScanQrCodeLayout.setVisibility(View.VISIBLE);
                            mLltRatingBarLayout.setVisibility(View.VISIBLE);
                            mLltScanQrCodeLayout.setVisibility(View.VISIBLE);
                            mRltSignatureLayout.setVisibility(View.VISIBLE);
                            mRltNoCustomer.setVisibility(View.VISIBLE);
                        } else {
                            mRltRejectLoad.setVisibility(View.GONE);
                            mRltAcceptLoad.setVisibility(View.VISIBLE);
                            mLltScanQrCodeLayout.setVisibility(View.GONE);
                            mLltRatingBarLayout.setVisibility(View.GONE);
                            mLltScanQrCodeLayout.setVisibility(View.GONE);
                            mRltSignatureLayout.setVisibility(View.GONE);
                            mRltNoCustomer.setVisibility(View.GONE);
                        }

                    }
                };
                dialogueUtils.openRejectReasonDialog(getActivity());
                break;

            case R.id.accept_layout:
                ShowDialogUtils.getInstance().askDialog(getActivity(), getResources().getString(R.string.confirm_accept)
                        , new DialogCallBackListener() {
                            @Override
                            public void callBack() {
                                mRltRejectLoad.setVisibility(View.VISIBLE);
                                mRltAcceptLoad.setVisibility(View.GONE);
                                mLltScanQrCodeLayout.setVisibility(View.VISIBLE);
                                Utilities.getInstance().setPracticeRejectReason("");
                                mRltSignatureLayout.setVisibility(View.VISIBLE);
                                mRltNoCustomer.setVisibility(View.VISIBLE);
                            }
                        });

                break;

            case R.id.scan_qr_code:
                new IntentIntegrator(getActivity()).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES).setOrientationLocked(true).setCaptureActivity(QRCodeActivity.class).initiateScan();
                break;

            case R.id.signature_image:
                Constants.IMAGE_NAME = getResources().getString(R.string.haul_on) + getResources().getString(R.string.customer_signature) + "1234";

                if (!Utilities.getInstance().checkPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    return;

                }
                Utilities.getInstance().setIS_PROFILE(2);
                Intent intent = new Intent(getActivity(), SignatureActivity.class);
                getActivity().startActivity(intent);
                break;

        }
    }


    /**
     * @description This method is called when this step is completed
     */
    private void goBack() {
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_on))) {
            Utilities.getInstance().setPracticeJobStep(3);
        } else {
            Utilities.getInstance().setPracticeJobStep(4);
        }
        getActivity().getSupportFragmentManager().popBackStack();
    }


    @Override
    public void setProofImage(String Path) {

    }

    @Override
    public void setProfileImage(String Path) {

    }

    @Override
    public void setJobSignature(String Path) {
        mTvSignatureDummyText.setVisibility(View.GONE);
        Utilities.getInstance().setImage(mImCustomerSignature, Path, false);
        Utilities.getInstance().setPracticeHaulOnCustomerSignature(Path);
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
        mRltRejectLoad.setVisibility(visibility);
    }

    @Override
    public void receiveData(String result) {
        Log.e("QR_CODE", ":" + result);
        if (Utilities.getInstance().isQRValid(result)) {
            mLltSignerLayout.setVisibility(View.GONE);
            mRltSignatureLayout.setVisibility(View.GONE);
            mRltNoCustomer.setVisibility(View.GONE);
            mStrQrCodeScannedResult = result;
        } else {
            ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources()
                    .getString(R.string.warning), getResources().getString(R.string.invalid_qr_code));
        }
    }
}
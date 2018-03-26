package com.demoapp.practice_module.practice_on_site_steps;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.demoapp.abstract_classes.CustomTimePicker;
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

import java.util.Date;

/**
 * Created by android5 on 22/7/16.
 */
public class PracticeOnSiteLeavingJobFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, RatingBar.OnRatingBarChangeListener, SetImageListener, StringCallbackListner, TextWatcher {

    private TextView mTvMaterialType;
    private TextView mTvOnSiteAddress;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvDummyText;
    private TextView mTvFromMapLabel;
    private TextView mTvFromMapAddress;
    private TextView mTvToMapLabel;
    private TextView mTvToMapAddress;
    private TextView mTvTotalTime;
    private TextView mTvOnSiteLabel;
    private TextView mTvCustomerName;
    private TextView mTvJobSite;

    private EditText mEtBreakTime;
    private EditText mEtSignerName;

    private CheckBox mChkNoCustomer;

    private LinearLayout mLltSignerLayout;
    private LinearLayout mLltDetailsLayout;
    private LinearLayout mLltBreakLayout;
    private LinearLayout mLltRatingBarLayout;
    private LinearLayout mLltScanQrCodeMainLayout;

    private RelativeLayout mRltSignatureLayout;
    private RelativeLayout mRltScanQrCodeLayout;
    private RelativeLayout mRltRejectLoad;
    private RelativeLayout mRltAcceptLoad;
    private RelativeLayout mRltNoCustomer;
    private RelativeLayout mRltMapLayout;

    private ImageView mImCustomerSignature;
    private ImageView mImSourceMapIcon;
    private ImageView mImDottedMapLine;

    private RatingBar mRatingBar;

    private Button mBtnDone;
    private Button mBtnDetails;
    private Button mBtnMapView;

    private boolean isBreakTimeWrong;

    private View mView;// using fot hiding the keyboard

    private String mStrQrCodeScannedResult;
    private String mDate[];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return init(inflater, container);
    }

    /**
     * @description This method is used to hide the views from the map layout
     */

    private void hideViews() {
        mRltMapLayout.setVisibility(View.GONE);
        mImDottedMapLine.setVisibility(View.GONE);
        mImSourceMapIcon.setVisibility(View.GONE);
        mTvFromMapLabel.setVisibility(View.GONE);
        mTvFromMapAddress.setVisibility(View.GONE);
        mTvToMapLabel.setText(getResources().getString(R.string.on_site_));
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_on_site_leaving_job, container, false);

        mTvMaterialType = (TextView) view.findViewById(R.id.material_type);
        mTvOnSiteAddress = (TextView) view.findViewById(R.id.on_site_address);
        mTvStartTime = (TextView) view.findViewById(R.id.start_time);
        mTvEndTime = (TextView) view.findViewById(R.id.end_time);
        mTvFromMapLabel = (TextView) view.findViewById(R.id.from_map_label);
        mTvFromMapAddress = (TextView) view.findViewById(R.id.from_map_address);
        mTvToMapAddress = (TextView) view.findViewById(R.id.to_map_address);
        mTvDummyText = (TextView) view.findViewById(R.id.dummytext);
        mTvToMapLabel = (TextView) view.findViewById(R.id.to_map_label);
        mTvTotalTime = (TextView) view.findViewById(R.id.total_time);
        mTvOnSiteLabel = (TextView) view.findViewById(R.id.on_site);
        mTvCustomerName = (TextView) view.findViewById(R.id.on_site_customer_name);
        mTvJobSite = (TextView) view.findViewById(R.id.job_site);

        mEtBreakTime = (EditText) view.findViewById(R.id.break_time);
        mEtSignerName = (EditText) view.findViewById(R.id.first_signer_name);

        mChkNoCustomer = (CheckBox) view.findViewById(R.id.no_customer);

        mLltSignerLayout = (LinearLayout) view.findViewById(R.id.signer_layout);
        mRltMapLayout = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltDetailsLayout = (LinearLayout) view.findViewById(R.id.details_layout);
        mLltBreakLayout = (LinearLayout) view.findViewById(R.id.break_layout);
        mLltRatingBarLayout = (LinearLayout) view.findViewById(R.id.ratingbar_layout);

        mRltSignatureLayout = (RelativeLayout) view.findViewById(R.id.signature_layout);
        mRltScanQrCodeLayout = (RelativeLayout) view.findViewById(R.id.scan_qr_code);
        mLltScanQrCodeMainLayout = (LinearLayout) view.findViewById(R.id.qr_code_layout);
        mRltRejectLoad = (RelativeLayout) view.findViewById(R.id.reject_load);
        mRltAcceptLoad = (RelativeLayout) view.findViewById(R.id.accept_layout);
        mRltNoCustomer = (RelativeLayout) view.findViewById(R.id.no_customer_layout);

        mImCustomerSignature = (ImageView) view.findViewById(R.id.signature_image);
        mImSourceMapIcon = (ImageView) view.findViewById(R.id.source_map_icon);
        mImDottedMapLine = (ImageView) view.findViewById(R.id.dotted_map_line);

        mRatingBar = (RatingBar) view.findViewById(R.id.ratingbar);

        mBtnDone = (Button) view.findViewById(R.id.done_button);
        mBtnDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mView = view;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utilities.getInstance().setSTEPS(3);
        setOnClickListener();
        SignatureActivity signatureActivity = new SignatureActivity();
        signatureActivity.callBack(this);
        QRCodeActivity qrCodeActivity = new QRCodeActivity();
        qrCodeActivity.initializeListener(this);
        hideViews();
        getData();
        Date date = new Date();
        mDate = Utilities.getInstance().getFormatter1().format(date).split(" ");
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mBtnDone.setOnClickListener(this);
        mRltScanQrCodeLayout.setOnClickListener(this);
        mImCustomerSignature.setOnClickListener(this);
        mChkNoCustomer.setOnCheckedChangeListener(this);
        mRatingBar.setOnRatingBarChangeListener(this);
        mBtnDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mLltDetailsLayout.setOnClickListener(this);
        mRltRejectLoad.setOnClickListener(this);
        mRltAcceptLoad.setOnClickListener(this);
        mLltDetailsLayout.setOnClickListener(this);
        mTvStartTime.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
        mEtBreakTime.addTextChangedListener(this);
    }

    /**
     * @description This method is used to get data from the shared preferences
     */
    private void getData() {

        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.on_site))) {
            mLltBreakLayout.setVisibility(View.VISIBLE);
        } else {
            mLltBreakLayout.setVisibility(View.GONE);
        }

        Date date = new Date();

        Log.e("end_time", ":" + Utilities.getInstance().getFormatter1().format(date));

        mTvMaterialType.setText(getResources().getString(R.string.default_material));

        mTvToMapLabel.setText(getResources().getString(R.string.on_site_address));
        if (!TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.on_site))) {
            mTvOnSiteLabel.setText(getResources().getString(R.string.waiting_address));
            mTvToMapLabel.setText(getResources().getString(R.string.waiting_address));
        }

        mTvJobSite.setText(getResources().getString(R.string.job_site));
        mTvToMapAddress.setText(getResources().getString(R.string.from_address));
        mTvOnSiteAddress.setText(getResources().getString(R.string.from_address));

        mTvCustomerName.setVisibility(View.VISIBLE);
        mTvCustomerName.setText("Alliance Go Trucking");


        mTvStartTime.setText(Utilities.getInstance().getHoursMinutes(Utilities.getInstance().getPracticeStartTime()));
        mTvEndTime.setText(Utilities.getInstance().getHoursMinutes(Utilities.getInstance().getFormatter1().format(date)));
        mTvTotalTime.setText(Utilities.getInstance().getHoursFromMinutes(Utilities.getInstance().getDifferenceInMins(Utilities.getInstance().getPracticeStartTime(), Utilities.getInstance().getFormatter1().format(date))));

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
                Utilities.getInstance().changeView(getActivity(), mLltDetailsLayout, mRltMapLayout, mBtnDetails, mBtnMapView, true);
                break;

            case R.id.map_button:
                Utilities.getInstance().changeView(getActivity(), mLltDetailsLayout, mRltMapLayout, mBtnDetails, mBtnMapView, false);
                break;

            case R.id.signature_image:
                Constants.IMAGE_NAME = getResources().getString(R.string.customer_signature) + "1234";

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

                if (mChkNoCustomer.isChecked()) {
                    Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(false);
                    goBack();
                    return;
                } else {
                    Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(true);
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
                        if (TextUtils.isEmpty(result)) {
                        } else {
                            Utilities.getInstance().setPracticeRejectReason(result);
                            mRltRejectLoad.setVisibility(View.GONE);
                            mRltAcceptLoad.setVisibility(View.VISIBLE);
                            mRltSignatureLayout.setVisibility(View.GONE);
                            mRltNoCustomer.setVisibility(View.GONE);
                            mLltRatingBarLayout.setVisibility(View.GONE);
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
                                Utilities.getInstance().setPracticeRejectReason("");
                                mRltRejectLoad.setVisibility(View.VISIBLE);
                                mRltAcceptLoad.setVisibility(View.GONE);
                                mRltSignatureLayout.setVisibility(View.VISIBLE);
                                mRltNoCustomer.setVisibility(View.VISIBLE);
                                mLltRatingBarLayout.setVisibility(View.VISIBLE);
                            }
                        });
                break;

            case R.id.start_time:
                setTime(true);

                break;
            case R.id.end_time:
                setTime(false);
                break;
        }
    }


    /**
     * @description This method is called when this step is completed
     */
    private void goBack() {
        Log.e("total_time", ":111: " + mTvTotalTime.getText().toString());
        Utilities.getInstance().setPracticeTotalTime(mTvTotalTime.getText().toString());

        Utilities.getInstance().setPracticeJobStep(2);
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int visibility = View.VISIBLE;

        if (isChecked) {
            visibility = View.GONE;
        }

        mLltRatingBarLayout.setVisibility(visibility);
        mRltSignatureLayout.setVisibility(visibility);
        mLltSignerLayout.setVisibility(visibility);
        mLltScanQrCodeMainLayout.setVisibility(visibility);
        mRltRejectLoad.setVisibility(visibility);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mRatingBar.setRating(ratingBar.getRating());
    }

    @Override
    public void setProofImage(String Path) {

    }

    @Override
    public void setProfileImage(String Path) {

    }

    @Override
    public void setJobSignature(String Path) {
        mTvDummyText.setVisibility(View.GONE);
        Utilities.getInstance().setImage(mImCustomerSignature, Path, false);
        Utilities.getInstance().setPracticeHaulOnCustomerSignature(Path);
    }

    /**
     * @description This method will return the time taken for job excluding break time
     */
    private void calculateTimeDiffrence(boolean calculate, View view) {
        String mins = Utilities.getInstance().getDifferenceInMins(mDate[0] + " " + mTvStartTime.getText().toString().trim() + ":00",
                mDate[0] + " " + mTvEndTime.getText().toString().trim() + ":00");
        if (!calculate) {
            int diffrence = Integer.parseInt(mins) - 0;
            isBreakTimeWrong = false;
            mTvTotalTime.setText(Utilities.getInstance().getHoursFromMinutes(String.valueOf(diffrence)));
        } else {
            int diffrence = Integer.parseInt(mins) - Integer.parseInt(mEtBreakTime.getText().toString().trim());
            Log.e("mins", ":" + mins);
            Log.e("difference", "1:" + diffrence);
            Log.e("difference", ":" + (Integer.parseInt(mins) - diffrence));
            if (diffrence >= 0) {
                isBreakTimeWrong = false;
                mTvTotalTime.setText(Utilities.getInstance().getHoursFromMinutes(String.valueOf(diffrence)));
            } else {
                isBreakTimeWrong = true;
                Utilities.getInstance().hideKeyboard(getActivity(), view);
                ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.breaks_time_less));
            }
        }
    }


    private void setTime(final boolean isStart) {
        CustomTimePicker customTimePicker = new CustomTimePicker() {
            @Override
            public void setTime(String hour, String minute) {
                Log.e("hours", ":: " + hour);
                Log.e("minutes", ":: " + minute);
                if (isStart) {
                    Log.e("start", ":: ");
                    Utilities.getInstance().changeTime(getActivity(), mDate[0] + " 00:00:00",
                            mDate[0] + " " + mTvEndTime.getText().toString() + ":00", hour, minute, mTvStartTime, mTvTotalTime, isStart);
                } else {
                    Utilities.getInstance().changeTime(getActivity(), mDate[0] + " " + mTvStartTime.getText().toString() + ":00",
                            mDate[0] + " " + mTvEndTime.getText().toString() + ":00", hour, minute, mTvEndTime, mTvTotalTime, isStart);
                }
                Log.e("total", ":: " + mDate[0] + " " + mTvStartTime.getText().toString() + ":00");
                Log.e("total", ":: " + mDate[0] + " " + mTvEndTime.getText().toString() + ":00");
                Log.e("total", ":: " + Utilities.getInstance().getDifferenceInMins(mDate[0] + " "
                        + mTvStartTime.getText().toString() + ":00", mDate[0] + " " + mTvEndTime.getText().toString() + ":00"));
                Log.e("total", ":: " + Utilities.getInstance().getHoursFromMinutes(Utilities.getInstance().getDifferenceInMins(mDate[0] + " "
                        + mTvStartTime.getText().toString() + ":00", mDate[0] + " " + mTvEndTime.getText().toString() + ":00")));
                String totalTime = Utilities.getInstance().getHoursFromMinutes(Utilities.getInstance().getDifferenceInMins(mDate[0] + " "
                        + mTvStartTime.getText().toString() + ":00", mDate[0] + " " + mTvEndTime.getText().toString() + ":00"));
                mTvTotalTime.setText(totalTime);
                if (!TextUtils.isEmpty(mEtBreakTime.getText().toString().trim())) {
                    calculateTimeDiffrence(true, mView);
                } else {
                    calculateTimeDiffrence(false, mView);
                }
            }
        };

        String s1[];
        if (isStart) {
            s1 = mTvStartTime.getText().toString().trim().split(":");
        } else {
            s1 = mTvEndTime.getText().toString().trim().split(":");
        }
        customTimePicker.showTimePickerDialog(getActivity(), Integer.parseInt(s1[0]), Integer.parseInt(s1[1]));
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(mEtBreakTime.getText().toString().trim())) {
            calculateTimeDiffrence(true, mView);
        } else {
            calculateTimeDiffrence(false, mView);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
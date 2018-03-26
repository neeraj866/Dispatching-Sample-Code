package com.demoapp.practice_module.practice_on_site_steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.interfaces.DialogCallBackListener;
import com.demoapp.map.MapFragment;
import com.demoapp.practice_module.PracticeOrdersFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;


/**
 * Created by android5 on 18/7/16.
 */
public class PracticeOnSiteCompleteJobFragment extends Fragment implements View.OnClickListener {

    private Button mBtnCompleteJob;
    private Button mBtnJobDetails;
    private Button mBtnMapView;

    private TextView mTvLoadNumber;
    private TextView mTvCustomerName;
    private TextView mTvJobName;
    private TextView mTvMaterialType;
    private TextView mTvTotalTime;
    private TextView mTvToAddress;
    private TextView mTvFromAddressMap;
    private TextView mTvToAddressMap;
    private TextView mTvSignerName;
    private TextView mTvFromMapLabel;
    private TextView mTvToMapLabel;
    private TextView mTvOnSiteLabel;
    private TextView mTvFromCustomerName;
    private TextView mTvRejectReason;

    private ImageView mImCustomerSignature;
    private ImageView mImDriverSignature;
    private ImageView mImDottedMapLine;
    private ImageView mImSourceMapIcon;

    private LinearLayout mLltDetailsLayout;
    private LinearLayout mLltCustomerSignatureLayout;
    private LinearLayout mLltRejectReasonLayout;

    private RelativeLayout mRltMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return init(inflater, container);
    }

    private void hideViews() {
        mRltMapView.setVisibility(View.GONE);
        mImDottedMapLine.setVisibility(View.GONE);
        mImSourceMapIcon.setVisibility(View.GONE);
        mTvFromMapLabel.setVisibility(View.GONE);
        mTvFromAddressMap.setVisibility(View.GONE);
        mTvToMapLabel.setText(getResources().getString(R.string.on_site_));
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_on_site_complete_job, container, false);
        mBtnCompleteJob = (Button) view.findViewById(R.id.complete_job);
        mBtnJobDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mTvLoadNumber = (TextView) view.findViewById(R.id.load_no);
        mTvCustomerName = (TextView) view.findViewById(R.id.customer_name);
        mTvJobName = (TextView) view.findViewById(R.id.job_name);
        mTvMaterialType = (TextView) view.findViewById(R.id.material_type);
        mTvTotalTime = (TextView) view.findViewById(R.id.total_time);
        mTvFromAddressMap = (TextView) view.findViewById(R.id.from_map_address);
        mTvToAddress = (TextView) view.findViewById(R.id.to_address);
        mTvToAddressMap = (TextView) view.findViewById(R.id.to_map_address);
        mTvSignerName = (TextView) view.findViewById(R.id.first_signer_name);
        mTvToMapLabel = (TextView) view.findViewById(R.id.to_map_label);
        mTvFromMapLabel = (TextView) view.findViewById(R.id.from_map_label);
        mTvOnSiteLabel = (TextView) view.findViewById(R.id.on_site);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.on_site_customer_name);
        mTvRejectReason = (TextView) view.findViewById(R.id.rejected_reason);

        mImCustomerSignature = (ImageView) view.findViewById(R.id.first_customer_signature);
        mImDriverSignature = (ImageView) view.findViewById(R.id.driver_signature);


        mLltDetailsLayout = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltCustomerSignatureLayout = (LinearLayout) view.findViewById(R.id.first_customer_signature_layout);
        mLltRejectReasonLayout = (LinearLayout) view.findViewById(R.id.reject_layout);

        mImSourceMapIcon = (ImageView) view.findViewById(R.id.source_map_icon);
        mImDottedMapLine = (ImageView) view.findViewById(R.id.dotted_map_line);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utilities.getInstance().setSTEPS(3);
        setOnClickListener();
        getData();
        hideViews();
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mBtnCompleteJob.setOnClickListener(this);
        mBtnJobDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
    }


    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {

        mTvMaterialType.setText(getResources().getString(R.string.default_material));
        mTvFromAddressMap.setText(getResources().getString(R.string.from_address));
        mTvToAddressMap.setText(getResources().getString(R.string.from_address));
        mTvToAddress.setText(getResources().getString(R.string.from_address));
        mTvLoadNumber.setText("4567");
        mTvCustomerName.setText("Alliance Go Trucking");
        mTvJobName.setText(Utilities.getInstance().getPracticeJobName());
        mTvTotalTime.setText(Utilities.getInstance().getPracticeTotalTime());
        mTvFromCustomerName.setVisibility(View.VISIBLE);
        mTvFromCustomerName.setText("Alliance Go Trucking");


        if (Utilities.getInstance().isPracticeHaulOnIsCustomerPresent() && TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            mLltCustomerSignatureLayout.setVisibility(View.VISIBLE);
            Utilities.getInstance().setImage(mImCustomerSignature, Utilities.getInstance().getPracticeHaulOnCustomerSignature(), false);
            mTvSignerName.setText(Utilities.getInstance().getPracticeHaulOnSignatory());
        } else {
            mLltCustomerSignatureLayout.setVisibility(View.GONE);
        }

        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.on_site))) {

        } else if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.wait))) {
//                mTvQuantityDelivered.setVisibility(View.GONE);
//                mTvQuantityDeliveredLabel.setVisibility(View.GONE);
            mTvOnSiteLabel.setText(getResources().getString(R.string.waiting_address));
            mTvToMapLabel.setText(getResources().getString(R.string.waiting_address));
        }

        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            mLltRejectReasonLayout.setVisibility(View.VISIBLE);
            mTvRejectReason.setText(Utilities.getInstance().getPracticeRejectReason());
        }


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
        switch (v.getId()) {
            case R.id.details_button:
                Utilities.getInstance().changeView(getActivity(), mLltDetailsLayout, mRltMapView, mBtnJobDetails, mBtnMapView, true);
                break;

            case R.id.map_button:
                Utilities.getInstance().changeView(getActivity(), mLltDetailsLayout, mRltMapView, mBtnJobDetails, mBtnMapView, false);
                break;

            case R.id.complete_job:

                if (!Utilities.getInstance().locationEnabled(getActivity())) {
                    Utilities.getInstance().enableGps(getActivity());
                    return;
                }

                goBack();

                break;
        }
    }

    /**
     * @description This method is called when this step is completed
     */
    private void goBack() {
        ShowDialogUtils.getInstance().showAlertDialogCallback(getActivity(), getResources().getString(R.string.success)
                , getResources().getString(R.string.job_completed), new DialogCallBackListener() {
                    @Override
                    public void callBack() {
                        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new PracticeOrdersFragment())
                                .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                                .commitAllowingStateLoss();
                    }
                }, false);
    }
}
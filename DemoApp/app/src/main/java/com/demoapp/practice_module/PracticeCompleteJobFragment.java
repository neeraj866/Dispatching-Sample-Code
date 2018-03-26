package com.demoapp.practice_module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;

import java.util.Date;

/**
 * Created by android5 on 18/7/16.
 */
public class PracticeCompleteJobFragment extends Fragment implements View.OnClickListener {

    private Button mBtnCompleteJob;
    private Button mBtnJobDetails;
    private Button mBtnMapView;

    private TextView mTvLoadNumber;
    private TextView mTvCustomerName;
    private TextView mTvCustomerNamelabel;
    private TextView mTvCustomerName2;
    private TextView mTvJobName;
    private TextView mTvMaterialType;
    private TextView mTvTotalTime;
    private TextView mTvQuantityDelivered;
    private TextView mTvTicketNumber;
    private TextView mTvViewTicketImage;
    private TextView mTvFromAddress;
    private TextView mTvPickUpTime;
    private TextView mTvToAddress;
    private TextView mTvDropOffTime;
    private TextView mTvFromAddressMap;
    private TextView mTvToAddressMap;
    private TextView mTvTicketNumberLabel;
    private TextView mTvFirstSignerName;
    private TextView mTvSecondSignerName;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvLoadingJobLabel;
    private TextView mTvDumpingJobLabel;
    private TextView mTvDDJobName;
    private TextView mTvFromCustomerName;
    private TextView mTvFromJobSite;
    private TextView mTvToCustomerName;
    private TextView mTvToJobSite;
    private TextView mTvRejectReason;

    private ImageView mImFirstCustomerSignature;
    private ImageView mImSecondCustomerSignature;
    private ImageView mImDriverSignature;

    private LinearLayout mLltCustomerLayout;
    private LinearLayout mLltDetailsLayout;
    private LinearLayout mLltFirstCustomerSignatureLayout;
    private LinearLayout mLltSecondCustomerSignatureLayout;
    private LinearLayout mLltRejectReasonLayout;
    private LinearLayout mLltStepsLayout;
    private LinearLayout mLltDropOffStepsLayout;
    private LinearLayout mLltTicketLayout;
    private LinearLayout mLltBreakLayout;
    private LinearLayout mLltStartLayout;
    private LinearLayout mLltEndLayout;
    private LinearLayout mLltTotalTimeLayout;

    private RelativeLayout mRltMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return init(inflater, container);
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_complete_job, container, false);
        mBtnCompleteJob = (Button) view.findViewById(R.id.complete_job);
        mBtnJobDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mTvLoadNumber = (TextView) view.findViewById(R.id.load_no);
        mTvCustomerName = (TextView) view.findViewById(R.id.customer_name);
        mTvCustomerNamelabel = (TextView) view.findViewById(R.id.customer_label1);
        mTvCustomerName2 = (TextView) view.findViewById(R.id.customer_name2);
        mTvJobName = (TextView) view.findViewById(R.id.job_name);
        mTvMaterialType = (TextView) view.findViewById(R.id.material_type);
        mTvQuantityDelivered = (TextView) view.findViewById(R.id.qty_delivered);
        mTvTicketNumber = (TextView) view.findViewById(R.id.ticket_number);
        mTvFirstSignerName = (TextView) view.findViewById(R.id.first_signer_name);
        mTvSecondSignerName = (TextView) view.findViewById(R.id.second_signer_name);
        mTvTicketNumberLabel = (TextView) view.findViewById(R.id.pit_ticket_number_label);
        mTvViewTicketImage = (TextView) view.findViewById(R.id.view_image);
        mTvFromAddress = (TextView) view.findViewById(R.id.from_address);
        mTvFromAddressMap = (TextView) view.findViewById(R.id.from_map_address);
        mTvPickUpTime = (TextView) view.findViewById(R.id.pick_up_time);
        mTvToAddress = (TextView) view.findViewById(R.id.to_address);
        mTvToAddressMap = (TextView) view.findViewById(R.id.to_map_address);
        mTvDropOffTime = (TextView) view.findViewById(R.id.drop_off_time);
        mTvStartTime = (TextView) view.findViewById(R.id.start_time);
        mTvTotalTime = (TextView) view.findViewById(R.id.total_time);
        mTvEndTime = (TextView) view.findViewById(R.id.end_time);
        mTvLoadingJobLabel = (TextView) view.findViewById(R.id.job_label1);
        mTvDumpingJobLabel = (TextView) view.findViewById(R.id.job_label2);
        mTvDDJobName = (TextView) view.findViewById(R.id.dd_job_name);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.from_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.from_job_site);
        mTvToCustomerName = (TextView) view.findViewById(R.id.to_customer_name);
        mTvToJobSite = (TextView) view.findViewById(R.id.to_job_site);
        mTvRejectReason = (TextView) view.findViewById(R.id.rejected_reason);

        mImFirstCustomerSignature = (ImageView) view.findViewById(R.id.first_customer_signature);
        mImSecondCustomerSignature = (ImageView) view.findViewById(R.id.second_customer_signature);
        mImDriverSignature = (ImageView) view.findViewById(R.id.driver_signature);

        mLltDetailsLayout = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltFirstCustomerSignatureLayout = (LinearLayout) view.findViewById(R.id.first_customer_signature_layout);
        mLltSecondCustomerSignatureLayout = (LinearLayout) view.findViewById(R.id.second_customer_signature_layout);
        mLltRejectReasonLayout = (LinearLayout) view.findViewById(R.id.reject_layout);
        mLltStepsLayout = (LinearLayout) view.findViewById(R.id.steps_layout);
        mLltDropOffStepsLayout = (LinearLayout) view.findViewById(R.id.drop_off_steps_layout);
        mLltTicketLayout = (LinearLayout) view.findViewById(R.id.ticket_layout);
        mLltCustomerLayout = (LinearLayout) view.findViewById(R.id.customer_layout2);
        mLltBreakLayout = (LinearLayout) view.findViewById(R.id.break_layout);
        mLltStartLayout = (LinearLayout) view.findViewById(R.id.start_time_layout);
        mLltEndLayout = (LinearLayout) view.findViewById(R.id.end_time_layout);
        mLltTotalTimeLayout = (LinearLayout) view.findViewById(R.id.total_time_layout);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utilities.getInstance().setSTEPS(3);
        mRltMapView.setVisibility(View.GONE);
        setOnClickListener();
        getData();
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mBtnCompleteJob.setOnClickListener(this);
        mBtnJobDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mTvViewTicketImage.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
    }


    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {
        mTvFromAddressMap.setText("");
        mTvToAddressMap.setText("");
        mTvLoadNumber.setText("4567");
        mTvEndTime.setText("");
        mTvJobName.setText(Utilities.getInstance().getPracticeJobName());
        mTvFromAddressMap.setText(getResources().getString(R.string.from_address));
        mTvToAddressMap.setText(getResources().getString(R.string.to_address));
        mTvPickUpTime.setText("");
        mTvDropOffTime.setText("");

        Date date = new Date();
        String s1[] = Utilities.getInstance().getFormatter1().format(date).split(" ");
        String s2[] = s1[1].split(":");
        String endTime = s1[0] + " " + s2[0] + ":" + s2[1] + ":00";

        mTvTotalTime.setText(Utilities.getInstance().getHoursFromMinutes(Utilities.getInstance().getDifferenceInMins(Utilities.getInstance().getPracticeStartTime(), endTime)));
        mLltTotalTimeLayout.setVisibility(View.VISIBLE);
        mLltEndLayout.setVisibility(View.GONE);
        mLltStartLayout.setVisibility(View.GONE);
        mLltBreakLayout.setVisibility(View.GONE);
        mTvStartTime.setText("");

        mLltFirstCustomerSignatureLayout.setVisibility(View.VISIBLE);
        mLltRejectReasonLayout.setVisibility(View.GONE);

        mLltStepsLayout.setVisibility(View.VISIBLE);
        mLltDropOffStepsLayout.setVisibility(View.GONE);

        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.START_LATITUDE, getResources().getString(R.string.from_lati));
        bundle.putString(Constants.START_LONGITUDE, getResources().getString(R.string.from_longi));
        bundle.putString(Constants.END_LATITUDE, getResources().getString(R.string.to_lati));
        bundle.putString(Constants.END_LONGITUDE, getResources().getString(R.string.to_longi));
        mapFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.map_frame, mapFragment).commitAllowingStateLoss();


        mTvMaterialType.setText(getResources().getString(R.string.default_material));
        mTvTicketNumber.setText("7846");
        mTvQuantityDelivered.setText(getResources().getString(R.string.one_load) + " " + getResources().getString(R.string.load));
        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            mLltRejectReasonLayout.setVisibility(View.VISIBLE);
            mTvRejectReason.setText(Utilities.getInstance().getPracticeRejectReason());
        }
        Log.e("typeeee", "::: " + Utilities.getInstance().getPracticeJobType());
        if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_on))) {

            Log.e("complete", ": 1");
            mTvCustomerName.setText("Alliance Go Trucking");
            mTvFromJobSite.setText(getResources().getString(R.string.pit_address));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));
            mTvFromCustomerName.setVisibility(View.VISIBLE);
            mTvFromCustomerName.setText("John");
            mTvToJobSite.setText(getResources().getString(R.string.job_site));
            mTvToAddress.setText(getResources().getString(R.string.to_address));
            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Alliance Go Trucking");
            mTvTicketNumberLabel.setText(getResources().getString(R.string.pit_ticket_no));

            Log.e("present", ":: " + Utilities.getInstance().isPracticeHaulOnIsCustomerPresent());
            Log.e("signature", ":: " + Utilities.getInstance().getPracticeHaulOnCustomerSignature());

            if (Utilities.getInstance().isPracticeHaulOnIsCustomerPresent() && TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                mLltFirstCustomerSignatureLayout.setVisibility(View.VISIBLE);
                Utilities.getInstance().setImage(mImFirstCustomerSignature, Utilities.getInstance().getPracticeHaulOnCustomerSignature(), false);
                mTvFirstSignerName.setText(Utilities.getInstance().getPracticeHaulOnSignatory());
            } else {
                mLltFirstCustomerSignatureLayout.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                mTvViewTicketImage.setVisibility(View.VISIBLE);
                mTvTicketNumber.setVisibility(View.VISIBLE);
                mTvTicketNumberLabel.setVisibility(View.VISIBLE);
            } else {
                mTvViewTicketImage.setVisibility(View.GONE);
                mTvTicketNumber.setVisibility(View.GONE);
                mTvTicketNumberLabel.setVisibility(View.GONE);
            }

        } else if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.haul_off))) {


            mTvFromJobSite.setText(getResources().getString(R.string.job_site));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));

            mTvFromCustomerName.setVisibility(View.VISIBLE);
            mTvFromCustomerName.setText("Alliance Go Trucking");

            mTvToJobSite.setText(getResources().getString(R.string.drop_address));
            mTvToAddress.setText(getResources().getString(R.string.to_address));
            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Andrew");

            mTvCustomerName.setText("Alliance Go Trucking");

            mTvTicketNumberLabel.setText(getResources().getString(R.string.dump_ticket_no));

            if (Utilities.getInstance().isPracticeHaulOffIsCustomerPresent()) {
                mLltFirstCustomerSignatureLayout.setVisibility(View.VISIBLE);
                Utilities.getInstance().setImage(mImFirstCustomerSignature, Utilities.getInstance().getPracticeHaulOffCustomerSignature(), false);
                mTvFirstSignerName.setText(Utilities.getInstance().getPracticeHaulOffSignatory());
            } else {
                mLltFirstCustomerSignatureLayout.setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                mTvViewTicketImage.setVisibility(View.VISIBLE);
                mTvTicketNumber.setVisibility(View.VISIBLE);
                mTvTicketNumberLabel.setVisibility(View.VISIBLE);
            } else {
                mTvViewTicketImage.setVisibility(View.GONE);
                mTvTicketNumber.setVisibility(View.GONE);
                mTvTicketNumberLabel.setVisibility(View.GONE);
            }


        } else if (TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.dirt_deal))) {
            mTvDDJobName.setVisibility(View.VISIBLE);
            mTvDumpingJobLabel.setVisibility(View.VISIBLE);
            mTvLoadingJobLabel.setText(getResources().getString(R.string.loading_job));
            mTvDumpingJobLabel.setText(getResources().getString(R.string.dumping_job));
            mTvCustomerNamelabel.setText(getResources().getString(R.string.customer_name1));
            mLltCustomerLayout.setVisibility(View.VISIBLE);
            mTvJobName.setText(Utilities.getInstance().getPracticeJobName());
            mTvDDJobName.setText(Utilities.getInstance().getPracticeJobName());
            mLltTicketLayout.setVisibility(View.GONE);
            mLltStepsLayout.setVisibility(View.GONE);
            mLltDropOffStepsLayout.setVisibility(View.VISIBLE);

            mTvFromJobSite.setText(getResources().getString(R.string.job_site1));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));

            mTvFromCustomerName.setVisibility(View.VISIBLE);
            mTvFromCustomerName.setText("Alliance Go Trucking");

            mTvToJobSite.setText(getResources().getString(R.string.job_site2));
            mTvToAddress.setText(getResources().getString(R.string.to_address));

            mTvToCustomerName.setVisibility(View.VISIBLE);
            mTvToCustomerName.setText("Alliance Go Trucking");
            mTvCustomerName.setText("Alliance Go Trucking");
            mTvCustomerName2.setText("Alliance Go Trucking");

            if (Utilities.getInstance().isPracticeHaulOffIsCustomerPresent()) {
                mLltFirstCustomerSignatureLayout.setVisibility(View.VISIBLE);
                Utilities.getInstance().setImage(mImFirstCustomerSignature, Utilities.getInstance().getPracticeHaulOffCustomerSignature(), false);
                mTvFirstSignerName.setText(Utilities.getInstance().getPracticeHaulOffSignatory());
            } else {
                mLltFirstCustomerSignatureLayout.setVisibility(View.GONE);
            }

            if (Utilities.getInstance().isPracticeHaulOnIsCustomerPresent() && TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                mLltSecondCustomerSignatureLayout.setVisibility(View.VISIBLE);
                Utilities.getInstance().setImage(mImSecondCustomerSignature, Utilities.getInstance().getPracticeHaulOnCustomerSignature(), false);
                mTvSecondSignerName.setText(Utilities.getInstance().getPracticeHaulOnSignatory());
            } else {
                mLltSecondCustomerSignatureLayout.setVisibility(View.GONE);
            }


        }

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

                goBack();

                break;

            case R.id.view_image:
                if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeTicketImage())) {
                    ShowDialogUtils.getInstance().showImageDialog(getActivity(), Utilities.getInstance().getPracticeTicketImage());
                }
                break;

            case R.id.end_time:

                break;
        }
    }

    /**
     * @description This method is called when this step is completed
     */
    private void goBack() {
        Utilities.getInstance().setPracticeJobStep(0);
        Utilities.getInstance().setPracticeJobName("");
        Utilities.getInstance().setPracticeJobType("");
        Utilities.getInstance().setPracticeHaulOffCustomerSignature("");
        Utilities.getInstance().setPracticeHaulOnCustomerSignature("");
        Utilities.getInstance().setPracticeRejectReason("");
        Utilities.getInstance().setPracticeHaulOffIsCustomerPresent(false);
        Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(false);
        Utilities.getInstance().setPracticeHaulOffSignatory("");
        Utilities.getInstance().setPracticeHaulOnSignatory("");
        ShowDialogUtils.getInstance().showAlertDialogCallback(getActivity(), getResources().getString(R.string.success), getResources().getString(R.string.job_completed), new DialogCallBackListener() {
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
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
import com.demoapp.practice_module.practice_dirt_deal_steps.PracticeDirtDealJobStepsFragment;
import com.demoapp.practice_module.practice_haul_off_steps.PracticeHaulOffJobStepsFragment;
import com.demoapp.practice_module.practice_haul_on_steps.PracticeHaulOnJobStepsFragment;
import com.demoapp.practice_module.practice_on_site_steps.PracticeOnSiteJobStepsFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;

import java.util.Date;

/**
 * Created by android5 on 12/7/16.
 */
public class PracticeJobDetailsFragment extends Fragment implements View.OnClickListener {

    private Button mBtnDetails;
    private Button mBtnMap;
    private Button mBtnStartJob;

    private TextView mTvJobName;
    private TextView mTvMaterialLabel;
    private TextView mTvMaterial;
    private TextView mTvQtyRemaining;
    private TextView mTvQtyRemainingLabel;
    private TextView mTvAlliancePo;
    private TextView mTvAlliancePoLabel;
    private TextView mTvPay;
    private TextView mTvBillMaterialTo;
    private TextView mTvBillMaterialToLabel;
    private TextView mTvCustomerName;
    private TextView mTvCustomerName2;
    private TextView mTvContactPerson;
    private TextView mTvContactPerson2;
    private TextView mTvContactPhone;
    private TextView mTvContactPhone2;
    private TextView mTvFromLabel;
    private TextView mTvFromMapLabel;
    private TextView mTvToLabel;
    private TextView mTvToMapLabel;
    private TextView mTvFromAddress;
    private TextView mTvFromMapAddress;
    private TextView mTvToAddress;
    private TextView mTvToMapAddress;
    private TextView mTvBackHaulPayLabel;
    private TextView mTvBackHaulPay;
    private TextView mTvCustomerLabel;
    private TextView mTvJobLabel;
    private TextView mTvLoadingJob;
    private TextView mTvDDAlliancePOLable;
    private TextView mTvDDAlliancePO;
    private TextView mTvDDDumpingAlliancePO;
    private TextView mTvDumpingJobName;
    private TextView mTvFromCustomerName;
    private TextView mTvFromJobSite;
    private TextView mTvToCustomerName;
    private TextView mTvToJobSite;
    private TextView mTvPartiallyCompleted;

    private ImageView mImSourceIcon;
    private ImageView mImSourceMapIcon;
    private ImageView mImDottedLine;
    private ImageView mImDottedMapLine;

    private LinearLayout mLltInstructionsLayout;
    private LinearLayout mLltJobDetails;
    private LinearLayout mLltCustomerLayout;

    private RelativeLayout mRltMapLayout;

    private String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    /*
     *@description This method is used to find views from layout
     * */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_job_details, container, false);

        mBtnDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMap = (Button) view.findViewById(R.id.map_button);

        mTvJobName = (TextView) view.findViewById(R.id.job_name);
        mTvMaterialLabel = (TextView) view.findViewById(R.id.material_label);
        mTvMaterial = (TextView) view.findViewById(R.id.material_type);
        mTvAlliancePo = (TextView) view.findViewById(R.id.alliance_po);
        mTvAlliancePoLabel = (TextView) view.findViewById(R.id.alliance_po_label);
        mTvPay = (TextView) view.findViewById(R.id.pay);
        mTvBillMaterialToLabel = (TextView) view.findViewById(R.id.bill_material_to_label);
        mTvBillMaterialTo = (TextView) view.findViewById(R.id.bill_material_to);
        mTvCustomerName = (TextView) view.findViewById(R.id.customer_name);
        mTvCustomerName2 = (TextView) view.findViewById(R.id.customer_name2);
        mTvCustomerLabel = (TextView) view.findViewById(R.id.customer_label1);
        mTvContactPerson = (TextView) view.findViewById(R.id.contact_person);
        mTvContactPerson2 = (TextView) view.findViewById(R.id.contact_person2);
        mTvContactPhone = (TextView) view.findViewById(R.id.contact_phone);
        mTvContactPhone2 = (TextView) view.findViewById(R.id.contact_phone2);
        mTvFromAddress = (TextView) view.findViewById(R.id.from_address);
        mTvFromMapAddress = (TextView) view.findViewById(R.id.from_map_address);
        mTvFromLabel = (TextView) view.findViewById(R.id.from);
        mTvFromMapLabel = (TextView) view.findViewById(R.id.from_map_label);
        mTvToLabel = (TextView) view.findViewById(R.id.to);
        mTvToMapLabel = (TextView) view.findViewById(R.id.to_map_label);
        mTvToAddress = (TextView) view.findViewById(R.id.to_address);
        mTvToMapAddress = (TextView) view.findViewById(R.id.to_map_address);
        mTvBackHaulPayLabel = (TextView) view.findViewById(R.id.backhaul_pay_label);
        mTvBackHaulPay = (TextView) view.findViewById(R.id.backhaul_pay);
        mTvQtyRemaining = (TextView) view.findViewById(R.id.qty_remaining);
        mTvQtyRemainingLabel = (TextView) view.findViewById(R.id.qty_remaining_label);
        mTvJobLabel = (TextView) view.findViewById(R.id.job_label);
        mTvLoadingJob = (TextView) view.findViewById(R.id.loading_job);
        mTvDDAlliancePOLable = (TextView) view.findViewById(R.id.dd_alliacne_po_lable);
        mTvDDAlliancePO = (TextView) view.findViewById(R.id.dd_alliacne_po);
        mTvDDDumpingAlliancePO = (TextView) view.findViewById(R.id.dd_dumping_alliacne_po);
        mTvDumpingJobName = (TextView) view.findViewById(R.id.dumping_job);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.from_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.from_job_site);
        mTvToCustomerName = (TextView) view.findViewById(R.id.to_customer_name);
        mTvToJobSite = (TextView) view.findViewById(R.id.to_job_site);
        mTvPartiallyCompleted = (TextView) view.findViewById(R.id.partially_completed);

        mLltInstructionsLayout = (LinearLayout) view.findViewById(R.id.instructions_layout);
        mRltMapLayout = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltJobDetails = (LinearLayout) view.findViewById(R.id.details_layout);
        mLltCustomerLayout = (LinearLayout) view.findViewById(R.id.customer_layout);

        mImSourceIcon = (ImageView) view.findViewById(R.id.source_icon);
        mImSourceMapIcon = (ImageView) view.findViewById(R.id.source_map_icon);
        mImDottedLine = (ImageView) view.findViewById(R.id.dotted_line);
        mImDottedMapLine = (ImageView) view.findViewById(R.id.dotted_map_line);

        mBtnStartJob = (Button) view.findViewById(R.id.start_job_button);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnclickListener();
        getData();
        setData();
        mRltMapLayout.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * @description This method is used to get data for job which is in progress
     */
    private void getData() {
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
     * @description This methosd is used to set click listener
     */
    private void setOnclickListener() {
        mBtnDetails.setOnClickListener(this);
        mBtnMap.setOnClickListener(this);
        mBtnStartJob.setOnClickListener(this);
        mTvContactPhone.setOnClickListener(this);
        mTvContactPhone2.setOnClickListener(this);
        mTvFromAddress.setOnClickListener(this);
        mTvToAddress.setOnClickListener(this);
        mTvPartiallyCompleted.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_button:
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapLayout, mBtnDetails, mBtnMap, true);
                break;

            case R.id.map_button:
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapLayout, mBtnDetails, mBtnMap, false);
                break;

            case R.id.start_job_button:
                if (!Utilities.getInstance().locationEnabled(getActivity())) {
                    Utilities.getInstance().enableGps(getActivity());
                    return;
                }
                goToStepFragment(type);
                break;

            case R.id.contact_phone:
                if (!TextUtils.isEmpty(mTvContactPhone.getText().toString().trim())) {
                    ShowDialogUtils.getInstance().showAlertDialogCallback(getActivity(), "", getResources().getString(R.string.ask_for_call), new DialogCallBackListener() {
                        @Override
                        public void callBack() {
                            Utilities.getInstance().makeCall(getActivity(), mTvContactPhone.getText().toString().trim());
                        }
                    }, true);
                }
                break;

            case R.id.contact_phone2:
                if (!TextUtils.isEmpty(mTvContactPhone2.getText().toString().trim())) {
                    ShowDialogUtils.getInstance().showAlertDialogCallback(getActivity(), "", getResources().getString(R.string.ask_for_call), new DialogCallBackListener() {
                        @Override
                        public void callBack() {
                            Utilities.getInstance().makeCall(getActivity(), mTvContactPhone2.getText().toString().trim());
                        }
                    }, true);
                }
                break;

        }
    }

    /**
     * @description This method is used to go to steps screen according to job type
     */
    private void goToStepFragment(String type) {
        Date date = new Date();
        String s1[] = Utilities.getInstance().getFormatter1().format(date).split(" ");
        String s2[] = s1[1].split(":");
        Utilities.getInstance().setPracticeStartTime(s1[0] + " " + s2[0] + ":" + s2[1] + ":00");
        Utilities.getInstance().setPracticeRejectReason("");
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);

        Utilities.getInstance().setApplyAnimation(false);
        Fragment fragment = null;
        if (TextUtils.equals(type, getResources().getString(R.string.haul_on))) {
            Log.e("here", "4:");
            fragment = new PracticeHaulOnJobStepsFragment();

        } else if (TextUtils.equals(type, getResources().getString(R.string.haul_off))) {
            Log.e("here", "5:");
            fragment = new PracticeHaulOffJobStepsFragment();
        } else if (TextUtils.equals(type, getResources().getString(R.string.on_site)) || TextUtils.equals(type, getResources().getString(R.string.wait))) {
            Log.e("here", "6:");
            fragment = new PracticeOnSiteJobStepsFragment();
        } else if (TextUtils.equals(type, getResources().getString(R.string.dirt_deal))) {
            Log.e("here", "7:");
            fragment = new PracticeDirtDealJobStepsFragment();
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                    .replace(R.id.main_frame, fragment).addToBackStack(getResources().getString(R.string.complete_job_stack)).commit();
        }
    }


    /**
     * @description This method is used to set Data on views
     **/
    private void setData() {


        mTvFromMapAddress.setText(getResources().getString(R.string.from_address));
        mTvToMapAddress.setText(getResources().getString(R.string.to_address));

        type = Utilities.getInstance().getPracticeJobType();
        mTvJobName.setText(Utilities.getInstance().getPracticeJobName());
        mTvMaterial.setText(getResources().getString(R.string.default_material));
        mTvAlliancePo.setText("1234");
        mTvBillMaterialTo.setText(getResources().getString(R.string.not_));
        mTvContactPerson.setText("John");
        mTvContactPhone.setText("(123) 123-1234");
        mTvCustomerName.setText("Go Trucking");
        mTvPay.setText("$50.00" + " / " + getResources().getString(R.string.load));
        mTvBackHaulPay.setVisibility(View.GONE);
        mTvBackHaulPayLabel.setVisibility(View.GONE);
        mLltInstructionsLayout.setVisibility(View.GONE);
        mTvQtyRemainingLabel.setVisibility(View.GONE);
        mTvQtyRemaining.setVisibility(View.GONE);
        if (TextUtils.equals(type, getResources().getString(R.string.haul_on))) {
            mTvMaterialLabel.setText(getResources().getString(R.string.material));
            mTvBillMaterialToLabel.setText(getResources().getString(R.string.bill_material_to));
            mTvJobLabel.setVisibility(View.GONE);
            mTvLoadingJob.setVisibility(View.GONE);
            mTvDDAlliancePOLable.setVisibility(View.GONE);
            mTvDDAlliancePO.setVisibility(View.GONE);


            mTvFromJobSite.setText(getResources().getString(R.string.pit_address));
            mTvFromAddress.setText(getResources().getString(R.string.from_address));
            mTvFromCustomerName.setText("John");


            mTvToJobSite.setText(getResources().getString(R.string.job_site));
            mTvToAddress.setText(getResources().getString(R.string.to_address));

            mTvToCustomerName.setText("Go Trucking");


        } else if (TextUtils.equals(type, getResources().getString(R.string.haul_off))
                || TextUtils.equals(type, getResources().getString(R.string.dirt_deal))) {

            mTvMaterialLabel.setText(getResources().getString(R.string.dump));
            mTvBillMaterialToLabel.setText(getResources().getString(R.string.bill_dump_fee_to));

            if (TextUtils.equals(type, getResources().getString(R.string.dirt_deal))) {
                mTvMaterialLabel.setText(getResources().getString(R.string.material));
                mTvLoadingJob.setText(Utilities.getInstance().getPracticeJobName());
                mTvDumpingJobName.setText(Utilities.getInstance().getPracticeJobName());
                mLltCustomerLayout.setVisibility(View.VISIBLE);
                mTvCustomerLabel.setVisibility(View.VISIBLE);
                mTvCustomerName2.setText("Go Trucking");
                mTvContactPerson2.setText("Go Trucking");
                mTvContactPhone2.setText("(123) 123-1234");

                mTvDDAlliancePO.setText("1234");
                mTvBillMaterialTo.setText(getResources().getString(R.string.not_));

                mTvDDDumpingAlliancePO.setText("1234");
                mTvBillMaterialTo.setText(getResources().getString(R.string.not_));

                mTvFromJobSite.setText(getResources().getString(R.string.job_site1));
                mTvFromAddress.setText(getResources().getString(R.string.from_address));
                mTvFromCustomerName.setText("Go Trucking");

                mTvToJobSite.setText(getResources().getString(R.string.job_site2));
                mTvToAddress.setText(getResources().getString(R.string.to_address));
                mTvToCustomerName.setText("Go Trucking");
                mTvBillMaterialToLabel.setVisibility(View.GONE);
                mTvBillMaterialTo.setVisibility(View.GONE);
                mTvAlliancePoLabel.setVisibility(View.GONE);
                mTvAlliancePo.setVisibility(View.GONE);

            } else {
                mTvJobLabel.setVisibility(View.GONE);
                mTvLoadingJob.setVisibility(View.GONE);
                mTvDDAlliancePOLable.setVisibility(View.GONE);
                mTvDDAlliancePO.setVisibility(View.GONE);

                mTvFromJobSite.setText(getResources().getString(R.string.job_site));
                mTvFromAddress.setText(getResources().getString(R.string.from_address));

                mTvFromCustomerName.setText("Go Trucking");

                mTvToJobSite.setText(getResources().getString(R.string.drop_address));
                mTvToAddress.setText(getResources().getString(R.string.to_address));

                mTvBackHaulPayLabel.setVisibility(View.VISIBLE);
                mTvBackHaulPay.setVisibility(View.VISIBLE);
                mTvBackHaulPay.setText("$35.00 / " + getResources().getString(R.string.load));
                mTvToCustomerName.setText("Andrew");
            }
        } else if (TextUtils.equals(type, getResources().getString(R.string.on_site))
                || TextUtils.equals(type, getResources().getString(R.string.wait))) {

            mTvMaterialLabel.setText(getResources().getString(R.string.material_type));
            mTvJobLabel.setVisibility(View.GONE);
            mTvLoadingJob.setVisibility(View.GONE);
            mTvDDAlliancePOLable.setVisibility(View.GONE);
            mTvDDAlliancePO.setVisibility(View.GONE);
            if (TextUtils.equals(type, getResources().getString(R.string.on_site))) {
                Log.e("here", ":1");
                mTvToLabel.setText(getResources().getString(R.string.on_site_address));
                mTvToMapLabel.setText(getResources().getString(R.string.on_site_address));
            } else {
                mTvToLabel.setText(getResources().getString(R.string.waiting_address));
                Log.e("here", ":2");
                mTvQtyRemaining.setVisibility(View.GONE);
                mTvQtyRemainingLabel.setVisibility(View.GONE);
                mTvToMapLabel.setText(getResources().getString(R.string.waiting_address));
            }


            mTvToCustomerName.setText("John");

            mTvToJobSite.setText(getResources().getString(R.string.job_site));
            mTvToAddress.setText(getResources().getString(R.string.from_address));
            mTvToMapAddress.setText(Utilities.getInstance().checkNull(""));
            mTvBillMaterialToLabel.setVisibility(View.GONE);
            mTvBillMaterialTo.setVisibility(View.GONE);


            mImSourceIcon.setVisibility(View.GONE);
            mImSourceMapIcon.setVisibility(View.GONE);
            mImDottedLine.setVisibility(View.GONE);
            mImDottedMapLine.setVisibility(View.GONE);
            mTvFromLabel.setVisibility(View.GONE);
            mTvFromMapLabel.setVisibility(View.GONE);
            mTvFromAddress.setVisibility(View.GONE);
            mTvFromMapAddress.setVisibility(View.GONE);
        }

    }
}
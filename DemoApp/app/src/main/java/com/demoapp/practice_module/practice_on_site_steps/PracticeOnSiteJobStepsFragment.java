package com.demoapp.practice_module.practice_on_site_steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demoapp.R;
import com.demoapp.utilities.Utilities;


/**
 * Created by android5 on 16/7/16.
 */
public class PracticeOnSiteJobStepsFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLltOnSiteStepsLayout;
    private LinearLayout mLltFirstStep;
    private LinearLayout mLltSecondStep;
    private LinearLayout mLltThirdStep;

    private TextView mTvStepOne;
    private TextView mTvStepTwo;
    private TextView mTvStepThree;
    private TextView mTvArrivedOnSite;
    private TextView mTvLeavigJoblabel;
    private TextView mTvCompleteJoblabel;
    private TextView mTvSiteAddress;
    private TextView mTvCustomerName;
    private TextView mTvJobSite;

    private ImageView mImStepOne;
    private ImageView mImStepTwo;
    private ImageView mImStepTwoRed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_job_steps, container, false);
        mLltOnSiteStepsLayout = (LinearLayout) view.findViewById(R.id.on_site_steps_layout);
        mLltFirstStep = (LinearLayout) view.findViewById(R.id.on_site_first_step);
        mLltSecondStep = (LinearLayout) view.findViewById(R.id.on_site_second_step);
        mLltThirdStep = (LinearLayout) view.findViewById(R.id.on_site_third_step);

        mTvStepOne = (TextView) view.findViewById(R.id.on_site_step_one);
        mTvStepTwo = (TextView) view.findViewById(R.id.on_site_step_two);
        mTvStepThree = (TextView) view.findViewById(R.id.on_site_step_three);
        mTvArrivedOnSite = (TextView) view.findViewById(R.id.arrived_on_site);
        mTvLeavigJoblabel = (TextView) view.findViewById(R.id.on_site_leaving_the_job_label);
        mTvCompleteJoblabel = (TextView) view.findViewById(R.id.on_site_complete_job_label);
        mTvSiteAddress = (TextView) view.findViewById(R.id.site_address);
        mTvCustomerName = (TextView) view.findViewById(R.id.on_site_customer_name);
        mTvJobSite = (TextView) view.findViewById(R.id.job_site);

        mImStepOne = (ImageView) view.findViewById(R.id.on_site_first_check);
        mImStepTwo = (ImageView) view.findViewById(R.id.on_site_second_check);
        mImStepTwoRed = (ImageView) view.findViewById(R.id.on_site_second_check_red);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClickListener();
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        mLltOnSiteStepsLayout.setVisibility(View.VISIBLE);
        changeStep(Utilities.getInstance().getPracticeJobStep());
        if (Utilities.getInstance().getPracticeJobStep() < 2) {
            Utilities.getInstance().setSTEPS(1);
        } else {
            Utilities.getInstance().setSTEPS(2);
        }
        getData();
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {

        mTvJobSite.setText(getResources().getString(R.string.job_site));
        mTvCustomerName.setVisibility(View.VISIBLE);
        mTvCustomerName.setText("Alliance Go Trucking");
        mTvSiteAddress.setText(getResources().getString(R.string.from_address));
        if (!TextUtils.equals(Utilities.getInstance().getPracticeJobType(), getResources().getString(R.string.on_site))) {
            mTvArrivedOnSite.setText(getResources().getString(R.string.start_waiting));
            mTvLeavigJoblabel.setText(getResources().getString(R.string.stop_waiting));
        }

        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepTwo,
                    mImStepTwoRed, mLltThirdStep, mTvCompleteJoblabel, mTvStepThree);
            mImStepTwo.setVisibility(View.GONE);
        }
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mLltFirstStep.setOnClickListener(this);
        mLltSecondStep.setOnClickListener(this);
        mLltThirdStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.on_site_first_step:
                if (Utilities.getInstance().getPracticeJobStep() == 0) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }

                    changeStep(1);
                    Utilities.getInstance().setPracticeJobStep(1);

                }
                break;

            case R.id.on_site_second_step:
                if (Utilities.getInstance().getPracticeJobStep() == 1) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeOnSiteLeavingJobFragment())
                            .addToBackStack(getResources().getString(R.string.leaving_job_stack)).commitAllowingStateLoss();
                }
                break;

            case R.id.on_site_third_step:
                if (Utilities.getInstance().getPracticeJobStep() == 2) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeOnSiteCompleteJobFragment())
                                .addToBackStack(getResources().getString(R.string.on_site_complete_job_stack)).commitAllowingStateLoss();
                }
                break;
        }
    }

    /**
     * @description This method will tell that which job step has been done and which is next
     */
    private void changeStep(int status) {
        Log.e("status", ": " + status);
        if (status > 0) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepOne,
                    mImStepOne, mLltSecondStep, mTvLeavigJoblabel, mTvStepTwo);
        }
        if (status > 1) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepTwo,
                    mImStepTwo, mLltThirdStep, mTvCompleteJoblabel, mTvStepThree);
        }
        if (status > 2) {
            Utilities.getInstance().setPracticeJobStep(3);
        }
    }
}

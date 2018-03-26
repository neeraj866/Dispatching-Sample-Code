package com.demoapp.practice_module.practice_haul_on_steps;

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
import com.demoapp.practice_module.PracticeCompleteJobFragment;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;


/**
 * Created by android5 on 16/7/16.
 */
public class PracticeHaulOnJobStepsFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLltHaulOnStepsLayout;
    private LinearLayout mLltFirstStep;
    private LinearLayout mLltSecondStep;
    private LinearLayout mLltThirdStep;
    private LinearLayout mLltForthStep;

    private TextView mTvStepOne;
    private TextView mTvStepTwo;
    private TextView mTvStepThree;
    private TextView mTvStepForth;
    private TextView mTvPickupAddress;
    private TextView mTvDropOffAddress;
    private TextView mTvGetSignatureLabel;
    private TextView mTvDropOffLoadLabel;
    private TextView mTvCompleteJobLabel;
    private TextView mTvFromCustomerName;
    private TextView mTvFromJobSite;
    private TextView mTvToCustomerName;
    private TextView mTvToJobSite;

    private ImageView mImStepOne;
    private ImageView mImStepTwo;
    private ImageView mImStepThree;
    private ImageView mImStepThreeRed;


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

        mLltHaulOnStepsLayout = (LinearLayout) view.findViewById(R.id.haul_on_steps_layout);
        mLltFirstStep = (LinearLayout) view.findViewById(R.id.haul_on_first_step);
        mLltSecondStep = (LinearLayout) view.findViewById(R.id.haul_on_second_step);
        mLltThirdStep = (LinearLayout) view.findViewById(R.id.haul_on_third_step);
        mLltForthStep = (LinearLayout) view.findViewById(R.id.haul_on_forth_step);

        mTvStepOne = (TextView) view.findViewById(R.id.haul_on_step_one);
        mTvStepTwo = (TextView) view.findViewById(R.id.haul_on_step_two);
        mTvStepThree = (TextView) view.findViewById(R.id.haul_on_step_three);
        mTvStepForth = (TextView) view.findViewById(R.id.haul_on_step_four);
        mTvPickupAddress = (TextView) view.findViewById(R.id.haul_on_pickup_address);
        mTvDropOffAddress = (TextView) view.findViewById(R.id.haul_on_drop_off_address);
        mTvGetSignatureLabel = (TextView) view.findViewById(R.id.haul_on_get_signature_label);
        mTvDropOffLoadLabel = (TextView) view.findViewById(R.id.haul_on_drop_off_label);
        mTvCompleteJobLabel = (TextView) view.findViewById(R.id.haul_on_complete_job_label);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.haul_on_from_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.haul_on_from_job_site);
        mTvToCustomerName = (TextView) view.findViewById(R.id.haul_on_to_customer_name);
        mTvToJobSite = (TextView) view.findViewById(R.id.haul_on_to_job_site);

        mImStepOne = (ImageView) view.findViewById(R.id.haul_on_first_check);
        mImStepTwo = (ImageView) view.findViewById(R.id.haul_on_second_check);
        mImStepThree = (ImageView) view.findViewById(R.id.haul_on_third_check);
        mImStepThreeRed = (ImageView) view.findViewById(R.id.haul_on_third_check_red);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClickListerner();
        mLltHaulOnStepsLayout.setVisibility(View.VISIBLE);
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        changeStep(Utilities.getInstance().getPracticeJobStep());
        if (Utilities.getInstance().getPracticeJobStep() < 3) {
            Utilities.getInstance().setSTEPS(1);
        } else {
            Utilities.getInstance().setSTEPS(2);
        }
        getData();
        if (Utilities.getInstance().isApplyAnimation()) {
            Utilities.getInstance().setAnimation(getActivity(), mLltHaulOnStepsLayout, R.anim.anim_steps_up);
        }
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListerner() {
        mLltFirstStep.setOnClickListener(this);
        mLltSecondStep.setOnClickListener(this);
        mLltThirdStep.setOnClickListener(this);
        mLltForthStep.setOnClickListener(this);
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {

        mTvFromJobSite.setText(getResources().getString(R.string.pit_address));

        String fromAddress = getResources().getString(R.string.from_address) + "\n" + getResources().getString(R.string.alliance_po) + "1234";
        mTvPickupAddress.setText(fromAddress);

        mTvFromCustomerName.setVisibility(View.VISIBLE);
        mTvFromCustomerName.setText("John");

        mTvToJobSite.setText(getResources().getString(R.string.job_site));
        mTvDropOffAddress.setText(getResources().getString(R.string.to_address));

        mTvToCustomerName.setVisibility(View.VISIBLE);
        mTvToCustomerName.setText("Alliance Go Trucking");
        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepThree, mImStepThreeRed,
                    mLltForthStep, mTvCompleteJobLabel, mTvStepForth);
            mImStepThree.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.haul_on_first_step:
                if (Utilities.getInstance().getPracticeJobStep() == 0) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeHaulOnPickUpJobFragment())
                            .addToBackStack(getResources().getString(R.string.pickup_job_stack)).commitAllowingStateLoss();
                }
                break;
            case R.id.haul_on_second_step:
                if (Utilities.getInstance().getPracticeJobStep() == 1) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().saveValueToSharedPreference(Constants.JOB_STEPS, "2", getActivity());
                    changeStep(2);
                }
                break;
            case R.id.haul_on_third_step:
                if (Utilities.getInstance().getPracticeJobStep() == 2) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeHaulOnGetSignatureFragment())
                            .addToBackStack(getResources().getString(R.string.get_signature_stack)).commitAllowingStateLoss();
                }
                break;
            case R.id.haul_on_forth_step:
                if (Utilities.getInstance().getPracticeJobStep() == 3) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeCompleteJobFragment())
                            .addToBackStack(getResources().getString(R.string.complete_job_stack)).commitAllowingStateLoss();
                }
                break;
        }
    }

    /**
     * @description This method is used to proceed to next step of the job
     */
    private void changeStep(int status) {
        Log.e("status", ": " + status);
        if (status > 0) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepOne,
                    mImStepOne, mLltSecondStep, mTvDropOffLoadLabel, mTvStepTwo);
        }
        if (status > 1) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepTwo,
                    mImStepTwo, mLltThirdStep, mTvGetSignatureLabel, mTvStepThree);

            Utilities.getInstance().setPracticeJobStep(2);
        }
        if (status > 2) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepThree,
                    mImStepThree, mLltForthStep, mTvCompleteJobLabel, mTvStepForth);

            Utilities.getInstance().setPracticeJobStep(3);
        }
        if (Utilities.getInstance().getPracticeJobStep() > 3) {
            Utilities.getInstance().setPracticeJobStep(4);
        }
    }
}

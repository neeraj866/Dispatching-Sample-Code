package com.demoapp.practice_module.practice_haul_off_steps;

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
public class PracticeHaulOffJobStepsFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLltHaulOffStepsLayout;
    private LinearLayout mLltFirstStep;
    private LinearLayout mLltSecondStep;
    private LinearLayout mLltThirdStep;
    private LinearLayout mLltForthStep;

    private TextView mTvStepOne;
    private TextView mTvStepTwo;
    private TextView mTvStepThree;
    private TextView mTvStepForth;
    private TextView mTvCustomerSignOffLabel;
    private TextView mTvDropOffLoadLabel;
    private TextView mTvCompleteJobLabel;
    private TextView mTvPickupAddress;
    private TextView mTvDropOffAddress;
    private TextView mTvFromCustomerName;
    private TextView mTvToCustomerName;
    private TextView mTvFromJobSite;
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

        mLltHaulOffStepsLayout = (LinearLayout) view.findViewById(R.id.haul_off_steps_layout);
        mLltFirstStep = (LinearLayout) view.findViewById(R.id.haul_off_first_step);
        mLltSecondStep = (LinearLayout) view.findViewById(R.id.haul_off_second_step);
        mLltThirdStep = (LinearLayout) view.findViewById(R.id.haul_off_third_step);
        mLltForthStep = (LinearLayout) view.findViewById(R.id.haul_off_forth_step);

        mTvStepOne = (TextView) view.findViewById(R.id.haul_off_step_one);
        mTvStepTwo = (TextView) view.findViewById(R.id.haul_off_step_two);
        mTvStepThree = (TextView) view.findViewById(R.id.haul_off_step_three);
        mTvStepForth = (TextView) view.findViewById(R.id.haul_off_step_forth);
        mTvCustomerSignOffLabel = (TextView) view.findViewById(R.id.haul_off_customer_sign_off_label);
        mTvDropOffLoadLabel = (TextView) view.findViewById(R.id.haul_off_drop_off_load_label);
        mTvCompleteJobLabel = (TextView) view.findViewById(R.id.haul_off_complete_job_label);
        mTvPickupAddress = (TextView) view.findViewById(R.id.haul_off_pickup_address);
        mTvDropOffAddress = (TextView) view.findViewById(R.id.haul_off_drop_off_address);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.haul_off_customer_name);
        mTvFromJobSite = (TextView) view.findViewById(R.id.haul_off_job_site);
        mTvToJobSite = (TextView) view.findViewById(R.id.haul_off_dump_name);
        mTvToCustomerName = (TextView) view.findViewById(R.id.haul_off_to_customer_name);

        mImStepOne = (ImageView) view.findViewById(R.id.haul_off_first_check);
        mImStepTwo = (ImageView) view.findViewById(R.id.haul_off_second_check);
        mImStepThree = (ImageView) view.findViewById(R.id.haul_off_third_check);
        mImStepThreeRed = (ImageView) view.findViewById(R.id.haul_off_third_check_red);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnClickListener();
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        mLltHaulOffStepsLayout.setVisibility(View.VISIBLE);

        changeStep(Utilities.getInstance().getPracticeJobStep());
        if (Utilities.getInstance().getPracticeJobStep() < 2) {
            Utilities.getInstance().setSTEPS(1);
        } else {
            Utilities.getInstance().setSTEPS(2);
        }
        getData();

        if (Utilities.getInstance().isApplyAnimation()) {
            Utilities.getInstance().setAnimation(getActivity(), mLltHaulOffStepsLayout, R.anim.anim_steps_up);
        }
    }

    /**
     * @description this method is used to proceed to next ste[ of the job
     */
    private void changeStep(int status) {
        Log.e("status", ": " + status);
        if (status > 0) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepOne, mImStepOne,
                    mLltSecondStep, mTvCustomerSignOffLabel, mTvStepTwo);
        }
        if (status > 1) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepTwo, mImStepTwo,
                    mLltThirdStep, mTvDropOffLoadLabel, mTvStepThree);
        }
        if (status > 2) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepThree, mImStepThree,
                    mLltForthStep, mTvCompleteJobLabel, mTvStepForth);
        }
        if (status > 3) {
            Utilities.getInstance().setPracticeJobStep(4);
        }
    }

    /**
     * @description This method is used to set click listener
     */
    private void setOnClickListener() {
        mLltFirstStep.setOnClickListener(this);
        mLltSecondStep.setOnClickListener(this);
        mLltThirdStep.setOnClickListener(this);
        mLltForthStep.setOnClickListener(this);
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {

        mTvFromJobSite.setText(getResources().getString(R.string.job_site));
        mTvPickupAddress.setText(getResources().getString(R.string.from_address));

        mTvFromCustomerName.setVisibility(View.VISIBLE);
        mTvFromCustomerName.setText("Alliance Go Trucking");
        mTvToJobSite.setText(getResources().getString(R.string.drop_address));

        mTvToCustomerName.setVisibility(View.VISIBLE);
        mTvToCustomerName.setText("Andrew");
        String toAddress = getResources().getString(R.string.alliance_po) + "1234";
        mTvDropOffAddress.setText(toAddress);
        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepThree, mImStepThreeRed,
                    mLltForthStep, mTvCompleteJobLabel, mTvStepForth);
            mImStepThree.setVisibility(View.GONE);
        }
        Log.e("driver_signature", ":: " + Utilities.getInstance().getValueFromSharedPreference(Constants.DRIVER_SIGNATURE, "", getActivity()));
        /*if (TextUtils.isEmpty(Utilities.getInstance().getValueFromSharedPreference(Constants.DRIVER_SIGNATURE, "", getActivity()))) {
            Utilities.getInstance().getDriverSignature(getActivity());
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.haul_off_first_step:
                if (Utilities.getInstance().getPracticeJobStep() == 0) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    changeStep(1);
                    Utilities.getInstance().setPracticeJobStep(1);
                }
                break;
            case R.id.haul_off_second_step:
                if (Utilities.getInstance().getPracticeJobStep() == 1) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeHaulOffCustomerSignOffFragment())
                            .addToBackStack(getResources().getString(R.string.customer_sign_off_stack)).commitAllowingStateLoss();
                }
                break;
            case R.id.haul_off_third_step:
                if (Utilities.getInstance().getPracticeJobStep() == 2) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .replace(R.id.main_frame, new PracticeHaulOffDropOffLoadFragment())
                            .addToBackStack(getResources().getString(R.string.drop_off_load_stack)).commitAllowingStateLoss();
                }
                break;
            case R.id.haul_off_forth_step:
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
}
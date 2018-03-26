package com.demoapp.practice_module.practice_dirt_deal_steps;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.demoapp.practice_module.practice_haul_off_steps.PracticeHaulOffCustomerSignOffFragment;
import com.demoapp.practice_module.practice_haul_on_steps.PracticeHaulOnGetSignatureFragment;
import com.demoapp.utilities.Utilities;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by android5 on 27/7/16.
 */
public class PracticeDirtDealJobStepsFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mLltDirtDeal_steps_layout;
    private LinearLayout mLltFirstStep;
    private LinearLayout mLltSecondStep;
    private LinearLayout mLltThirdStep;
    private LinearLayout mLltForthStep;
    private LinearLayout mLltFifthStep;

    private TextView mTvPickUpAddress;
    private TextView mTvDropOffAddress;
    private TextView mTvStepOne;
    private TextView mTvStepTwo;
    private TextView mTvStepThree;
    private TextView mTvStepFour;
    private TextView mTvStepFive;
    private TextView mTvFirstGetSignatureLabel;
    private TextView mTvSecondGetSignatureLabel;
    private TextView mTvDropOffLoadLabel;
    private TextView mTvFirstSignDetails;
    private TextView mTvSecondSignDetails;
    private TextView mTvCompleteJobLabel;
    private TextView mTvFromCustomerName;
    private TextView mTvFromLoadingAddress;
    private TextView mTvToCustomerName;
    private TextView mTvToDumpingAddress;

    private ImageView mImStepOne;
    private ImageView mImStepTwo;
    private ImageView mImStepThree;
    private ImageView mImStepFour;
    private ImageView mImStepFourRed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }

    /**
     * @description This method is used to find views from the layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_job_steps, container, false);

        mLltFirstStep = (LinearLayout) view.findViewById(R.id.dirt_deal_first_step);
        mLltSecondStep = (LinearLayout) view.findViewById(R.id.dirt_deal_second_step);
        mLltThirdStep = (LinearLayout) view.findViewById(R.id.dirt_deal_third_step);
        mLltForthStep = (LinearLayout) view.findViewById(R.id.dirt_deal_forth_step);
        mLltFifthStep = (LinearLayout) view.findViewById(R.id.dirt_deal_fifth_step);
        mLltDirtDeal_steps_layout = (LinearLayout) view.findViewById(R.id.dirt_deal_steps_layout);

        mTvPickUpAddress = (TextView) view.findViewById(R.id.dirt_deal_pickup_address);
        mTvDropOffAddress = (TextView) view.findViewById(R.id.dirt_deal_drop_off_address);
        mTvStepOne = (TextView) view.findViewById(R.id.dirt_deal_step_one);
        mTvStepTwo = (TextView) view.findViewById(R.id.dirt_deal_step_two);
        mTvStepThree = (TextView) view.findViewById(R.id.dirt_deal_step_three);
        mTvStepFour = (TextView) view.findViewById(R.id.dirt_deal_step_four);
        mTvStepFive = (TextView) view.findViewById(R.id.dirt_deal_step_fifth);
        mTvFirstGetSignatureLabel = (TextView) view.findViewById(R.id.dirt_deal_get_signature_first_label);
        mTvSecondGetSignatureLabel = (TextView) view.findViewById(R.id.dirt_deal_get_signature_second_label);
        mTvDropOffLoadLabel = (TextView) view.findViewById(R.id.dirt_deal_drop_off_load_label);
        mTvFirstSignDetails = (TextView) view.findViewById(R.id.dirt_deal_first_sign_details);
        mTvSecondSignDetails = (TextView) view.findViewById(R.id.dirt_deal_second_sign_details);
        mTvCompleteJobLabel = (TextView) view.findViewById(R.id.dirt_deal_complete_job_label);
        mTvFromCustomerName = (TextView) view.findViewById(R.id.dirt_deal_from_customer_name);
        mTvFromLoadingAddress = (TextView) view.findViewById(R.id.dirt_deal_loading_address);
        mTvToCustomerName = (TextView) view.findViewById(R.id.dirt_deal_to_customer_name);
        mTvToDumpingAddress = (TextView) view.findViewById(R.id.dirt_deal_dumping_address);

        mImStepOne = (ImageView) view.findViewById(R.id.dirt_deal_first_check);
        mImStepTwo = (ImageView) view.findViewById(R.id.dirt_deal_second_check);
        mImStepThree = (ImageView) view.findViewById(R.id.dirt_deal_third_check);
        mImStepFour = (ImageView) view.findViewById(R.id.dirt_deal_forth_check);
        mImStepFourRed = (ImageView) view.findViewById(R.id.dirt_deal_forth_check_red);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLltDirtDeal_steps_layout.setVisibility(View.VISIBLE);
        setOnClickListener();

        changeStep(Utilities.getInstance().getPracticeJobStep());
        if (Utilities.getInstance().getPracticeJobStep() < 2) {
            Utilities.getInstance().setSTEPS(1);
        } else {
            Utilities.getInstance().setSTEPS(2);
        }
        getData();
        if (Utilities.getInstance().isApplyAnimation()) {
            Utilities.getInstance().setAnimation(getActivity(), mLltDirtDeal_steps_layout, R.anim.anim_steps_up);
        }
    }


    /**
     * @description This method is used to get job details from the shared preferences
     */
    private void getData() {

        mTvFromLoadingAddress.setText(getResources().getString(R.string.job_site1));

        mTvFromCustomerName.setText("Alliance Go Trucking");

        String final_address = getResources().getString(R.string.from_address) + "\n" + getResources().getString(R.string.alliance_po) + " 1234";
        String drop_off_address = getResources().getString(R.string.to_address) + "\n" + getResources().getString(R.string.alliance_po) + " 1234";

        mTvPickUpAddress.setText(final_address);
        mTvToDumpingAddress.setText(getResources().getString(R.string.job_site2));
        mTvDropOffAddress.setText(drop_off_address);

        mTvToCustomerName.setText("Alliance Go Trucking");
        mTvFirstSignDetails.setText("Alliance Go Trucking");
        mTvSecondSignDetails.setText("Alliance Go Trucking");
        if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepFour, mImStepFourRed,
                    mLltFifthStep, mTvCompleteJobLabel, mTvStepFive);
            mImStepFour.setVisibility(View.GONE);
        }
    }

    /**
     * @description This method is used to set click listeners.
     */
    private void setOnClickListener() {
        mLltFirstStep.setOnClickListener(this);
        mLltSecondStep.setOnClickListener(this);
        mLltThirdStep.setOnClickListener(this);
        mLltForthStep.setOnClickListener(this);
        mLltFifthStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dirt_deal_first_step:
                if (Utilities.getInstance().getPracticeJobStep() == 0) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    changeStep(1);
                    Utilities.getInstance().setPracticeJobStep(1);
                }
                break;

            case R.id.dirt_deal_second_step:
                if (Utilities.getInstance().getPracticeJobStep() == 1) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frame, new PracticeHaulOffCustomerSignOffFragment())
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .addToBackStack(getResources().getString(R.string.customer_sign_off_stack)).commitAllowingStateLoss();

                }
                break;

            case R.id.dirt_deal_third_step:
                if (Utilities.getInstance().getPracticeJobStep() == 2) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    changeStep(3);
                    Utilities.getInstance().setPracticeJobStep(3);
                }
                break;

            case R.id.dirt_deal_forth_step:
                if (Utilities.getInstance().getPracticeJobStep() == 3) {
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

            case R.id.dirt_deal_fifth_step:
                if (Utilities.getInstance().getPracticeJobStep() == 4) {
                    if (!Utilities.getInstance().locationEnabled(getActivity())) {
                        Utilities.getInstance().enableGps(getActivity());
                        return;
                    }
                    Utilities.getInstance().setIS_LAST_FRAGMENT(false);
                    Date date = new Date();


                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frame, new PracticeCompleteJobFragment())
                            .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                            .addToBackStack(getResources().getString(R.string.complete_job_stack)).commitAllowingStateLoss();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * @description This method is used to proceed to next step of the job
     */
    private void changeStep(int status) {
        Log.e("status", ": " + status);
        if (status > 0) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepOne, mImStepOne,
                    mLltSecondStep, mTvFirstGetSignatureLabel, mTvStepTwo);
        }
        if (status > 1) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepTwo, mImStepTwo,
                    mLltThirdStep, mTvDropOffLoadLabel, mTvStepThree);
        }
        if (status > 2) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepThree, mImStepThree,
                    mLltForthStep, mTvSecondGetSignatureLabel, mTvStepFour);
        }
        if (status > 3) {
            Utilities.getInstance().setStepBackgroud(getActivity(), mTvStepFour, mImStepFour,
                    mLltFifthStep, mTvCompleteJobLabel, mTvStepFive);
        }
    }

    /**
     * @description This method is used to get the permission from user
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    if (isAdded())
                        askPermissionDialog();
                }
            }
        }
    }

    /**
     * @description This method is used to display the alert for permission to be allowed by user
     */
    private void askPermissionDialog() {
        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity());
        alert.setMessage("Please Allow Camera Permission."); //Message here
        alert.setCancelable(false); //dialogbox not canceled if user click/touch outside the dialog box
        // Set an EditText view to get user input
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //You will get as string input data in this variable.
                // here we convert the input to a string.
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 10);
            } // End of onClick(DialogInterface dialog, int whichButton)
        }); //End of alert.setPositiveButton
        alert.show();

    }
}
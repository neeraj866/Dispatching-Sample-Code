package com.demoapp.practice_module.practice_haul_off_steps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demoapp.R;
import com.demoapp.abstract_classes.RejectDialog;
import com.demoapp.activities.ImageCropActivity;
import com.demoapp.interfaces.DialogCallBackListener;
import com.demoapp.interfaces.TakeImageListener;
import com.demoapp.map.MapFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;



/**
 * Created by android5 on 22/7/16.
 */
public class PracticeHaulOffDropOffLoadFragment extends Fragment implements View.OnClickListener, TakeImageListener {

    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;

    private TextView mTVAlliancePo;
    private TextView mTVBillDumpFeeTo;
    private TextView mTVToAddress;
    private TextView mTVFromMapLabel;
    private TextView mTVFromMapAddress;
    private TextView mTVToMapAddress;
    private TextView mTvDeleteImage;
    private TextView mTvEndTime;
    private TextView mTvCustomerName;
    private TextView mTvJobSite;
    private TextView mTvUnits;

    private RelativeLayout mRltRejectLoad;
    private RelativeLayout mRltAcceptLoad;
    private RelativeLayout mRltPitTicketPhoto;
    private RelativeLayout mRltPitTicketPhotoLayout;
    private RelativeLayout mRltTicketImageLayout;
    private RelativeLayout mRltMapView;

    private EditText mEtMaterialQuantity;
    private EditText mEtPitTicketNumber;

    private Button mBtnDone;
    private Button mBtnDetails;
    private Button mBtnMapView;

    private LinearLayout mLltTimeLayout;
    private LinearLayout mLltJobDetails;
    private LinearLayout mLltTicketLayout;
    private LinearLayout mLltBreakLayout;

    private ImageView mIMDumpTicketPhoto;
    private ImageView mIMSourceMapIcon;
    private ImageView mIMDottedMapLine;

    private View mView; // for hiding the keyboard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);

    }

    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_haul_off_drop_load, container, false);

        mTVAlliancePo = (TextView) view.findViewById(R.id.alliance_po);
        mTVBillDumpFeeTo = (TextView) view.findViewById(R.id.bill_dump_fee_to);
        mTVToAddress = (TextView) view.findViewById(R.id.to_address);
        mTVFromMapAddress = (TextView) view.findViewById(R.id.from_map_address);
        mTVFromMapLabel = (TextView) view.findViewById(R.id.from_map_label);
        mTVToMapAddress = (TextView) view.findViewById(R.id.to_map_address);
        mTvDeleteImage = (TextView) view.findViewById(R.id.delete_image);
        mTvEndTime = (TextView) view.findViewById(R.id.end_time);
        mTvCustomerName = (TextView) view.findViewById(R.id.to_customer_name);
        mTvJobSite = (TextView) view.findViewById(R.id.to_job_site);
        mTvUnits = (TextView) view.findViewById(R.id.units);

        mRltRejectLoad = (RelativeLayout) view.findViewById(R.id.reject_layout);
        mRltAcceptLoad = (RelativeLayout) view.findViewById(R.id.accept_layout);
        mRltPitTicketPhoto = (RelativeLayout) view.findViewById(R.id.get_pit_ticket);
        mRltPitTicketPhotoLayout = (RelativeLayout) view.findViewById(R.id.ticket_photo_layout);
        mRltTicketImageLayout = (RelativeLayout) view.findViewById(R.id.ticket_image_layout);

        mEtMaterialQuantity = (EditText) view.findViewById(R.id.material_qty);
        mEtPitTicketNumber = (EditText) view.findViewById(R.id.pit_ticket_number);

        mLltJobDetails = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltTicketLayout = (LinearLayout) view.findViewById(R.id.ticket_layout);
        mLltBreakLayout = (LinearLayout) view.findViewById(R.id.break_layout);
        mLltTimeLayout = (LinearLayout) view.findViewById(R.id.time_layout);

        mBtnDone = (Button) view.findViewById(R.id.done_button);
        mBtnDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);

        mIMDumpTicketPhoto = (ImageView) view.findViewById(R.id.pit_ticket_Image);
        mIMSourceMapIcon = (ImageView) view.findViewById(R.id.source_map_icon);
        mIMDottedMapLine = (ImageView) view.findViewById(R.id.dotted_map_line);

        mRltMapView.setVisibility(View.GONE);

        mView = view;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageCropActivity imageCropActivity = new ImageCropActivity();
        imageCropActivity.callBack(this);

        Utilities.getInstance().setSTEPS(3);
        setOnclickListener();
        getData(view);
        hideViews();
    }

    /**
     * @description This method is used to hide the views from the map layout
     */
    private void hideViews() {
        mTVFromMapAddress.setVisibility(View.GONE);
        mIMSourceMapIcon.setVisibility(View.GONE);
        mIMDottedMapLine.setVisibility(View.GONE);
    }

    /**
     * @description This method is used to set click listeners
     */
    private void setOnclickListener() {
        mRltRejectLoad.setOnClickListener(this);
        mRltAcceptLoad.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
        mBtnDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mRltPitTicketPhoto.setOnClickListener(this);
        mTvDeleteImage.setOnClickListener(this);
        mLltJobDetails.setOnClickListener(this);
        mTvEndTime.setOnClickListener(this);
    }

    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData(View view) {


        mTVAlliancePo.setText("1234");
        mTVBillDumpFeeTo.setText(getResources().getString(R.string.not_));

        mLltBreakLayout.setVisibility(View.GONE);
        mLltTimeLayout.setVisibility(View.GONE);
        mTVFromMapLabel.setVisibility(View.GONE);
        mLltBreakLayout.setVisibility(View.GONE);


        mEtMaterialQuantity.setText(getResources().getString(R.string.one_load) + " " + getResources().getString(R.string.load));
        mEtMaterialQuantity.setFocusable(false);
        mEtMaterialQuantity.setFocusableInTouchMode(false);
        mEtMaterialQuantity.setHint("");
        mTvUnits.setText("");
        mEtMaterialQuantity.setBackgroundResource(R.drawable.rounded_corner);
        mTvJobSite.setText(getResources().getString(R.string.drop_address));
        mTVToAddress.setText(getResources().getString(R.string.to_address));
        mTvCustomerName.setVisibility(View.VISIBLE);
        mTvCustomerName.setText("Andrew");
        mTVToMapAddress.setText(getResources().getString(R.string.to_address));

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
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapView, mBtnDetails, mBtnMapView, true);
                break;

            case R.id.map_button:
                Utilities.getInstance().changeView(getActivity(), mLltJobDetails, mRltMapView, mBtnDetails, mBtnMapView, false);
                break;

            case R.id.reject_layout:
                RejectDialog dialogueUtils = new RejectDialog() {
                    @Override
                    public void receiveData(String result) {
                        Log.e("reason", ": " + result);
                        if (!TextUtils.isEmpty(result)) {
                            mRltRejectLoad.setVisibility(View.GONE);
                            mRltAcceptLoad.setVisibility(View.VISIBLE);
                            Utilities.getInstance().setPracticeRejectReason(result);
                            mRltPitTicketPhotoLayout.setVisibility(View.GONE);
                            mLltTicketLayout.setVisibility(View.GONE);
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
                                Utilities.getInstance().setPracticeRejectReason("");
                                mRltPitTicketPhotoLayout.setVisibility(View.VISIBLE);
                                mLltTicketLayout.setVisibility(View.VISIBLE);
                            }
                        });
                break;

            case R.id.get_pit_ticket:
                Utilities.getInstance().choosePhotoDialog(getActivity());
                break;

            case R.id.done_button:
                if (TextUtils.isEmpty(mEtMaterialQuantity.getText().toString().trim())) {
                    ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_material_qty));
                    return;
                }
                if (!TextUtils.isEmpty(Utilities.getInstance().getPracticeRejectReason())) {
                    Utilities.getInstance().setIS_LAST_FRAGMENT(true);
                    Utilities.getInstance().setPracticeJobStep(3);
                    getActivity().getSupportFragmentManager().popBackStack();
                    return;
                } else {
                    if (TextUtils.isEmpty(mEtPitTicketNumber.getText().toString().trim())) {
                        ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_dump_ticket_number));
                        return;
                    } else if (TextUtils.isEmpty(Utilities.getInstance().getPracticeTicketImage())) {

                        ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_capture_dump_ticket_photo));
                        return;
                    }
                }
                if (!Utilities.getInstance().locationEnabled(getActivity())) {
                    Utilities.getInstance().enableGps(getActivity());
                    return;
                }
                Utilities.getInstance().setIS_LAST_FRAGMENT(true);
                Utilities.getInstance().setPracticeJobStep(3);
                getActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.delete_image:
                Utilities.getInstance().setPracticeTicketImage("");
                mRltTicketImageLayout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * @description this method will return the image from camera or gallery
     */
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == REQUEST_CODE_UPDATE_PIC) {
            if (resultCode == getActivity().RESULT_OK) {
                String imagePath = result.getStringExtra("image-path");
                showCroppedImage(imagePath);
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                //TODO : Handle case
            } else {
                String errorMsg = result.getStringExtra(ImageCropActivity.ERROR_MSG);
                Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showCroppedImage(String mImagePath) {
        if (mImagePath != null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(mImagePath);
            mIMDumpTicketPhoto.setImageBitmap(myBitmap);
        }
    }

    @Override
    public void getImage(String Path) {
        if (Path != null) {
            Utilities.getInstance().hideKeyboard(getActivity(), mView);
            Utilities.getInstance().setImage(mIMDumpTicketPhoto, Path, true);
            mRltTicketImageLayout.setVisibility(View.VISIBLE);
            Utilities.getInstance().setPracticeTicketImage(Path);
        }
    }
}

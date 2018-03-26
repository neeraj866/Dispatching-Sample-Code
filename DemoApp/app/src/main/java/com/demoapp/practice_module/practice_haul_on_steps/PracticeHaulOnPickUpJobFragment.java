package com.demoapp.practice_module.practice_haul_on_steps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import com.demoapp.activities.ImageCropActivity;
import com.demoapp.interfaces.TakeImageListener;
import com.demoapp.map.MapFragment;
import com.demoapp.singleton.ShowDialogUtils;
import com.demoapp.utilities.Constants;
import com.demoapp.utilities.Utilities;

/**
 * Created by android5 on 17/7/16.
 */
public class PracticeHaulOnPickUpJobFragment extends Fragment implements View.OnClickListener, TakeImageListener {

    public static final int REQUEST_CODE_UPDATE_PIC = 0x1;

    private Button mBtnJobDetails;
    private Button mBtnMapView;
    private Button mBtnDone;

    private LinearLayout mLltJobDetails;
    private LinearLayout mLltPitTicketLayout;

    private TextView mTvAlliancePo;
    private TextView mTvBillMaterialTo;
    private TextView mTvFromAddress;
    private TextView mTvToAddress;
    private TextView mTvDeleteImage;
    private TextView mTvUnits;

    private EditText mEtPitTicketNumber;
    private EditText mEtMaterialQty;

    private RelativeLayout mRltGetPitTicketImage;
    private RelativeLayout mRltTicketImageLayout;
    private RelativeLayout mRltMapView;

    private ImageView mImPitTicket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return init(inflater, container);
    }

    /**
     * @description This method is used to get views from layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_haul_on_pickup_job, container, false);

        mBtnJobDetails = (Button) view.findViewById(R.id.details_button);
        mBtnMapView = (Button) view.findViewById(R.id.map_button);
        mBtnDone = (Button) view.findViewById(R.id.done_button);

        mLltJobDetails = (LinearLayout) view.findViewById(R.id.details_layout);
        mRltMapView = (RelativeLayout) view.findViewById(R.id.map_layout);
        mLltPitTicketLayout = (LinearLayout) view.findViewById(R.id.pit_ticket_layout);

        mTvAlliancePo = (TextView) view.findViewById(R.id.alliance_po);
        mTvBillMaterialTo = (TextView) view.findViewById(R.id.bill_material_to);
        mTvFromAddress = (TextView) view.findViewById(R.id.from_map_address);
        mTvToAddress = (TextView) view.findViewById(R.id.to_map_address);
        mTvDeleteImage = (TextView) view.findViewById(R.id.delete_image);
        mTvUnits = (TextView) view.findViewById(R.id.units);

        mRltGetPitTicketImage = (RelativeLayout) view.findViewById(R.id.get_pit_ticket);
        mRltTicketImageLayout = (RelativeLayout) view.findViewById(R.id.ticket_image_layout);

        mEtMaterialQty = (EditText) view.findViewById(R.id.material_qty);
        mEtPitTicketNumber = (EditText) view.findViewById(R.id.pit_ticket_number);

        mImPitTicket = (ImageView) view.findViewById(R.id.pit_ticket_Image);

        mRltMapView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageCropActivity imageCropActivity = new ImageCropActivity();
        imageCropActivity.callBack(this);

        Utilities.getInstance().setSTEPS(3);
        setOnClickListener();
        getData();
    }


    /**
     * @description This method is used to get data for the in progress job
     */
    private void getData() {


        mTvAlliancePo.setText("1234");
        mTvBillMaterialTo.setText(getResources().getString(R.string.not_));


        mTvFromAddress.setText(getResources().getString(R.string.from_address));
        mTvToAddress.setText(getResources().getString(R.string.to_address));


        mTvUnits.setText("");
        mEtMaterialQty.setText(getResources().getString(R.string.one_load) + " " + getResources().getString(R.string.load));
        mEtMaterialQty.setFocusable(false);
        mEtMaterialQty.setHint("");
        mEtMaterialQty.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_corner));

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
        mBtnJobDetails.setOnClickListener(this);
        mBtnMapView.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
        mRltGetPitTicketImage.setOnClickListener(this);
        mTvDeleteImage.setOnClickListener(this);
        mLltJobDetails.setOnClickListener(this);
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
                if (TextUtils.isEmpty(mEtMaterialQty.getText().toString().trim())) {
                    ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_material_qty));
                    return;
                } else if (TextUtils.isEmpty(mEtPitTicketNumber.getText().toString().trim())) {
                    ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_enter_ticket_number));
                    return;
                } else if (TextUtils.isEmpty(Utilities.getInstance().getPracticeTicketImage())) {
                    ShowDialogUtils.getInstance().showAlertDialog(getActivity(), getResources().getString(R.string.warning), getResources().getString(R.string.please_capture_pit_ticket_photo));
                    return;
                }

                if (!Utilities.getInstance().locationEnabled(getActivity())) {
                    Utilities.getInstance().enableGps(getActivity());
                    return;
                }


                Utilities.getInstance().setPracticeJobStep(1);
                getActivity().getSupportFragmentManager().popBackStack();

                break;

            case R.id.get_pit_ticket:
                Utilities.getInstance().choosePhotoDialog(getActivity());
                break;

            case R.id.delete_image:
                Utilities.getInstance().setPracticeTicketImage("");
                mRltTicketImageLayout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * @description This method will get the resultant image from the camera or gallery
     */
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == REQUEST_CODE_UPDATE_PIC) {
            if (resultCode == getActivity().RESULT_OK) {
                String imagePath = result.getStringExtra("image-path");
                Utilities.getInstance().setImage(mImPitTicket, imagePath, true);
//                showCroppedImage(imagePath);
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
            mImPitTicket.setImageBitmap(myBitmap);
        }
    }

    @Override
    public void getImage(String Path) {
        if (Path != null) {
            Utilities.getInstance().setImage(mImPitTicket, Path, true);
            Utilities.getInstance().setPracticeTicketImage(Path);
            mRltTicketImageLayout.setVisibility(View.VISIBLE);
        }
    }
}
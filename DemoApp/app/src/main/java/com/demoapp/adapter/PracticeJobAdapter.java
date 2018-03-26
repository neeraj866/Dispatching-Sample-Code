package com.demoapp.adapter;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.demoapp.R;
import com.demoapp.enum_classes.JobsEnum;
import com.demoapp.model_classes.JobListingResponse;
import com.demoapp.practice_module.PracticeJobDetailsFragment;
import com.demoapp.utilities.Utilities;

import java.util.Date;
import java.util.List;

public class PracticeJobAdapter extends RecyclerView.Adapter<PracticeJobAdapter.MyViewHolder> {

    private Context mContext;
    private List<JobListingResponse> mLstJobResponse;

    public PracticeJobAdapter(Context context, List<JobListingResponse> mLstJobResponse) {
        this.mContext = context;
        this.mLstJobResponse = mLstJobResponse;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jobs_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (mLstJobResponse.get(position) != null) {
            holder.mJobName.setText(mLstJobResponse.get(position).getJob_name());

            JobsEnum jobsEnum = JobsEnum.valueOf(mLstJobResponse.get(position).getType());

            switch (jobsEnum) {
                case HAULON:
                    holder.mJobIcon.setImageResource(R.drawable.ic_haul_on);
                    holder.mJobType.setText(mContext.getResources().getString(R.string.haul_on_));
                    holder.mMaterialType.setVisibility(View.VISIBLE);
                    holder.mMaterialType.setText(mContext.getResources().getString(R.string.default_material));
                    break;

                case HAULOFF:
                    holder.mJobIcon.setImageResource(R.drawable.ic_haul_off);
                    holder.mJobType.setText(mContext.getResources().getString(R.string.haul_off_));
                    holder.mMaterialType.setVisibility(View.VISIBLE);
                    holder.mMaterialType.setText(mContext.getResources().getString(R.string.default_material));
                    break;

                case DIRTDEAL:
                    holder.mJobIcon.setImageResource(R.drawable.ic_dirt_deal);
                    holder.mJobType.setText(mContext.getResources().getString(R.string.dirt_deal_));
                    holder.mMaterialType.setVisibility(View.VISIBLE);
                    holder.mMaterialType.setText(mContext.getResources().getString(R.string.default_material));
                    break;

                case WAIT:
                    holder.mJobIcon.setImageResource(R.drawable.ic_wait_icon);
                    holder.mJobType.setText(mContext.getResources().getString(R.string.wait_));
                    holder.mMaterialType.setVisibility(View.GONE);
                    break;

                case ONSITE:
                    holder.mJobIcon.setImageResource(R.drawable.ic_on_site);
                    holder.mJobType.setText(mContext.getResources().getString(R.string.on_site_));
                    holder.mMaterialType.setVisibility(View.GONE);
                    break;
            }
            Date date = new Date();
            String s1[] = Utilities.getInstance().getFormatter1().format(date).split(" ");
            holder.mJobTime.setText(Utilities.getInstance().checkNull(getDateMonth(s1[0]) + " " + getTime(s1[1])));
            holder.mAddress.setText(mContext.getResources().getString(R.string.from_address));
            holder.mTrailerType.setText(Utilities.getInstance().checkNull(mLstJobResponse.get(position).getTrailer_type()));

            holder.mItemHolder.setTag(position);
            holder.mItemHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    callFragment(pos);

                }
            });
            FragmentActivity _context = (FragmentActivity) mContext;
            Utilities.getInstance().setAnimation(_context, holder.mItemContainer, R.anim.anim_item_animation);
        }
    }

    private void callFragment(int pos) {
        FragmentActivity _context = (FragmentActivity) mContext;
        if (Utilities.getInstance().checkPermission(_context, Manifest.permission.ACCESS_FINE_LOCATION)) {


            Utilities.getInstance().setIS_FROM_LIST(true);
            PracticeJobDetailsFragment jobDetails = new PracticeJobDetailsFragment();
            Bundle bundle = new Bundle();
            Utilities.getInstance().setPracticeJobType(mLstJobResponse.get(pos).getType());
            Utilities.getInstance().setPracticeJobName(mLstJobResponse.get(pos).getJob_name());
//            bundle.putSerializable(Constants.P_JOB_NAME, mLstJobResponse.get(pos));
            Log.e("order", ":" + mLstJobResponse.get(pos).getOrder_num());
            Utilities.getInstance().setSTEPS(0);

            Log.e("today", ":" + mLstJobResponse.get(pos).is_today());
            jobDetails.setArguments(bundle);
//        ((DriverLandingActivity) context).showMenu(false);
            Utilities.getInstance().setMAP_DIRECTION(null);
            Utilities.getInstance().setIS_LAST_FRAGMENT(false);
            Utilities.getInstance().setSHOW_MAP_OFFLINE(false);
            _context.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left).
                    replace(R.id.main_frame, jobDetails)
                    .addToBackStack(mContext.getResources().getString(R.string.practice_job_details_stack)).commit();
        }
    }

    @Override
    public int getItemCount() {
        return mLstJobResponse.size();
    }

    private String getDateMonth(String date) {
        String day = mContext.getResources().getString(R.string.today);


        String month = "";
        String s1[] = date.split("-");
        if (TextUtils.equals(s1[1], "01")) {
            month = s1[2] + " Jan. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "02")) {
            month = s1[2] + " Feb. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "03")) {
            month = s1[2] + " Mar. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "04")) {
            month = s1[2] + " Apr. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "05")) {
            month = s1[2] + " May. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "06")) {
            month = s1[2] + " Jun. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "07")) {
            month = s1[2] + " Jul. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "08")) {
            month = s1[2] + " Aug. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "09")) {
            month = s1[2] + " Sep. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "10")) {
            month = s1[2] + " Oct. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "11")) {
            month = s1[2] + " Nov. (" + day + ")";
        } else if (TextUtils.equals(s1[1], "12")) {
            month = s1[2] + " Dec. (" + day + ")";
        }

        return month;
    }

    private String getTime(String time) {
        String finalTime = "";
        String s1[] = time.split(":");
        if (Integer.parseInt(s1[0]) >= 12) {
            if ((Integer.parseInt(s1[0]) - 12) < 10)
                finalTime = "0" + (Integer.parseInt(s1[0]) - 12) + ":" + s1[1] + " PM";
            else {
                finalTime = "" + (Integer.parseInt(s1[0]) - 12) + ":" + s1[1] + " PM";
            }
        } else {
            if ((Integer.parseInt(s1[0])) < 10) {
                finalTime = "0" + (Integer.parseInt(s1[0])) + ":" + s1[1] + " AM";
            } else {
                finalTime = "" + (Integer.parseInt(s1[0])) + ":" + s1[1] + " AM";
            }
        }

        return finalTime;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mJobName;
        TextView mJobType;
        TextView mJobTime;
        TextView mAddress;
        TextView mTrailerType;
        TextView mMaterialType;
        ImageView mJobIcon;
        LinearLayout mItemContainer;
        LinearLayout mItemHolder;

        public MyViewHolder(View view) {
            super(view);
            mJobName = (TextView) view.findViewById(R.id.job_name);
            mItemContainer = (LinearLayout) view.findViewById(R.id.item_container);
            mJobType = (TextView) view.findViewById(R.id.job_type);
            mJobTime = (TextView) view.findViewById(R.id.job_time);
            mAddress = (TextView) view.findViewById(R.id.address);
            mTrailerType = (TextView) view.findViewById(R.id.trailer_type);
            mItemHolder = (LinearLayout) view.findViewById(R.id.item_holder);
            mMaterialType = (TextView) view.findViewById(R.id.material_type);
            mJobIcon = (ImageView) view.findViewById(R.id.job_icon);
        }
    }
}
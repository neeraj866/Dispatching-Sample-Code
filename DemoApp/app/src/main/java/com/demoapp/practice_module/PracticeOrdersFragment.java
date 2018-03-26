package com.demoapp.practice_module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.demoapp.R;
import com.demoapp.adapter.PracticeJobAdapter;
import com.demoapp.model_classes.JobListingResponse;
import com.demoapp.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by android5 on 7/6/16.
 */
public class PracticeOrdersFragment extends Fragment {

    private RecyclerView mRvJobList;
    private List<JobListingResponse> mLstJobListArray;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return init(inflater, container);
    }


    /**
     * @description This method is used to set views from the layout
     */
    private View init(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRvJobList = (RecyclerView) view.findViewById(R.id.job_list);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utilities.getInstance().setIS_LAST_FRAGMENT(true);
        swipeRefreshLayout.setEnabled(false);
        Utilities.getInstance().setPracticeJobStep(0);
        Utilities.getInstance().setPracticeHaulOffSignatory("");
        Utilities.getInstance().setPracticeHaulOnSignatory("");
        Utilities.getInstance().setPracticeHaulOffIsCustomerPresent(false);
        Utilities.getInstance().setPracticeHaulOnIsCustomerPresent(false);
        Utilities.getInstance().setPracticeHaulOffCustomerSignature("");
        Utilities.getInstance().setPracticeHaulOnCustomerSignature("");
        Utilities.getInstance().setPracticeRejectReason("");

        getJobs();
    }


    /**
     * @description This method is used to get job from the server
     */
    private void getJobs() {
        JobListingResponse model1 = new JobListingResponse();
        model1.setJob_name("Dirt Deal Job");
        model1.setType(getResources().getString(R.string.dirt_deal));
        model1.setTrailer_type("Trailer");

        JobListingResponse model2 = new JobListingResponse();
        model2.setJob_name("Haul Off Job");
        model2.setType(getResources().getString(R.string.haul_off));
        model2.setTrailer_type("Tandem");

        JobListingResponse model3 = new JobListingResponse();
        model3.setJob_name("Haul On Job");
        model3.setType(getResources().getString(R.string.haul_on));
        model3.setTrailer_type("Trailer");

        JobListingResponse model4 = new JobListingResponse();
        model4.setJob_name("On Site Job");
        model4.setType(getResources().getString(R.string.on_site));
        model4.setTrailer_type("Tandem");

        JobListingResponse model5 = new JobListingResponse();
        model5.setJob_name("Wait Job");
        model5.setType(getResources().getString(R.string.wait));
        model5.setTrailer_type("Trailer");

        mLstJobListArray = new ArrayList<>();
        mLstJobListArray.add(model1);
        mLstJobListArray.add(model2);
        mLstJobListArray.add(model3);
        mLstJobListArray.add(model4);
        mLstJobListArray.add(model5);

        setAdapter();
    }


    /**
     * @description This method is used to set adapter to the recyclerView
     */
    private void setAdapter() {
        PracticeJobAdapter mJobListAdapter = new PracticeJobAdapter(getActivity(), mLstJobListArray);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }

        };
        mRvJobList.setLayoutManager(lm);
        mRvJobList.setItemAnimator(new DefaultItemAnimator());
        mRvJobList.setAdapter(mJobListAdapter);
        mRvJobList.scrollBy(0, 0);
    }
}

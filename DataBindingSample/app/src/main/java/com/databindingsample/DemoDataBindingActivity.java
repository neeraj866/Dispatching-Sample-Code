package com.databindingsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.databindingsample.databinding.DemoActivityLayoutBinding;


/**
 * Created by android on 9/1/17.
 */

public class DemoDataBindingActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DemoActivityLayoutBinding layoutBinding = DataBindingUtil.setContentView(this, R.layout.demo_activity_layout);

        layoutBinding.setDemoactivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new DemoFragment()).commitAllowingStateLoss();
    }
}


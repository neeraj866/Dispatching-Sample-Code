package com.demoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demoapp.R;
import com.demoapp.practice_module.PracticeOrdersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new PracticeOrdersFragment())
                .setCustomAnimations(R.anim.anim_fade_in, 0, 0, R.anim.anim_slide_out_left)
                .commitAllowingStateLoss();

    }
}

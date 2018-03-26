package com.databindingsample;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.databindingsample.databinding.DemoFragmentLayoutBinding;

import java.util.Objects;

/**
 * Created by android on 9/1/17.
 */

public class DemoFragment extends Fragment {
    DemoFragmentLayoutBinding demoFragmentLayoutBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        demoFragmentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.demo_fragment_layout, container, false);
        View view = demoFragmentLayoutBinding.getRoot();
        demoFragmentLayoutBinding.setClickevent(new ButtonClickEvent());
        demoFragmentLayoutBinding.setEcho(new Echo());
        return view;
    }

    public class ButtonClickEvent {
        /**
         * using parameter inside the method
         *
         * @param echo
         */
        public void showName(Echo echo) {
            /**
             * you can use below line if using with id
             */
            demoFragmentLayoutBinding.txtview.setTextColor(Color.RED);

            /**
             * you can use below code if not using ids
             */
            Log.e("fname", ":: " + echo.fname.get());
            Log.e("lname", ":: " + echo.lname.get());
            echo.FullName.set(echo.fname.get() + " " + echo.lname.get());

        }

        /**
         * without parameter method
         */
        public void showToast() {
            Toast.makeText(getActivity(), "Shown", Toast.LENGTH_SHORT).show();
        }

    }


    public class Echo {
        /**
         * use it if not using id
         */
        public ObservableField<String> fname = new ObservableField<>();
        public ObservableField<String> lname = new ObservableField<>();
        public ObservableField<String> FullName = new ObservableField<>();
        public TextWatcher watcherFname = getTextWatcher(fname);
        public TextWatcher watcherLname = getTextWatcher(lname);
//        public TextWatcher watcherFullName = getTextWatcher(FullName);

        private TextWatcher getTextWatcher(final ObservableField<String> text) {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void afterTextChanged(Editable editable) {
                    if (!Objects.equals(text.get(), editable.toString())) {
                        text.set(editable.toString());
                    }

                }
            };

        }
    }
}

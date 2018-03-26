package com.demoapp.abstract_classes;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.demoapp.R;
import com.demoapp.interfaces.SetTimeListener;
import com.demoapp.singleton.ShowDialogUtils;

import java.lang.reflect.Field;

/**
 * Created by android5 on 29/8/16.
 */
public abstract class CustomTimePicker implements SetTimeListener {

    public void showTimePickerDialog(Activity activity, int hour, int minute) {
        final Dialog alertDialog = ShowDialogUtils.getInstance().initiateDialog(activity);
        alertDialog.setContentView(R.layout.dialog_time_picker);
        Button btnCancel = (Button) alertDialog.findViewById(R.id.cancel_button);
        Button btnSet = (Button) alertDialog.findViewById(R.id.set_button);
        final NumberPicker npHourPicker = (NumberPicker) alertDialog.findViewById(R.id.numberPicker1);
        final NumberPicker npMinutePicker = (NumberPicker) alertDialog.findViewById(R.id.numberPicker2);
        npHourPicker.setMinValue(00);
        npHourPicker.setMaxValue(23);
        npHourPicker.setValue(hour);
        npHourPicker.setWrapSelectorWheel(true);
        npHourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npHourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        setNumberPickerTextColor(npHourPicker, ContextCompat.getColor(activity, R.color.blue_text));
        npHourPicker.setDisplayedValues(activity.getResources().getStringArray(R.array.minutes));
        npMinutePicker.setMinValue(00);
        npMinutePicker.setMaxValue(59);
        npMinutePicker.setValue(minute);
        npMinutePicker.setWrapSelectorWheel(true);
        npMinutePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npMinutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        setNumberPickerTextColor(npMinutePicker, ContextCompat.getColor(activity, R.color.blue_text));
        npMinutePicker.setDisplayedValues(activity.getResources().getStringArray(R.array.minutes));
        btnCancel.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             alertDialog.dismiss();
                                         }
                                     }
        );
        btnSet.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          setTime(String.valueOf(npHourPicker.getValue()), String.valueOf(npMinutePicker.getValue()));
                                          alertDialog.dismiss();
                                      }
                                  }
        );
        alertDialog.show();
    }

    public abstract void setTime(String hour, String minute);

    private void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }
}
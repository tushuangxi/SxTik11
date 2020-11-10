package com.tushuangxi.smart.tv.lding.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

public class EditTextLinearLayout  extends LinearLayout {

    Context context;
    public EditTextLinearLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context=context;
        setFocusableInTouchMode(true);
    }

    @Override

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = ((Activity)context).getCurrentFocus();
            isShouldHideInput(v,ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void isShouldHideInput(View v,MotionEvent event) {
        v.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            boolean isFocusChange = false;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFocusChange = false;
                } else {
                    v.clearFocus();
                    isFocusChange = true;

                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(imm !=null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                    }

                }
            }
        });
    }
}

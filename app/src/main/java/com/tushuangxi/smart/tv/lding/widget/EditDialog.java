package com.tushuangxi.smart.tv.lding.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tushuangxi.smart.tv.R;

/**

 **/
public class EditDialog extends Dialog implements View.OnClickListener {


    public EditDialog(@NonNull Context context) {
        super(context);
        initDilaog();
    }

    public EditDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initDilaog();
    }

    protected EditDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initDilaog();
    }

    void initDilaog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        findViewById(R.id.bt_close).setOnClickListener(this);
        findViewById(R.id.bt_commite).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_close:

                break;

             case R.id.bt_commite:

                break;

        }
    }
}

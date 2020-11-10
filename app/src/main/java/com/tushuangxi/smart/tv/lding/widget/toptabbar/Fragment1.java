package com.tushuangxi.smart.tv.lding.widget.toptabbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.tushuangxi.smart.tv.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

/**
 * Created by Carson_Ho on 16/7/22.
 * 版本信息
 */
public class Fragment1 extends Fragment {

    String TAG = "TAG: "+ Fragment1.class.getSimpleName()+"....";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        TextView tv_termValidity = view.findViewById(R.id.tv_termValidity);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

}

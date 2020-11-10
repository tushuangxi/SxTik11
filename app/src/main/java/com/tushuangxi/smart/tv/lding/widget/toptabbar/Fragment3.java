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
 *
 * 客户服务
 */
public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        TextView tv_companyName = view.findViewById(R.id.tv_companyName);

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

}

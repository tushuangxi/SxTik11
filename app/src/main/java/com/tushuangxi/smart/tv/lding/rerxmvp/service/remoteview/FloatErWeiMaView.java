package com.tushuangxi.smart.tv.lding.rerxmvp.service.remoteview;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.eventbus.EventCode;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.http.ApiConstants;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview.AbsMeanager;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.library.nettyclient.netty.EchoClientHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description：全局悬浮窗口
 */

public class FloatErWeiMaView extends RelativeLayout {

    private Context context;
    private WindowManager.LayoutParams wmParams;
    private WindowManager winManager;
    private LinearLayout layout;
    private TextView tv_registerNum;
    private ImageView iv_creat_erweima,iv_erweimaClose;
    View mFloatingLayout;

    //开始触控的坐标，移动时的坐标（相对于屏幕左上角的坐标）
    private int mTouchStartX, mTouchStartY, mTouchCurrentX, mTouchCurrentY;
    //开始时的坐标和结束时的坐标（相对于自身控件的坐标）
    private int mStartX, mStartY, mStopX, mStopY;
    //判断悬浮窗口是否移动，这里做个标记，防止移动后松手触发了点击事件
    private boolean isMove;

    public FloatErWeiMaView(Context context, WindowManager.LayoutParams wmParams, WindowManager winManager) {
        super(context);
        this.context = context;
        this.wmParams = wmParams;
        this.winManager = winManager;
        mFloatingLayout = View.inflate(context, R.layout.remoteview, this);

        initView();
    }

    private void initView() {
        EventBus.getDefault().register(this);
        layout = findViewById(R.id.line1);
        tv_registerNum = findViewById(R.id.tv_registerNum);
        iv_creat_erweima = findViewById(R.id.iv_creat_erweima);
        iv_erweimaClose = findViewById(R.id.iv_erweimaClose);

        //必须带上setOnClickListener   否则无法拖动
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        layout.setOnTouchListener(new FloatingListener());

        String floatId = SpfsUtils.readString(context, AppGlobalConsts.FloatId);//悬浮活动id
        String floatQrCodeUrl = SpfsUtils.readString(context, AppGlobalConsts.FloatQrCodeUrl);//悬浮二维码
        //活动状态查询结果
        if (!TextUtils.isEmpty(floatId)){
            iv_creat_erweima.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load( ApiConstants.HOST_HENGYUANIOT+floatQrCodeUrl+"")
                    .into(iv_creat_erweima);
            if (!TextUtils.isEmpty(EchoClientHandler.msgStr)){
                tv_registerNum.setText("在线学员: "+EchoClientHandler.msgStr+"人");
            }else {
                tv_registerNum.setText("在线学员: 0人");
            }
        }
        iv_creat_erweima.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AbsMeanager.getInstance().startBigErWerMaFloat();
                AbsMeanager.getInstance().stopErWerMaFloat();
            }
        });
        iv_erweimaClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AbsMeanager.getInstance().stopErWerMaFloat();
            }
        });
    }

    //粘性事件  处理消息
    @Subscribe(threadMode= ThreadMode.MAIN, sticky=false)
    public void myEventBusMessage(EventMessage eventMessage){
        switch (eventMessage.getCode()) {

            default:
        }
    }

    private class FloatingListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    isMove = false;
                    mTouchStartX = (int) event.getRawX();
                    mTouchStartY = (int) event.getRawY();
                    mStartX = (int) event.getX();
                    mStartY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mTouchCurrentX = (int) event.getRawX();
                    mTouchCurrentY = (int) event.getRawY();
                    wmParams.x += mTouchCurrentX - mTouchStartX;
                    wmParams.y += mTouchCurrentY - mTouchStartY;
                    winManager.updateViewLayout(mFloatingLayout, wmParams);
                    mTouchStartX = mTouchCurrentX;
                    mTouchStartY = mTouchCurrentY;
                    break;
                case MotionEvent.ACTION_UP:
                    mStopX = (int) event.getX();
                    mStopY = (int) event.getY();
                    if (Math.abs(mStartX - mStopX) >= 1 || Math.abs(mStartY - mStopY) >= 1) {
                        isMove = true;
                    }
                    break;
                default:
                    break;
            }
            //如果是移动事件不触发OnClick事件，防止移动的时候一放手形成点击事件
            return isMove;
        }
    }
}

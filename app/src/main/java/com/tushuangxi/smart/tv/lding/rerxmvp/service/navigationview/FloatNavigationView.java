package com.tushuangxi.smart.tv.lding.rerxmvp.service.navigationview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.eventbus.EventCode;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.utils.ActivityUtils;
import com.tushuangxi.smart.tv.lding.utils.DoubleClickHelper;
import com.tushuangxi.smart.tv.lding.utils.JumpUI;
import com.tushuangxi.smart.tv.lding.utils.TipUtil;
import org.greenrobot.eventbus.EventBus;

/**
 * Description：全局悬浮窗口
 */

public class FloatNavigationView extends RelativeLayout{

    private RelativeLayout layout;
    //记录菜单的打开状态
    boolean isLevel2Open = false;
    //第二层
    RelativeLayout level2;
    LinearLayout ll_bianji;
    LinearLayout ll_signout;
    LinearLayout ll_tianjia;
    LinearLayout ll_shanchu;
    LinearLayout ll_signout_delete;

    private Context context;
    private String floatId;

    View view;
    public FloatNavigationView(Context context, WindowManager.LayoutParams wmParams, WindowManager wm) {
        super(context);
        this.context = context;
        this.wmParams = wmParams;
        this.wm = wm;
        // 将自定义组合控件的布局渲染成View

        view = View.inflate(context, R.layout.float_scan_view, this);
        layout = findViewById(R.id.line1);
        level2 = view.findViewById(R.id.root_level_2);
        ll_bianji = view.findViewById(R.id.ll_bianji);
        ll_signout = view.findViewById(R.id.ll_signout);
        ll_tianjia = view.findViewById(R.id.ll_tianjia);
        ll_shanchu = view.findViewById(R.id.ll_shanchu);
        ll_signout_delete = view.findViewById(R.id.ll_signout_delete);

        //必须带上setOnClickListener   否则无法拖动
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        layout.setOnTouchListener(new FloatingListener());

        //第一层按钮
        view.findViewById(R.id.ib_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityUtils.getActivityStack().size()==1){
                    if (DoubleClickHelper.isOnDoubleClick()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActivityUtils.currentActivity();
                                ActivityUtils.finishActivity();
                            }
                        }, 300);
                    } else {
                        TipUtil.newThreadToast("再按一次退出");
                    }
                }else if (ActivityUtils.getActivityStack().size()==2){
                    level2.setVisibility(View.INVISIBLE);
                    isLevel2Open = false;
                    ActivityUtils.currentActivity();
                    ActivityUtils.finishActivity();
                } else{
                    WebView webView = ActivityUtils.currentActivity().findViewById(R.id.webView);
                    if (null!=webView){
                        if(webView.canGoBack()){
                            webView.goBack();
                        }else {
                            //点击后退 检查 service是否存在  没有就启动  防止退出悬浮
                            AbsMeanager.getInstance().startFloatingAnimation();
                            level2.setVisibility(View.INVISIBLE);
                            isLevel2Open = false;
                            ActivityUtils.currentActivity();
                            ActivityUtils.finishActivity();

                            //重置 悬浮默认状态
                            ll_bianji.setVisibility(VISIBLE);
                            ll_signout.setVisibility(INVISIBLE);
                            ll_shanchu.setVisibility(VISIBLE);
                            ll_signout_delete.setVisibility(INVISIBLE);
                        }
                    }else {
                        //点击后退 检查 service是否存在  没有就启动  防止退出悬浮
                        AbsMeanager.getInstance().startFloatingAnimation();
                        level2.setVisibility(View.INVISIBLE);
                        isLevel2Open = false;
                        ActivityUtils.currentActivity();
                        ActivityUtils.finishActivity();

                        //重置 悬浮默认状态
                        ll_bianji.setVisibility(VISIBLE);
                        ll_signout.setVisibility(INVISIBLE);
                        ll_shanchu.setVisibility(VISIBLE);
                        ll_signout_delete.setVisibility(INVISIBLE);
                    }
                 }
            }
        });

        //第一层按钮 首页
        view.findViewById(R.id.ib_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityUtils.getActivityStack().size()==1){
                    ActivityUtils.currentActivity();
                    ActivityUtils.finishActivity();
                }else{
                    JumpUI.goInitActivity();
                }
            }
        });

        //第一层按钮 更多
        view.findViewById(R.id.ib_gengduo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重置 悬浮默认状态
                ll_bianji.setVisibility(VISIBLE);
                ll_signout.setVisibility(INVISIBLE);
                ll_shanchu.setVisibility(VISIBLE);
                ll_signout_delete.setVisibility(INVISIBLE);

                if (isLevel2Open){
                    if (isLevel2Open){
                        closeLevel(level2,0);
                        isLevel2Open = false;
                    }else{
                        openLevel(level2, 0);
                        level2.setVisibility(VISIBLE);
                    }
                }else {
                    openLevel(level2, 0);
                    level2.setVisibility(VISIBLE);
                    isLevel2Open = true;
                }
            }
        });


        //第二层按钮  添加
        view.findViewById(R.id.ib_tianjia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_ADD_DATA));

            }
        });

        //第二层按钮 编辑
        view.findViewById(R.id.ib_bianji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_bianji.setVisibility(INVISIBLE);
                ll_signout.setVisibility(VISIBLE);
//                EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_SET_DATA_));
            }
        });

        //第二层按钮  退出编辑
        view.findViewById(R.id.ib_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_bianji.setVisibility(VISIBLE);
                ll_signout.setVisibility(INVISIBLE);
//                EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_SIGNOUT_DATA_));
            }
        });

        //第二层按钮  删除
        view.findViewById(R.id.ib_shanchu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_shanchu.setVisibility(INVISIBLE);
                ll_signout_delete.setVisibility(VISIBLE);
//                EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_DELETE_DATA_));

            }
        });
        //第二层按钮  退出删除
        view.findViewById(R.id.ib_signout_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_shanchu.setVisibility(VISIBLE);
                ll_signout_delete.setVisibility(INVISIBLE);
//                EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_DELETEOUT_DATA_));
            }
        });
    }
//---------------------------------------------------------------------------------------------------------------
    /**
     * 打开动画
     * @param view
     */
    private void openLevel(View view, long delay){
        //创建旋转动画
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "Rotation", 180, 0);
        //设置旋转点
        view.setPivotX(view.getWidth());
        view.setPivotY(view.getHeight()>> 1 );
        //设置动画时间
        rotation.setDuration(300);
        //设置动画延迟时间
        rotation.setStartDelay(delay);
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotation.start();
        Log.d("AAAAA","openLevel...");
    }

    /**
     * 关闭动画
     * @param view
     * @param delay
     */
    private void closeLevel(View view, long delay){
        //创建旋转动画
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "Rotation", 0, 180);
        //设置旋转点
        view.setPivotX(view.getWidth() );
        view.setPivotY(view.getHeight()>> 1);
        //设置动画时间
        rotation.setDuration(300);
        //设置动画延迟时间
        rotation.setStartDelay(delay);
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotation.start();
        Log.d("AAAAA","closeLevel...");
    }


    private WindowManager.LayoutParams wmParams;
    private WindowManager wm;

    //开始触控的坐标，移动时的坐标（相对于屏幕左上角的坐标）
    private int mTouchStartX, mTouchStartY, mTouchCurrentX, mTouchCurrentY;
    //开始时的坐标和结束时的坐标（相对于自身控件的坐标）
    private int mStartX, mStartY, mStopX, mStopY;
    //判断悬浮窗口是否移动，这里做个标记，防止移动后松手触发了点击事件
    private boolean isMove;
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
                case MotionEvent.ACTION_MOVE://不允许左右滑动  去掉x方向
//                    mTouchCurrentX = (int) event.getRawX();
                    mTouchCurrentY = (int) event.getRawY();
//                    wmParams.x += mTouchCurrentX - mTouchStartX;
                    wmParams.y += mTouchCurrentY - mTouchStartY;
                    wm.updateViewLayout(view, wmParams);
//                    mTouchStartX = mTouchCurrentX;
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

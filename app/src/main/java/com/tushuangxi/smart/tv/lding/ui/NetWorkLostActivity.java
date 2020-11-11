package com.tushuangxi.smart.tv.lding.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tushuangxi.smart.tv.R;
import com.tushuangxi.smart.tv.lding.event.eventclent.Event;
import com.tushuangxi.smart.tv.lding.event.eventclent.EventClientHelper;
import com.tushuangxi.smart.tv.lding.event.eventclent.IEventClientListener;
import com.tushuangxi.smart.tv.lding.event.eventclent.EventClientMessage;
import com.tushuangxi.smart.tv.lding.event.synclent.DataSynCode;
import com.tushuangxi.smart.tv.lding.event.synclent.DataSynManager;
import com.tushuangxi.smart.tv.lding.event.synclent.DataSynMessage;
import com.tushuangxi.smart.tv.lding.event.synclent.IDataSynListener;
import com.tushuangxi.smart.tv.lding.eventbus.EventMessage;
import com.tushuangxi.smart.tv.lding.rerxmvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/*
android:launchMode="singleTask"    模式
 */
public class NetWorkLostActivity extends BaseActivity implements IEventClientListener,IDataSynListener{

    @BindView(R.id.rl_netlost)
    RelativeLayout rl_netlost;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.btn_commit)
    Button btn_commit;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.study_networklost;
    }

    @Override
    protected View getContentViewLayoutView() {
        return null;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionType() {
        return TransitionMode.FADE;
    }

    @Override
    protected boolean isBindEventBus() {
        return true;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isResultOK() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingAnimationService() {
        return false;
    }

    @Override
    protected boolean isOpenFloatingErWerMaService() {
        return false;
    }

    @Override
    public void initBundleData(Bundle bundle) {

    }

    EventClientHelper eventClientHelper;
    @Override
    public void initView() {

        addOnClickListeners(R.id.iv_close
                , R.id.btn_commit
        );
        //方式一   传递消息
        initEventClientMessage();

        //方式二  消息总线
        initDataSynEventMessage();
    }

    private void initEventClientMessage() {
        EventClientHelper.getInstance().registerEventListener(this);
        EventClientHelper.getInstance().notifyEvent(new EventClientMessage(Event.EVENT_DATA));
    }

    private void initDataSynEventMessage() {
        DataSynManager.getInstance().registerDataSynListener(this);
        DataSynManager.getInstance().onDataSynEventMessage(new DataSynMessage(DataSynCode.SYN_CODE_MESSAGE_DATA));
    }


    @Override
    public void getHttpData(Context context) {

    }

    //粘性事件  处理消息
    @Subscribe(threadMode= ThreadMode.MAIN, sticky=false)
    public void myEventBusMessage(EventMessage eventMessage){

    }


    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.btn_commit:
                finish();
                break;

            default:
        }
    }

     //eventClientHelper  接受消息
    @Override
    public boolean onEvent(EventClientMessage event) {
        switch (event.getEvent()) {
            case Event.EVENT_DATA:

                break;

                default:

        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除监听
        eventClientHelper.unregisterEventListener(this);
        DataSynManager.getInstance().unRegisterDataSynListener(this);
    }


    @Override
    public void onDataSynEvent(DataSynMessage dataSynMessage) {
        switch (dataSynMessage.getEvent()) {
            case DataSynCode.SYN_CODE_MESSAGE_DATA:

                break;

            default:

        }
    }
}

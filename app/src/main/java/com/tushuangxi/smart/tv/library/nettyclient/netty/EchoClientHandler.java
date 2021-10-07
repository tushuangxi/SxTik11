package com.tushuangxi.smart.tv.library.nettyclient.netty;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.tushuangxi.smart.tv.lding.other.AppGlobalConsts;
import com.tushuangxi.smart.tv.lding.rerxmvp.service.remoteview.FloatWinfowErWeiMaServices;
import com.tushuangxi.smart.tv.lding.ui.PartyActivity;
import com.tushuangxi.smart.tv.lding.utils.SpfsUtils;
import com.tushuangxi.smart.tv.library.loading.conn.LoadingApp;
import com.tushuangxi.smart.tv.library.nettyclient.constant.CmdCode;
import com.tushuangxi.smart.tv.library.nettyclient.entry.LdingPacket;
import com.tushuangxi.smart.tv.library.nettyclient.utils.ControllerTask;
import com.tushuangxi.smart.tv.library.nettyclient.utils.LinkInfoUtil;
import com.tushuangxi.smart.tv.library.nettyclient.utils.OrgActivityLog;
import com.vise.log.ViseLog;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import static com.tushuangxi.smart.tv.library.nettyclient.constant.ModuleCode.MODULE_DEVICE;


/** 长连接数据业务处理
 * @describe
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<Object> {

    String TAG = "TAG: "+ EchoClientHandler.class.getSimpleName()+"....";
    String host;
    int port;

    OnMessageListener call;
    public EchoClientHandler(OnMessageListener call) {
        host = LinkInfoUtil.nettyAddrs;
        port = LinkInfoUtil.nettyPort;
        this.call = call;
    }

    /**
     * 向外部暴露一个接口
     */
    public interface OnMessageListener {
        //
        void onMessage(ChannelHandlerContext type, int sign, String result);
        void onMessageObject(ChannelHandlerContext type, int sign, Object msg);
        void onMessageData(ChannelHandlerContext type, int sign, LdingPacket packet);
    }
    public OnMessageListener onMessageListener;


    //客户端连接服务器 标志成功
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        call.onMessageObject(ctx, Config.TCP_CONN_SUCCESS, "连接成功");

        String floatId = SpfsUtils.readString(LoadingApp.getContext(), AppGlobalConsts.FloatId);//悬浮活动id
        if (!TextUtils.isEmpty(floatId)){
            OrgActivityLog orgActivityLog = new OrgActivityLog();
            orgActivityLog.setId(Long.parseLong(floatId));
            Gson gson = new Gson();
            String jsonString = gson.toJson(orgActivityLog);
            ctx.writeAndFlush(Unpooled.copiedBuffer(jsonString, CharsetUtil.UTF_8));
        }
        ViseLog.w("Netty..连接成功");
    }

    //从服务器接收到数据后调用
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String result) throws Exception {
//        call.onMessage(ctx, Config.TCP_RECEIVE, result);
//    }

    //从服务器接收到数据后调用
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, LdingPacket packet) throws Exception {
//        call.onMessageData(ctx, Config.TCP_RECEIVE, packet);
//
//        try {
//            //处理数据包
//            handlePacket(packet);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //簽到人數
   public static String msgStr;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        call.onMessageObject(ctx, Config.TCP_RECEIVE, msg);
        try {
            //传来的消息包装成字节缓冲区
            msgStr = (String) msg;
            ViseLog.w("NettyClient读取服务端消息:" +msgStr);
//            EventBus.getDefault().postSticky(new EventClientMessage(EventCode.SYN_CODE_NETTY_NUM_DATA,msgStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //发生异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

//        Log.i("LLL", "onExceptionTip " + cause.getMessage());
        Log.e("Netty","onExceptionTip "+ Log.getStackTraceString(cause));
        call.onMessage(ctx,Config.TCP_CONN_Exception_MSG,"异常消息提示,请查看Log");
        // 释放资源
        ctx.close();

        //重连
        PartyActivity.conn(host, port);
        FloatWinfowErWeiMaServices.conn(host, port);
    }
    //客户端断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (onMessageListener != null){
            onMessageListener.onMessage(ctx, Config.TCP_MANUAL_CLOSE, "客户端断开");//
        }
        LdingPacket ldingPacket = new LdingPacket();
        ldingPacket.setModule(MODULE_DEVICE);
//        onMessageListener.onMessageData(ctx, Config.TCP_MANUAL_CLOSE,ldingPacket);//
        call.onMessage(ctx,Config.TCP_CONN_Exception_MSG,"客户端断开");
        if (ctx != null){
            ctx.close();
        }
    }
    /**
     *    为了减少服务器端压力  默认服务器5s没有处理   服务端可以认为客户端挂了
     * @param ctx
     * @param evt
     * @throws
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                Log.i("Netty", "------IdleState.ALL_IDLE------");

                if (Config.COUNT < 1 && Config.TCP_CONN_AGAIN) {
                    Config.COUNT++;
                } else {
                    onMessageListener.onMessage(ctx, Config.TCP_RECONN, "客户端断开");
                    Log.i("Netty", "------客户端断开------");
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    private void handlePacket(LdingPacket packet) {
        final short modular = packet.getModule();
        short cmd = packet.getCmd();
        Log.e("tcy", "modular:" + modular + "cmd:" + cmd);

        cmd=1006;
//        switch (modular) {
        switch (cmd) {
             case CmdCode.CMD_TC_GET_APP_SCREENSHOT: {//截屏
                onReceiveScreenhotModularPacket( cmd, packet.getData());
                break;
             }

             case CmdCode.CMD_TC_RTMPPUSH_OPEN_OF_CLOSE: {// 电梯视频监控
                onReceiveRtmppushModularPacket( cmd, packet.getData());
                break;
             }

            default:
        }
    }

    private void onReceiveScreenhotModularPacket(short cmd, byte[] data) {
        new Thread( new ControllerTask(cmd, data)).start();
    }

    private void onReceiveRtmppushModularPacket(short cmd, byte[] data) {
        new Thread( new ControllerTask(cmd, data)).start();
    }


}

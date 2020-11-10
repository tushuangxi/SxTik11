package com.tushuangxi.smart.tv.lding.rerxmvp.service;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

//保证第三方软件和系统不能杀死的service

public class PermanentService extends Service
{
	AlarmManager mAlarmManager = null;
	PendingIntent mPendingIntent = null;
	
	@SuppressLint("WrongConstant")
	@Override
	public void onCreate()
	{
		//start the service through alarm repeatly
	    Intent intent = new Intent(getApplicationContext(), PermanentService.class);
		mAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		mPendingIntent = PendingIntent.getService(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
		long now = System.currentTimeMillis();
//		用于设置重复闹钟,间隔时间固定死    AlarmManager.RTC硬件闹钟，不唤醒手机（也可能是其它设备）休眠；当手机休眠时不发射闹钟。
		mAlarmManager.setInexactRepeating(AlarmManager.RTC, now, 300, mPendingIntent);
		
		super.onCreate();
	}

//	START_STICKY
//	当Service因内存不足而被系统kill后，一段时间后内存再次空闲时，系统将会尝试重新创建此Service，一旦创建成功后将回调onStartCommand方法，
//	但其中的Intent将是null，除非有挂起的Intent，如pendingintent，这个状态下比较适用于不执行命令、但无限期运行并等待作业的媒体播放器或类似服务。
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Toast.makeText(getApplicationContext(), "Callback Successed!", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public void onDestroy() 
	{
		
		super.onDestroy();
	}
}

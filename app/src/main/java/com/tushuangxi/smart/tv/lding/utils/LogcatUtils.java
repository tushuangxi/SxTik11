package com.tushuangxi.smart.tv.lding.utils;

import android.os.Environment;

import com.tao.admin.loglib.IConfig;
import com.tao.admin.loglib.TLogApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tcy on 2018/1/15.
 */
public class LogcatUtils {
    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getFormatTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 写日志到本地
     */
    public static void exportLogcat(String log) {
        try {
            File file = new File(TLogApplication.getAPP().getFilesDir(), "tlog.log");
            FileWriter fw = null;
            if (file.exists()) {
                if (file.length() > IConfig.getInstance().getFileSize()) {
                    fw = new FileWriter(file, false);
                } else {
                    fw = new FileWriter(file, true);
                }
            } else {
                fw = new FileWriter(file, false);
            }
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("MM-dd HH:mm:ss");
            String dateStr = s.format(d);
            fw.write(String.format("[%s] %s", dateStr, log));
            fw.write(13);
            fw.write(10);
            fw.flush();
            fw.close();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
}

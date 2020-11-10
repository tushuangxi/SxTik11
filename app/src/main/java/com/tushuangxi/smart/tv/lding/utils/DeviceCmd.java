package com.tushuangxi.smart.tv.lding.utils;

import java.io.DataOutputStream;

public class DeviceCmd {

    public static void exuseCmdPerssion(){
        exusecmd("setprop media.hp.switch.black false");
        exusecmd("setprop media.hp.local.switch.black false");
    }
    public static boolean exusecmd(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

package com.tushuangxi.smart.tv.lding.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//智慧屏 读取MAC
public class MacUtils {

//    String getMacAddr()

    public static String getMacAddr() {
        String str = loadFileAsString("/sys/class/net/wlan0/address");
        if (str.isEmpty()) {
            return "";
        }
        return str.toLowerCase().substring(0, 17);
    }

    private static String loadFileAsString(String filePath) {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = bufferedReader.read(buf)) != -1) {
                String readData = new String(buf, 0, numRead);
                fileData.append(readData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileData.toString();
    }
}

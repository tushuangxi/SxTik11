package com.tushuangxi.smart.tv.lding.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 判断SD卡是否挂载
 * 获取SD卡的根目录
 * 获取SD卡总容量
 * 获取SD卡剩余容量
 * 向SD卡9大共有目录保存数据
 * 向SD卡私有File目录写入数据
 * 向SD卡私有Cache目录写入数据
 * 从SD卡中读取数据
 *
 * @Created by changliang on 2017/7/4.
 */
public class SDCardUtils {

    /**
     * 判断SD卡是否挂载
     *
     * @return
     */
    public static boolean isSDCardMounted(){
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 返回SD卡根目录
     *
     * @return
     */
    public static String getSDCardRootDir() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取SD卡总容量
     *
     * @return
     */
    public static long getSDCardTotalSize(){
        if(isSDCardMounted()){
            //StatFs Statistic File System簇
            StatFs sf = new StatFs(getSDCardRootDir());
            int blockSize = sf.getBlockSize();
            int blockCount = sf.getBlockCount();
            //返回MB
            return blockSize * blockCount / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取SD卡可用容量
     *
     * @return
     */
    public static long getSDCardAvailableSize(){
        if(isSDCardMounted()){
            StatFs sf = new StatFs(getSDCardRootDir());
            int availableBlockCount = sf.getAvailableBlocks();
            int blockSize = sf.getBlockSize();

            return availableBlockCount * blockSize / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 向SD卡9大共有目录保存数据
     *
     * @param data 需要保存的数据
     * @param type　区分9大共有目录的type
     * @param fileName 另存为的文件名称
     * @return
     */
    public static boolean saveFileToPublicDirectory(byte[] data, String type, String fileName){
        if(isSDCardMounted()){
            BufferedOutputStream bos = null;
            File fileDir = Environment.getExternalStoragePublicDirectory(type);
            File file = new File(fileDir, fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 向SD卡私有File目录写入数据
     *
     * @param context　上下文,用来找到storage/sdcard0/Android/data/packageName/files
     * @param data 需要保存的数据
     * @param type　文件加类型
     * @param fileName　另存为的文件名名称
     * @return
     */
    public static boolean saveFileToExternalFileDir(Context context, byte[] data, String type, String fileName){
        if(isSDCardMounted()){
            BufferedOutputStream bos = null;
            File fileDir = context.getExternalFilesDir(null);
            File file = new File(fileDir, fileName);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 向SD卡私有Cache目录写入数据
     *
     * @param context　上下文,用来找到storage/sdcard0/Android/data/packageName/cache
     * @param data 需要保存的数据
     * @param fileName　另存为的文件名名称
     * @return
     */
    public static boolean saveFileToExternalCacheDir(Context context, byte[] data, String fileName){
        if(isSDCardMounted()){
            BufferedOutputStream bos = null;
            File fileDir = context.getExternalCacheDir();
            File file = new File(fileDir, fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 从SD卡中读取数据
     *
     * @param fileAbsolutePath　　读取文件的绝对路径
     * @return
     */
    public static byte[] loadDataFromSDCard(String fileAbsolutePath){
        if(isSDCardMounted()) {
            BufferedInputStream bis = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                bis = new BufferedInputStream(new FileInputStream(fileAbsolutePath));
                byte[] buffer = new byte[1024 * 8];
                int len = 0;
                while((len = bis.read(buffer)) != -1){
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if(baos != null) {
                        baos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getSDCardCacheDir(Context context){
        return context.getExternalCacheDir().getPath();
    }
    /**
    * 获取本地存储总容量
    * 
    * @author qhh
    * 2017/8/11
    */
    public static double getMemoryTotalSize(){
        File file = Environment.getExternalStorageDirectory();
        double totalspace = (double) file.getTotalSpace() / 1024 / 1024 / 1024;
        totalspace = Double.parseDouble(formatDecimal(totalspace));
        return totalspace;

    }

    /**
    * 获取本地可用存储
    * 
    * @author qhh
    * 2017/8/11
    */
    public static double getMemoryUseableSize(){
        File file = Environment.getExternalStorageDirectory();
        double tousespace = (double) file.getUsableSpace() / 1024 / 1024 / 1024;
        tousespace = Double.parseDouble(formatDecimal(tousespace));
        return tousespace;

    }
    /**
     * 保留两位小数
     *
     * @author qhh
     * 2017/8/8
     */
    public static  String formatDecimal(double memory) {
        java.math.BigDecimal bigDec = new java.math.BigDecimal(memory);
        double total = bigDec.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(total);
    }

}
package com.tushuangxi.smart.tv.library.mmkv;

import com.tencent.mmkv.MMKV;


/**
 *         KVUtils.getInstance().putString(AppGlobalConsts.Token,"Token3");
 *         if (KVUtils.getInstance().containsKey(AppGlobalConsts.Token)){
 *             KVUtils.getInstance().removeString(AppGlobalConsts.Token);
 *         }
 *         ViseLog.w(TAG,KVUtils.getInstance().getString(AppGlobalConsts.Token));
 *
 *
 *
 * //MMKV.initialize(this); //初始化mmkv
 * //    // 增
 * //mmkv.encode("token", token);
 * //// 删
 * //mmkv.removeValueForKey("token");  //删除单个
 * //mmkv.removeValuesForKeys(new String[]{"name", "token"}); //删除多个
 * ////改 （在执行一次增操作）
 * //mmkv.encode("token", token);
 * ////查
 * //mmkv.decodeString("token");
 *
 * //    //使用默认的实例
 * //    MMKV mmkv1 = MMKV.defaultMMKV();
 * //    //创建自己的实例  参数1：库的key， 参数2：库的模式（多进程或单进程）
 * //    MMKV mmkv2 = MMKV.mmkvWithID("user", MMKV.MULTI_PROCESS_MODE);
 */


public final class KVUtils  {

    private static KVUtils instance = null;
    private static MMKV mmkv = null;;

    public static KVUtils getInstance() {
        mmkv = MMKV.defaultMMKV();
        if (instance == null) {
            synchronized (KVUtils.class) {
                if (instance == null) {// 多线程安全单例模式(使用双重同步锁)
                    instance = new KVUtils();
                }
            }
        }
        return instance;
    }

    public static KVUtils getInstanceWithID(String id) {
        mmkv = MMKV.mmkvWithID(id);
        if (instance == null) {
            synchronized (KVUtils.class) {
                if (instance == null) {// 多线程安全单例模式(使用双重同步锁)
                    instance = new KVUtils();
                }
            }
        }
        return instance;
    }


//-----------------------------------------------------------------
    //String
    //储存String
    public  boolean putString(String key, String value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存String 带id的
    public  boolean putString(String id, String key, String value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出String  没有默认值的
    public  String getString(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeString(key);
    }
//拿出String   defaultValue=默认值
    public  String getString(String key, String defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeString(key, defaultValue);
    }
    //拿出带id的String   defaultValue=默认值
    public  String getString(String id, String key, String defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeString(key, defaultValue);
    }

//int
    //储存int
    public  boolean putint(String key, int value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存int 带id的
    public  boolean putint(String id, String key, int value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出int  没有默认值的
    public  int getint(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeInt(key);
    }
    //拿出int   defaultValue=默认值
    public  int getint(String key, int defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeInt(key, defaultValue);
    }
    //拿出带id的int   defaultValue=默认值
    public  int getint(String id, String key, int defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeInt(key, defaultValue);
    }



    //Boolean
    //储存Boolean
    public  boolean putBoolean(String key, boolean value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存Boolean 带id的
    public  boolean putBoolean(String id, String key, boolean value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出Boolean  没有默认值的
    public  Boolean getBoolean(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBool(key);
    }
    //拿出Boolean   defaultValue=默认值
    public  Boolean getBoolean(String key, boolean defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBool(key, defaultValue);
    }
    //拿出带id的Boolean   defaultValue=默认值
    public  Boolean getBoolean(String id, String key, boolean defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeBool(key, defaultValue);
    }




    //Byte
    //储存Byte
    public static boolean putByte(String key, byte[] value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存Byte 带id的
    public static boolean putByte(String id, String key, byte[] value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出Byte  没有默认值的
    public static byte[] getByte(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBytes(key);
    }
    //拿出Byte   defaultValue=默认值
    public static  byte[] getByte(String key, byte[] defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBytes(key, defaultValue);
    }
    //拿出带id的Byte   defaultValue=默认值
    public static  byte[] getByte(String id, String key, byte[] defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeBytes(key, defaultValue);
    }


    //Double
    //储存Double
    public static boolean putDouble(String key, double value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存Double 带id的
    public static boolean putDouble(String id, String key, double value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出Double   没有默认值的
    public static double getDouble(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeDouble(key);
    }
    //拿出Double    defaultValue=默认值
    public static  double getDouble(String key, double defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeDouble(key, defaultValue);
    }
    //拿出带id的Double    defaultValue=默认值
    public static  double getDouble(String id, String key, double defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeDouble(key, defaultValue);
    }



    //Float
    //储存Float
    public static boolean putFloat(String key, float value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存Float 带id的
    public static boolean putFloat(String id, String key, float value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出Float   没有默认值的
    public static float getFloat(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeFloat(key);
    }
    //Float    defaultValue=默认值
    public static  float getFloat(String key, float defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeFloat(key, defaultValue);
    }
    //拿出带id的Float    defaultValue=默认值
    public static  float getFloat(String id, String key, float defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeFloat(key, defaultValue);
    }




    //Long
    //储存Long
    public static boolean putLong(String key, long value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(key, value);
    }
    //储存Long 带id的
    public static boolean putLong(String id, String key, long value) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.encode(key, value);
    }
    //拿出Long   没有默认值的
    public static float getLong(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeLong(key);
    }
    //Long    defaultValue=默认值
    public static  float getLong(String key, long defaultValue) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeLong(key, defaultValue);
    }
    //拿出带id的Long    defaultValue=默认值
    public static  float getLong(String id, String key, long defaultValue) {
        MMKV mmkv = MMKV.mmkvWithID(id);
        return mmkv.decodeLong(key, defaultValue);
    }



    //删除单个
    public   void removeString(String key) {
        mmkv.removeValueForKey(key);
    }
    //删除多个
    public   void removeMore(String... key) {
        for (int i = 0; i < key.length; i++) {
            mmkv.removeValueForKey(key[i]);
//            mmkv.removeValuesForKeys(new String[]{key[i]});
        }
    }

    //是否存在
    public  boolean  containsKey(String key) {
        return mmkv.containsKey(key);
    }

}

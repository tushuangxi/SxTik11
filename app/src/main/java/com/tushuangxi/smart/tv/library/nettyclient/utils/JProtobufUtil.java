package com.tushuangxi.smart.tv.library.nettyclient.utils;


import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

public class JProtobufUtil {

    public static <T> T decode(byte[] datas, Class<T> clazz){
        if (datas == null || datas.length == 0) {
            return null;
        }
        try {
            Codec<T> tCodec = ProtobufProxy.create(clazz);
            // 反序列化
            return tCodec.decode(datas);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> byte[] encode(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            Codec<T> tCodec = ProtobufProxy.create((Class<T>)obj.getClass());
            // 序列化
            return tCodec.encode(obj);
        } catch (Exception e) {
            return null;
        }
    }
}

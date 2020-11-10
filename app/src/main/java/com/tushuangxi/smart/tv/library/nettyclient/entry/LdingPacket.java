package com.tushuangxi.smart.tv.library.nettyclient.entry;


/**
 * 消息包
 *
 * @author changliang
 *
 */
public class LdingPacket {
    //模块号
    private short module;
    //命令号
    private short cmd;
    //数据
    private byte[] data;

    public static LdingPacket valueOf(short module, short cmd, byte[] data){
        LdingPacket packet = new LdingPacket();
        packet.setModule(module);
        packet.setCmd(cmd);
        packet.setData(data);
        return packet;
    }

    public byte[] getData() {
        if (data == null) {
            return new byte[0];
        }
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }
}

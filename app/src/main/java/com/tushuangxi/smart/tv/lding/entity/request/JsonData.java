package com.tushuangxi.smart.tv.lding.entity.request;

public class JsonData {

  private  int pageNum ;
  private  int pageSize ;
  private  String deviceIdentity ;


    public String getDeviceIdentity() {
        return deviceIdentity;
    }

    public void setDeviceIdentity(String deviceIdentity) {
        this.deviceIdentity = deviceIdentity;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

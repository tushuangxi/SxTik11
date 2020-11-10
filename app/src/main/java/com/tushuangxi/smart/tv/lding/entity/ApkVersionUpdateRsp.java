package com.tushuangxi.smart.tv.lding.entity;

public class ApkVersionUpdateRsp {


    /**
     * code : 0
     * ext : {}
     * message : string
     * result : {"gmtCreate":"2020-09-10T03:43:17.926Z","gmtModified":"2020-09-10T03:43:17.927Z","id":0,"isDeleted":0,"url":"string","versionNumber":0}
     */

    private int code;
    private ExtBean ext;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ExtBean {
    }

    public static class ResultBean {
        /**
         * gmtCreate : 2020-09-10T03:43:17.926Z
         * gmtModified : 2020-09-10T03:43:17.927Z
         * id : 0
         * isDeleted : 0
         * url : string
         * versionNumber : 0
         */

        private String gmtCreate;
        private String gmtModified;
        private int id;
        private int isDeleted;
        private String url;
        private int versionNumber;

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(int versionNumber) {
            this.versionNumber = versionNumber;
        }
    }
}

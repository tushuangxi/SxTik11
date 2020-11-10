package com.tushuangxi.smart.tv.lding.entity;

import java.io.Serializable;
import java.util.List;

public class SiteNavigationRsp implements Serializable {

    /**
     * code : 0
     * ext : {}
     * message : string
     * result : {"endRow":0,"firstPage":0,"hasNextPage":true,"hasPreviousPage":true,"isFirstPage":true,"isLastPage":true,"lastPage":0,"list":[{"createBy":0,"gmtCreate":"2020-08-28T07:23:03.552Z","gmtModified":"2020-08-28T07:23:03.552Z","id":0,"image":"string","isDeleted":0,"modifiedBy":0,"name":"string","orgId":0,"url":"string"}],"navigateFirstPage":0,"navigateLastPage":0,"navigatePages":0,"navigatepageNums":[0],"nextPage":0,"pageNum":0,"pageSize":0,"pages":0,"prePage":0,"size":0,"startRow":0,"total":0}
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

    public static class ResultBean implements Serializable{
        /**
         * endRow : 0
         * firstPage : 0
         * hasNextPage : true
         * hasPreviousPage : true
         * isFirstPage : true
         * isLastPage : true
         * lastPage : 0
         * list : [{"createBy":0,"gmtCreate":"2020-08-28T07:23:03.552Z","gmtModified":"2020-08-28T07:23:03.552Z","id":0,"image":"string","isDeleted":0,"modifiedBy":0,"name":"string","orgId":0,"url":"string"}]
         * navigateFirstPage : 0
         * navigateLastPage : 0
         * navigatePages : 0
         * navigatepageNums : [0]
         * nextPage : 0
         * pageNum : 0
         * pageSize : 0
         * pages : 0
         * prePage : 0
         * size : 0
         * startRow : 0
         * total : 0
         */

        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
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

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable{
            /**
             * createBy : 0
             * gmtCreate : 2020-08-28T07:23:03.552Z
             * gmtModified : 2020-08-28T07:23:03.552Z
             * id : 0
             * image : string
             * isDeleted : 0
             * modifiedBy : 0
             * name : string
             * orgId : 0
             * url : string
             */

            private int createBy;
            private String gmtCreate;
            private String gmtModified;
            private int id;
            private String image;
            private int isDeleted;
            private int modifiedBy;
            private String name;
            private int orgId;
            private String url;

            public int getCreateBy() {
                return createBy;
            }

            public void setCreateBy(int createBy) {
                this.createBy = createBy;
            }

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public int getModifiedBy() {
                return modifiedBy;
            }

            public void setModifiedBy(int modifiedBy) {
                this.modifiedBy = modifiedBy;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrgId() {
                return orgId;
            }

            public void setOrgId(int orgId) {
                this.orgId = orgId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

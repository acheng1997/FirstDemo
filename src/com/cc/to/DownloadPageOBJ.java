package com.cc.to;

import com.cc.pojo.MyFile;

import java.util.List;

public class DownloadPageOBJ {
    //总页面数
    private Integer pageNumber;
    //当前页码
    private Integer pageIndex;
    //当前页面的数据，一条是myFiles对象保存，所以多条为list链表
    private List<MyFile> myFiles;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<MyFile> getMyFiles() {
        return myFiles;
    }

    public void setMyFiles(List<MyFile> myFiles) {
        this.myFiles = myFiles;
    }
//  写一个添加方法
    public void addMyFiles(MyFile myFile) {
        this.myFiles.add(myFile);
    }
}

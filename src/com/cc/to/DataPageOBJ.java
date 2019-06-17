package com.cc.to;

import com.cc.pojo.Staff;

import java.util.ArrayList;
import java.util.List;

public class DataPageOBJ {
    //总页面数
    private Integer pageNumber;
    //当前页码
    private Integer pageIndex;
    //当前页面的数据，一条是staff对象保存，所以多条为list链表
    private List<Staff> staffs;

    public DataPageOBJ() {
        this.staffs = new ArrayList<>();
    }

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

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    //写一个添加方法
    public void addStaffs(Staff staff) {
        this.staffs.add(staff);
    }
}

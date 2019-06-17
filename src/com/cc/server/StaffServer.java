package com.cc.server;

import com.cc.dao.StaffDao;
import com.cc.pojo.Staff;
import com.cc.pojo.User;
import com.cc.to.DataPageOBJ;

import java.sql.SQLException;
import java.util.List;

public class StaffServer {
    private StaffDao staffDao;

    public StaffServer() {
        this.staffDao = new StaffDao();
    }

    //    先得到总页数
    public int getPageNumber(int number) {
        long count = 0;
        try {
            count = staffDao.getCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (int) (count / (number * 1.0) % 1 == 0 ? count / number : count / number + 1);
    }


    //    再查询后得到的数据进行封装，当前显示的页码，和当页显示的数据，还有总页数
//    封装需要实体类保存但是跟数据库没有直接关系，所以写到to层
    public DataPageOBJ paging(int pageIndex, int number) {
//        总页码
        int count = getPageNumber(number);
//        查询当前页数据
        List<Staff> staffs = staffDao.paging(pageIndex, number);
//        封装
        DataPageOBJ dataPageOBJ = new DataPageOBJ();
//        总页码
        dataPageOBJ.setPageNumber(count);
//        当前页码
        dataPageOBJ.setPageIndex(pageIndex);
//        当前数据
        dataPageOBJ.setStaffs(staffs);
        return dataPageOBJ;
    }

    //    职工资料修改
    public boolean updateStaff(Staff staff) {
//        判断id是否为空
        if (staff.getId() == null) {
//        为空的时候则是添加
            return staffDao.insertStaff(staff);
        } else {
//        不为空的时候则是修改
            return staffDao.updateStaff(staff);
        }
    }

    //  职工资料删除
    public boolean deleteById(int id) {
        return staffDao.deleteById(id);
    }

    //根据id查询数据
    public Staff selectById(int id) {
        return staffDao.selectById(id);
    }


}

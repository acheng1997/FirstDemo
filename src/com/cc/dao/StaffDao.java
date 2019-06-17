package com.cc.dao;

import com.cc.pojo.Staff;
import com.cc.pojo.User;
import com.cc.utils.DBQueryUtil;
import com.cc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private Connection connection;

    public StaffDao() {
        connection = DBUtil.getConnection();

    }

    //    得到总数据，返回
    public long getCount() throws SQLException {
        long count = 0;
        //1.写sql语句
        String sql = "SELECT COUNT(id) count FROM staff";
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            //2.得到操作数据库的句柄
            ps = connection.prepareStatement(sql);
//            执行操作
            set = ps.executeQuery();
            while (set.next()) {
//                得到结果，进行保存
                count = set.getLong("count");
            }
        } finally {
            if (set != null) {
                set.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return count;
    }

    //    写一个分页查询，返回数据链表,分页需要两个参数，一个是第几页，一个是数量
    public List<Staff> paging(int pageIndex, int number) {
        String sql = "SELECT * FROM staff LIMIT ?,?";
        List<Staff> staffs = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (pageIndex - 1) * number);
            preparedStatement.setInt(2, number);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setId(resultSet.getInt("ID"));
                staff.setName(resultSet.getString("NAME"));
                staff.setSex(resultSet.getString("SEX"));
                staff.setBasePay(resultSet.getString("BASE_PAY"));
                staff.setRoyalty(resultSet.getString("ROYALTY"));
                staff.setDepartment(resultSet.getString("DEPARTMENT"));
                staff.setPost(resultSet.getString("POST"));
                staff.setPhone(resultSet.getString("PHONE"));
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return staffs;
    }

    //   增加，添加
    public boolean insertStaff(Staff staff) {
        String sql = "INSERT staff(NAME,SEX,BASE_PAY,ROYALTY,DEPARTMENT,POST,PHONE) VALUES(?,?,?,?,?,?,?)";
        //		句柄
        PreparedStatement pst = null;
        try {
//			得到预编译句柄
            pst = connection.prepareStatement(sql);
//			设置参数
            pst.setString(1, staff.getName());
            pst.setString(2, staff.getSex());
            pst.setString(3, staff.getBasePay());
            pst.setString(4, staff.getRoyalty());
            pst.setString(5, staff.getDepartment());
            pst.setString(6, staff.getPost());
            pst.setString(7, staff.getPhone());
//			执行sql语句
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //    修改
    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET NAME = ?,SEX = ?,BASE_PAY = ?,ROYALTY = ?,DEPARTMENT = ?,POST = ?,PHONE = ? WHERE ID = ?";
        PreparedStatement pst = null;
        try {
//			得到预编译句柄
            pst = connection.prepareStatement(sql);
//			设置参数
            pst.setString(1, staff.getName());
            pst.setString(2, staff.getSex());
            pst.setString(3, staff.getBasePay());
            pst.setString(4, staff.getRoyalty());
            pst.setString(5, staff.getDepartment());
            pst.setString(6, staff.getPost());
            pst.setString(7, staff.getPhone());
            pst.setInt(8, staff.getId());
//			执行sql语句
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //    删除
    public boolean deleteById(int id) {
        String sql = "DELETE FROM staff WHERE ID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //    根据id查询数据
    public Staff selectById(int id) {
        String sql = "SELECT * FROM staff WHERE id = ?";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        List<Staff> staffs = new ArrayList<>();
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setName(resultSet.getString("NAME"));
                staff.setSex(resultSet.getString("SEX"));
                staff.setBasePay(resultSet.getString("BASE_PAY"));
                staff.setRoyalty(resultSet.getString("ROYALTY"));
                staff.setDepartment(resultSet.getString("DEPARTMENT"));
                staff.setPost(resultSet.getString("POST"));
                staff.setPhone(resultSet.getString("PHONE"));
                staffs.add(staff);
            }
            return staffs.size() > 0 ? staffs.get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                resultSet.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

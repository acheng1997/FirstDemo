package com.cc.dao;

import com.cc.pojo.User;
import com.cc.utils.DBUtil;

import java.sql.*;


public class UserDao {

    private Connection connection;

    //	new此对象时候就连接上
    public UserDao() {
//		调用DBUtil类的得到连接方法
        connection = DBUtil.getConnection();
    }

    //添加用户
    public boolean insertUser(User user) {
        String sql = "INSERT user(USER_NAME,NICK_NAME,PASSWORD,PHONE,MAIL) VALUES(?,?,?,?,?)";
        //		句柄
        PreparedStatement pst = null;
        try {
//			得到预编译句柄
            pst = connection.prepareStatement(sql);
//			设置参数
            pst.setString(1, user.getName());

            pst.setString(2, user.getNickName());

            pst.setString(3, user.getPassword());

            pst.setString(4, user.getPhone());

            pst.setString(5, user.getEmail());

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

    //    查询登录用户
    public User selectUser(String username) {
        String sql = "SELECT * FROM user WHERE USER_NAME = ?";
        PreparedStatement pst = null;
//		结果集
        ResultSet rset = null;
//		查询的结果需要一个类对象来保存
        User user = null;
        try {
//			得到句柄,预编译句柄
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
//			执行sql语句后得到的结果用结果集来保存
            rset = pst.executeQuery();
//			循环读取每一行
            while (rset.next()) {
//				如果有数据则 new一个类对象,然后对应添加属性值
                user = new User();
                user.setId(rset.getInt("ID"));
                user.setName(rset.getString("USER_NAME"));
                user.setNickName(rset.getString("NICK_NAME"));
                user.setPassword(rset.getString("PASSWORD"));
                user.setPhone(rset.getString("PHONE"));
                user.setEmail(rset.getString("MAIL"));
                user.setLastTime(rset.getString("LAST_TIME"));
                user.setNowTime(rset.getString("NOW_TIME"));
                user.setProfilePhoto(rset.getString("PROFILE_PHOTO"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rset.close();
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//		返回结果,类对象
        return user;
    }

    //     修改时间
    public void updateTime(User user) {
        String sql = "UPDATE user SET LAST_TIME = ?,NOW_TIME=? WHERE ID = ?";
        PreparedStatement pst = null;
        try {
//            预编译句柄
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getLastTime());
            pst.setString(2, user.getNowTime());
            pst.setInt(3, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //    根据id修改头像路径
    public boolean updateProfilePhotoById(User user) {
        String sql = "UPDATE user SET PROFILE_PHOTO = ? WHERE ID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getProfilePhoto());
            pst.setInt(2, user.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //    修改密码
    public boolean updatePassword(int id, String password) {
        String sql = "UPDATE user SET PASSWORD = ? WHERE ID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, password);
            pst.setInt(2, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//    修改昵称
    public boolean updateNickNameById(User user){
        String sql = "UPDATE user SET NICK_NAME = ? WHERE ID = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getNickName());
            pst.setInt(2, user.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.cc.dao;

import com.cc.pojo.MyFile;
import com.cc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDao {
    private Connection connection;

    //	new此对象时候就连接上
    public FileDao() {
//		调用DBUtil类的得到连接方法
        connection = DBUtil.getConnection();
    }

    public boolean insertFile(MyFile myFile) {
        String sql = "INSERT file(FILE_PATH,FILE_NAME,USER_NAME,FILE_SIZE,FILE_NUMBER,FILE_DOWNLOAD) VALUES(?,?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
//			得到预编译句柄
            pst = connection.prepareStatement(sql);
//			设置参数
            pst.setString(1, myFile.getFilePath());
            pst.setString(2, myFile.getFileName());
            pst.setString(3, myFile.getUserName());
            pst.setString(4, myFile.getFileSize());
            pst.setInt(5, myFile.getFileNumber());
            pst.setString(6, myFile.getFileDownload());
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

    //          先是得到总数量
    public long getCount() throws SQLException {
        long count = 0;
        //1.写sql语句
        String sql = "SELECT COUNT(id) count FROM file";
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
    public List<MyFile> paging(int pageIndex, int number) {
        String sql = "SELECT * FROM file LIMIT ?,?";
        List<MyFile> myFiles = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (pageIndex - 1) * number);
            preparedStatement.setInt(2, number);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MyFile myFile = new MyFile();
                myFile.setId(resultSet.getInt("ID"));
                myFile.setFilePath(resultSet.getString("FILE_PATH"));
                myFile.setFileName(resultSet.getString("FILE_NAME"));
                myFile.setUserName(resultSet.getString("USER_NAME"));
                myFile.setFileSize(resultSet.getString("FILE_SIZE"));
                myFile.setFileNumber(resultSet.getInt("FILE_NUMBER"));
                myFile.setFileDownload(resultSet.getString("FILE_DOWNLOAD"));
                myFiles.add(myFile);
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
        return myFiles;
    }

    //    通过id查到下载次数
    public int numberById(int id) {
        int number = 0;
        String sql = "SELECT FILE_NUMBER FROM file WHERE ID = ?";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                number = resultSet.getInt("FILE_NUMBER");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return number;
    }

    //    修改下载次数
    public void updateNumber(int id, int number) {
        String sql = "UPDATE file SET FILE_NUMBER = ? WHERE ID=? ";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, number);
            pst.setInt(2, id);
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
}

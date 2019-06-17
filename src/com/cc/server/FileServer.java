package com.cc.server;

import com.cc.dao.FileDao;
import com.cc.pojo.MyFile;
import com.cc.to.DownloadPageOBJ;

import java.sql.SQLException;
import java.util.List;

public class FileServer {
    private FileDao fileDao;

    public FileServer() {
        this.fileDao = new FileDao();
    }

    //  添加到数据库
    public boolean insert(MyFile myFile) {
        return fileDao.insertFile(myFile);
    }

    //  先得到总页数
    public int getPageNumber(int number) {
        long count = 0;
        try {
            count = fileDao.getCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (int) (count / (number * 1.0) % 1 == 0 ? count / number : count / number + 1);
    }


    //    再查询后得到的数据进行封装，当前显示的页码，和当页显示的数据，还有总页数
//    封装需要实体类保存但是跟数据库没有直接关系，所以写到to层
    public DownloadPageOBJ paging(int pageIndex, int number) {
//        总页码
        int count = getPageNumber(number);
//        查询当前页数据
        List<MyFile> myFiles = fileDao.paging(pageIndex, number);
//        封装
        DownloadPageOBJ downloadPageOBJ = new DownloadPageOBJ();
//        总页码
        downloadPageOBJ.setPageNumber(count);
//        当前页码
        downloadPageOBJ.setPageIndex(pageIndex);
//        当前数据
        downloadPageOBJ.setMyFiles(myFiles);
        return downloadPageOBJ;
    }

    //  通过id得到下载次数
    public int numberById(int id) {
        return fileDao.numberById(id);
    }
//    修改下载次数
    public void updateNumber(int id,int number){
        fileDao.updateNumber(id,number);
    }
}

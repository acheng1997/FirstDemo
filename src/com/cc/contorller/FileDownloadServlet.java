package com.cc.contorller;

import com.cc.server.FileServer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "fileDownloadServlet", value = "/fileDownload")
public class FileDownloadServlet extends HttpServlet {
    private FileServer fileServer;

    public FileDownloadServlet() {
        this.fileServer = new FileServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String downloadPath = "F:/demo/img/";
        //得到文件名
        String fileName = request.getParameter("fileName");
        //得到id
        String id = request.getParameter("id");
        //查询数据库，得到下载次数
        int number = fileServer.numberById(Integer.parseInt(id));
        File file = new File(downloadPath + fileName);
        if (!file.exists()) {
//            跳转下载失败页面，提示，该文件不存在
            return;
        }
        //设置下载的文件名
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        fis.close();
//        下载成功下载次数加一再设回数据库
        number = number + 1;
//        调用服务层，修改下载次数
        fileServer.updateNumber(Integer.parseInt(id),number);
    }
}

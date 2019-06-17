package com.cc.contorller;

import com.cc.server.FileServer;
import com.cc.to.DownloadPageOBJ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "downloadServlet", value = "/download")
public class DownloadServlet extends HttpServlet {
    private FileServer fileServer;

    public DownloadServlet() {
        this.fileServer = new FileServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        先得到数据，需要查询的页数，和每页显示的数据一共多少条;从前端发送过来的得到
//        页面
        String pageIndex = req.getParameter("pageIndex");
//        数量
        String number = req.getParameter("number");
//        调用方法，需要把字符串转为int，查询,得到封装后的对象，里面保存了当前页码，总共页码，数据链表
        DownloadPageOBJ paging = fileServer.paging(Integer.parseInt(pageIndex), Integer.parseInt(number));
//        保存到域对象
        req.setAttribute("DOWNLOAD_OBJ", paging);
//        转发页面
        req.getRequestDispatcher("/download.jsp").forward(req, resp);
    }
}

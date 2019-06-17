package com.cc.contorller;

import com.cc.server.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    public StaffServer staffServer;

    public DeleteServlet() {
        this.staffServer = new StaffServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        得到id
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
//        得到当前页码
        String pageIndex = req.getParameter("pageIndex");
        int index = Integer.parseInt(pageIndex);
//          得到页面显示的数据量
        String number = req.getParameter("number");
        int n = Integer.parseInt(number);
        if (staffServer.deleteById(i)) {
//            成功
//            得到成功之后总页码
            int pageNumber = staffServer.getPageNumber(n);
//            判断是否是最后一页的唯一数据，被删的话则跳的删除后的最后一页
            if (index > pageNumber) {
//                是，跳到删除后的最后一页
//                req.getSession().setAttribute("DELETE_TRUE", "true");
                resp.sendRedirect(req.getContextPath() + "/data?number=" + n + "&pageIndex=" + pageNumber);
            }else {
//                不是，跳到删除之前的页码
//                req.getSession().setAttribute("DELETE_TRUE", "true");
                resp.sendRedirect(req.getContextPath() + "/data?number=" + n + "&pageIndex=" + index);
            }


        }else {
//            删除失败，跳到当前页码
//            req.getSession().setAttribute("DELETE_FALSE", "false");
            resp.sendRedirect(req.getContextPath() + "/data?number=" + n + "&pageIndex=" + index);
        }
    }
}

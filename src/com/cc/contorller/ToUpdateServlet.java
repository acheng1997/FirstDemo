package com.cc.contorller;

import com.cc.pojo.Staff;
import com.cc.server.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "toUpdateServlet", value = "/toUpdate")
public class ToUpdateServlet extends HttpServlet {
    private StaffServer staffServer;

    public ToUpdateServlet() {
        this.staffServer = new StaffServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        得到页面传来的数据
//        修改传ID和当前页码
        String id = req.getParameter("id");
        String pageIndex = req.getParameter("pageIndex");
//        删除传页数和当前页码
        String number = req.getParameter("number");
        String pageIndex2 = req.getParameter("pageIndex2");
        if (id == null) {
//            id为空时候说明是添加所以直接跳转，把传来的数据保存到session中
            req.getSession().setAttribute("UPDATE_NUMBER", number);
            req.getSession().setAttribute("UPDATE_INDEX2", pageIndex2);
            resp.sendRedirect(req.getContextPath() + "/update.jsp");
        } else {
//            不为空时候则是修改，需要把传来的数据进行保存
            req.getSession().setAttribute("UPDATE_ID", id);
            req.getSession().setAttribute("UPDATE_INDEX", pageIndex);
            int i = Integer.parseInt(id);
            Staff staff = staffServer.selectById(i);
            req.setAttribute("UPDATE_STAFF", staff);
            req.getRequestDispatcher("/update.jsp").forward(req, resp);
        }
    }
}

package com.cc.contorller;

import com.cc.pojo.Staff;
import com.cc.server.StaffServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {
    private StaffServer staffServer;

    public UpdateServlet() {
        this.staffServer = new StaffServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        在session中得到数据，保存
//        修改的ID，和当前页码
        String update_id = (String) req.getSession().getAttribute("UPDATE_ID");
        String update_index = (String) req.getSession().getAttribute("UPDATE_INDEX");
//        添加的数据，页面显示的数量和当前页码（添加失败时候用）
        String update_number = (String) req.getSession().getAttribute("UPDATE_NUMBER");
        String update_index2 = (String) req.getSession().getAttribute("UPDATE_INDEX2");
//        得到后删除session中的对象
        req.getSession().removeAttribute("UPDATE_ID");
        req.getSession().removeAttribute("UPDATE_INDEX");
        req.getSession().removeAttribute("UPDATE_NUMBER");
        req.getSession().removeAttribute("UPDATE_INDEX2");
        if (update_id == null) {
//            为空时候则是添加
            Staff staff = new Staff();
//            保存页面显示数和失败后跳转到过来之前的页码
            int number = Integer.parseInt(update_number);
            int index = Integer.parseInt(update_index2);
            staff.setName(req.getParameter("name"));
            staff.setSex(req.getParameter("sex"));
            staff.setBasePay(req.getParameter("basePay"));
            staff.setRoyalty(req.getParameter("royalty"));
            staff.setDepartment(req.getParameter("department"));
            staff.setPost(req.getParameter("post"));
            staff.setPhone(req.getParameter("phone"));
            if (staffServer.updateStaff(staff)) {
//              成功，得查到最后一页
                int pageNumber = staffServer.getPageNumber(number);
                req.getSession().setAttribute("INSERT_TRUE", "true");
//              跳转到最后一页
                resp.sendRedirect(req.getContextPath() + "/data?number=4&pageIndex=" + pageNumber);
            } else {
//                失败
                req.getSession().setAttribute("INSERT_FALSER", "false");
//              跳转到当时页
                resp.sendRedirect(req.getContextPath() + "/data?number=4&pageIndex=" + index);
            }
        } else {
//            不为空则是修改
            int id = Integer.parseInt(update_id);
            int index = Integer.parseInt(update_index);
            Staff staff = new Staff();
            staff.setId(id);
            staff.setName(req.getParameter("name"));
            staff.setSex(req.getParameter("sex"));
            staff.setBasePay(req.getParameter("basePay"));
            staff.setRoyalty(req.getParameter("royalty"));
            staff.setDepartment(req.getParameter("department"));
            staff.setPost(req.getParameter("post"));
            staff.setPhone(req.getParameter("phone"));
            if (staffServer.updateStaff(staff)) {
//              成功
                req.getSession().setAttribute("UPDATE_TRUE", "true");
                resp.sendRedirect(req.getContextPath() + "/data?number=4&pageIndex=" + index);
            } else {
                req.getSession().setAttribute("UPDATE_FALSE", "false");
                resp.sendRedirect(req.getContextPath() + "/data?number=4&pageIndex=" + index);
            }

        }
    }
}

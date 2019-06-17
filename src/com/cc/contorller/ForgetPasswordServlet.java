package com.cc.contorller;

import com.cc.pojo.User;
import com.cc.server.UserServer;
import com.cc.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forgetPasswordServlet", value = "/forgetPassword")
public class ForgetPasswordServlet extends HttpServlet {
    private UserServer userServer;

    public ForgetPasswordServlet() {
        this.userServer = new UserServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        先得到表单数据
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
//         再比较用户名和手机号是否一致
//        先查出来
        User user = userServer.selectUserByName(userName);
        if(user == null){
//            说明没有此用户
            req.getSession().setAttribute("UPDATE_PWD_NO", "no");
            resp.sendRedirect(req.getContextPath() + "/pwd.jsp");
            return;
        }
        if (user.getPhone().equals(phone)) {
//            一样说明是本人,修改密码
            String encrypt = MD5Util.encrypt(password);
            if (userServer.updatePassword(user.getId(), encrypt)) {
//                修改成功
                req.getSession().setAttribute("UPDATE_PWD_TRUE", "true");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
//                失败
                req.getSession().setAttribute("UPDATE_PWD_FALSE", "false");
                resp.sendRedirect(req.getContextPath() + "/pwd.jsp");
            }

        } else {
//            不是本人，拒绝修改
            req.getSession().setAttribute("UPDATE_PWD_ERROR", "error");
            resp.sendRedirect(req.getContextPath() + "/pwd.jsp");
        }
    }
}

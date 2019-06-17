package com.cc.contorller;

import com.cc.pojo.User;
import com.cc.server.UserServer;
import com.cc.utils.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserServer userServer;

    public RegisterServlet() {
        this.userServer = new UserServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        得到表单输入
        String parameter = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String pwd = req.getParameter("pwd");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("mail");
//        用一个User对象保存
        String password = MD5Util.encrypt(pwd);
        User user = new User(parameter, nickname, password, phone, mail);
//         注册到数据库中，调用服务方法
        if (userServer.IsRegisterOk(user)) {
            HttpSession session = req.getSession();
            session.setAttribute("REGISTER_SUCCEED", "true");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("REGISTER_ERROR", "true");
            resp.sendRedirect(req.getContextPath() + "/register.jsp");
        }
    }
}

package com.cc.contorller;

import com.cc.pojo.User;
import com.cc.server.UserServer;
import com.cc.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserServer userServer;

    public LoginServlet() {
        this.userServer = new UserServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        直接先判断是否自动登录了，自动登录后验证是否登录成功
//        先写验证登录窗口登录

//        得到表单数据
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("autoLogin");


//        new一个实体对象保存表单数据
        User user = new User(userName, password);
//        req.getRequestDispatcher("/home.jsp").forward(req, resp);

        if (userServer.IsLoginOk(user)) {
//            设置时间
            userServer.updateTime(user.getName());
//            查询用户得到用户信息
            User user1 = userServer.selectUserByName(user.getName());
            user1.setLastTime(user1.getLastTime().substring(0,19));
            user1.setNowTime(user1.getNowTime().substring(0,19));

//            设置昵称
            HttpSession session = req.getSession();
            session.setAttribute("USER_INFO", user1);
//            session.setAttribute("USER_NAME", user.getName());
//            判断是否点击了自动登录
            if ("true".equals(autoLogin)) {
//              有自动登录则保存到cookie里面，先把对象转成字符串
                String json = JsonUtils.objectToJson(user);
                String encode = URLEncoder.encode(json);
                Cookie cookie = new Cookie("AUTO_LOGIN", encode);
                cookie.setMaxAge(3600 * 24 * 30);
                resp.addCookie(cookie);
            }

//          内部转发页面
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("LOGIN_ERROR", "true");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}

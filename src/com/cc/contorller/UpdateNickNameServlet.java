package com.cc.contorller;

import com.cc.pojo.User;
import com.cc.server.UserServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateNickNameServlet",value = "/updateNickNameServlet")
public class UpdateNickNameServlet extends HttpServlet {
    private UserServer userServer;
    public UpdateNickNameServlet(){
        this.userServer = new UserServer();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        先得到id
        String id = req.getParameter("id");
//        再得到表单输入
        String nickName = req.getParameter("nickName");
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setNickName(nickName);
        if(userServer.updateNickName(user)){
//            成功
            User user_info = (User) req.getSession().getAttribute("USER_INFO");
            User user1 = userServer.selectUserByName(user_info.getName());
            req.getSession().setAttribute("USER_INFO", user1);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        }else {
//            失败
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        }
    }
}

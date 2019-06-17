package com.cc.server;

import com.cc.dao.UserDao;
import com.cc.pojo.User;
import com.cc.utils.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServer {
    private UserDao userDao;
    private SimpleDateFormat simpleDateFormat;

    public UserServer() {
        this.userDao = new UserDao();
        this.simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    //          是否注册成功
    public boolean IsRegisterOk(User user) {
//    需要操作数据库，所以需要调用dao层方法
        return userDao.insertUser(user);
    }

    //    是否登录成功
    public boolean IsLoginOk(User user) {
//        需要从数据库中查询，所以需要dao层
        User user1 = userDao.selectUser(user.getName());
        if (user1 != null && !"".equals(user1)) {
            String encrypt = MD5Util.encrypt(user.getPassword());
            return encrypt.equals(user1.getPassword());
        }
        return false;

    }

    //         得到查询的user
    public User selectUserByName(String username) {
        User user = userDao.selectUser(username);
        return user;
    }

    //            修改时间
    public void updateTime(String username) {
//        先查到数据库保存的数据
        User user = userDao.selectUser(username);
//        然后把时间修改
        user.setLastTime(user.getNowTime());
        user.setNowTime(simpleDateFormat.format(new Date()));
        userDao.updateTime(user);
    }

    //    注销
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
//        删除session
        HttpSession session = req.getSession();
        session.removeAttribute("USER_INFO");
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
//                删除cookie
                if ("AUTO_LOGIN".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }

    //    根据id修改头像路径
    public boolean updateProfilePhotoById(User user) {
        return userDao.updateProfilePhotoById(user);
    }

    //    修改密码
    public boolean updatePassword(int id, String password) {
        return userDao.updatePassword(id, password);
    }

//    修改昵称
    public boolean updateNickName(User user){
        return userDao.updateNickNameById(user);
    }
}

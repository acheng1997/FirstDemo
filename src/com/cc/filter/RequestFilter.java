package com.cc.filter;

import com.cc.pojo.User;
import com.cc.server.UserServer;
import com.cc.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//  请求拦截器
public class RequestFilter implements Filter {
    private FilterConfig config;
    private List<String> notFilters;
    private FilterChain chain;
    private UserServer userServer;

    public RequestFilter() {
        this.userServer = new UserServer();
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        先得到配置文件的参数,去掉空格
        String notFilter = config.getInitParameter("notFilter").trim();
//        然后再进行截断,得到参数数组
        String[] jsps = notFilter.split(",");
//        需要得到当前访问的页面,需要把ServletRequest，ServletResponse转为HttpServlet的
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
            resp = (HttpServletResponse) response;
        }
//      得到路径 contextPath 为服务器路径 requeURI 为网页的相对路径
        String contextPath = req.getContextPath();

        String requestURI = req.getRequestURI();

//      requestURI等于contextPath加上参数，所以进行截取
        String substring = requestURI.substring(contextPath.length());


//        遍历数组，看里面是否有默认放行的，如果有就放行
        notFilters = new ArrayList<>();
        for (String jsp : jsps) {
            notFilters.add(jsp);
        }

        //     判断是否直接放行  没有说明不是直接放行的所以要判断是否登录，登录的话session里面的保存的一个属性是不为空的，这个可以作为判断条件
        if (notFilters.contains(substring) || (req.getSession().getAttribute("USER_INFO") != null && !"".equals(req.getSession().getAttribute("USER_INFO")))) {
            chain.doFilter(req, resp);
            return;
        }
        //没有则判断是否有自动登录

        User user = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("AUTO_LOGIN".equals(cookie.getName())) {
                    String userJson = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    user = JsonUtils.jsonToPojo(userJson, User.class);
                }
            }
        }
//        当有自动登录时候，判断是否登录成功
        if (user != null) {
            if (userServer.IsLoginOk(user)) {
//                设置时间
                userServer.updateTime(user.getName());
                User user1 = userServer.selectUserByName(user.getName());
                user1.setLastTime(user1.getLastTime().substring(0,19));
                user1.setNowTime(user1.getNowTime().substring(0,19));
//            设置昵称
                user1.setPassword(null);
                HttpSession session = req.getSession();
                session.setAttribute("USER_INFO", user1);
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }


    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

}

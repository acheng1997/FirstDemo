package com.cc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CodingFilter implements Filter {
    private String coding;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding(coding);
        resp.setContentType("text/html;charset=" + coding);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
        //        得到配置文件的初始化参数
        this.coding = config.getInitParameter("coding");
    }

}

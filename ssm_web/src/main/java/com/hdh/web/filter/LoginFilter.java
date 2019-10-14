package com.hdh.web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private String unFilterPag = "";
    private String unFiltetRequest = "";

    public void init(FilterConfig filterConfig) throws ServletException {
        unFilterPag = filterConfig.getInitParameter("unFilterPag");
        unFiltetRequest = filterConfig.getInitParameter("unFiltetRequest");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        HttpSession session = req.getSession();
        Object ifLogin = session.getAttribute("username");

        if (servletPath.equals(unFilterPag) || servletPath.equals(unFiltetRequest)){
            chain.doFilter(req,res);
        }else {
            if (ifLogin==null){
                req.setAttribute("error","请先登陆后，在进行操作");
                req.getRequestDispatcher(unFilterPag).forward(req,res);
            }else{
                chain.doFilter(req,res);
            }
        }
    }

    public void destroy() {

    }
}

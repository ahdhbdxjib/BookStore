package com.bookstore.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")//拦截所有的psot请求
public class MyEncodingFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //1.设置POST请求中文乱码的问题
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //setHeader只有在HttpServletResponse才有，所以需要强转
        httpServletResponse.setHeader("content-type","text/html;charset=utf-8");


//        System.out.println("拦截请求:" + request);

        //2.解决get请求的中文乱码问题
        //request ： RequestFacade;
        HttpServletRequest hsr = (HttpServletRequest)request;
        if(hsr.getMethod().equalsIgnoreCase("post")){
            //放行请求
            request.setCharacterEncoding("UTF-8");
        }
            chain.doFilter(request, response);



    }

}


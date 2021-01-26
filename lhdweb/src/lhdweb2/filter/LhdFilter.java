package lhdweb2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LhdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        //如果这里出现乱码，可以在这里统一设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //可以在这里控制用户访问权限（说白了对要访问的url做是否拦截的处理）
        String isLogin=(String) req.getSession().getAttribute("islogin");
        System.out.println("loginststus:"+isLogin);
        String sURI=req.getRequestURI();
        //控制没有登录不允许访问lhda
        int pos=sURI.lastIndexOf(".");
        String sTxt=sURI.substring(pos+1);
        System.out.println("suffix:"+sTxt);
        pos=sURI.lastIndexOf("/");
        sURI=sURI.substring(pos+1);
        System.out.println("URI:"+sURI);
        if ((
                !(sTxt.equals("js") || sTxt.equals("css") || sTxt.equals("png") || sURI.equals("login.jsp") || sURI.equals("lhdb") || sURI.equals("register.jsp"))
        )){
            if (isLogin==null){
                req.getSession().setAttribute("error","请先登录。。。");
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
                return;
            }
        }
        chain.doFilter(request,response);
        System.out.println(sURI);
    }

    @Override
    public void destroy() {

    }
}

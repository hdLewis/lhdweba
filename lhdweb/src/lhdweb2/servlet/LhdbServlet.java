package lhdweb2.servlet;

import lhdweb2.service.LhdbService;
import lhdweb2.service.LhdbServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/lhdb"})
public class LhdbServlet extends HttpServlet {
    Logger log=Logger.getLogger(LhdbServlet.class);
    private LhdbService lhdbService =new LhdbServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op=req.getParameter("op");
        PrintWriter out=resp.getWriter();
        if (op==null){
            op="";
        }
        log.debug(op);
        switch (op){
            case "login":
                login(req,resp);
                break;
            case "register":
                register(req,resp);
                break;
            case "check":
                check(req,resp);
                break;
            case "logout":
                logout(req,resp);
                break;
            default:
                break;
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("islogin",null);
        resp.sendRedirect("zsls.zsls");

    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        String username=req.getParameter("username");
        if (lhdbService.check(username)){
            out.print("exist");
        }else {
            out.print("not");
        }
    }



    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out=resp.getWriter();
        String lhdaa1=req.getParameter("lhdaa1");
        String lhdbb1=req.getParameter("lhdbb1");
        if (lhdbService.register(lhdaa1,lhdbb1)){
            out.print("success");
        }else {
            req.setAttribute("error","register failure");
            req.getRequestDispatcher("register.jsp").forward(req,resp);//转发
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        String lhdaa1=req.getParameter("lhdaa");
        String lhdbb1=req.getParameter("lhdbb");
        if (lhdbService.login(lhdaa1,lhdbb1)){
            req.getSession().setAttribute("islogin",lhdaa1);
            resp.sendRedirect("lhd.jsp");
        }else {
            req.getSession().setAttribute("islogin",null);
            req.getSession().setAttribute("error","invalid user or pass");
            resp.sendRedirect("login.jsp");
        }
    }

}

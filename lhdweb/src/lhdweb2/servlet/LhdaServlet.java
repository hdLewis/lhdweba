package lhdweb2.servlet;

import lhdweb2.dao.LhdDao;
import lhdweb2.entity.Lhd;
import lhdweb2.service.LhdService;
import lhdweb2.service.LhdServiceImpl;
import lhdweb2.util.LhdUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = {"/lhd", "/lkg.py"})
public class LhdaServlet extends HttpServlet {
    Logger log = Logger.getLogger(LhdaServlet.class);
    private LhdService lhdService = new LhdServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String op = req.getParameter("op");

        if (op == null) {
            op = "";
        }
        log.debug(op);
        switch (op) {
            case "insert":
                insert(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "check":
                check(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "json":
                json(req, resp);
                break;
            case "ajaxdel":
                ajaxdel(req, resp);
                break;
            default:
                select(req, resp);
                break;
        }
    }

    private void ajaxdel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int lhda = Integer.parseInt(req.getParameter("lhda"));
        PrintWriter out = resp.getWriter();
        if (lhdService.deleteLhd(lhda)) {
            out.print("success");
        } else {
            out.print("failure");
        }
    }

    private void json(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lhdc = req.getParameter("lhdc");
        if (lhdc == null) {
            lhdc = "";
        }
        List<Lhd> lst = lhdService.getLhd(lhdc);
        resp.setContentType("application/json,charset=UTF-8");
        resp.getWriter().print(lst);
    }


    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lhdc = req.getParameter("lhdc");
        if (lhdc == null) {
            lhdc = "";
        }
        List<Lhd> lst = lhdService.getLhd(lhdc);

        req.getSession().setAttribute("lstlhd", lst);
        resp.sendRedirect("showLhd.jsp");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String lhda = req.getParameter("lhda");
        String lhdb = req.getParameter("lhdb");
        String lhdc = req.getParameter("lhdc");
        int oldlhda = Integer.parseInt(req.getParameter("oldlhda"));
        Lhd lhd = new Lhd(Integer.parseInt(lhda), Float.parseFloat(lhdb), lhdc);
        if (lhdService.updateLhd(lhd, oldlhda)) {
            resp.sendRedirect("lhd");
        } else {
            req.setAttribute("error", "upda cte failure");
            req.getRequestDispatcher("updatelhd.jsp").forward(req, resp);
        }

    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int lhda = Integer.parseInt(req.getParameter("lhda"));
        PrintWriter out = resp.getWriter();
        if (lhdService.checkLhd(lhda)) {
            out.print("exist");
        } else {
            out.print("not");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int lhda = Integer.parseInt(req.getParameter("lhda"));
        lhdService.deleteLhd(lhda);
        // String lhda = req.getParameter("lhda");
       // LhdDao.exeUpdate("delete from yournamea where yournamea='" + lhda + "'");
        resp.sendRedirect("lhd");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String lhda = req.getParameter("lhda");
//        String lhdb = req.getParameter("lhdb");
//        String lhdc = req.getParameter("lhdc");
//        Lhd lhd = new Lhd(Integer.parseInt(lhda), Float.parseFloat(lhdb), lhdc);
        Lhd lhd = new Lhd();
       // LhdUtil.setParams(req, lhd);
        org.apache.jasper.runtime.JspRuntimeLibrary.introspect(lhd,req);
        if (lhdService.insertLhd(lhd)) {
            resp.sendRedirect("lhd");
        } else {
            req.setAttribute("error", "insert failure");

            req.getRequestDispatcher("lhd.jsp").forward(req, resp);
        }


    }
}

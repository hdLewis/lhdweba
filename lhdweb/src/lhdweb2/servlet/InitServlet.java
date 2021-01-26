package lhdweb2.servlet;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {

    public void init() throws ServletException {
        String log4j = getInitParameter("lhdd");
        log4j = getServletContext().getRealPath("/") + log4j;
        System.out.println(log4j);
        PropertyConfigurator.configure(log4j);

    }
}

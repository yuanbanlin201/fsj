package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/hello")
public class Dispatcher extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMSG","登录失败，请检查邮箱及密码是否正确！");
//        super.processTemplate("resource/templates/index",req,resp);
        resp.sendRedirect("resource/templates/index.html");
    }
}

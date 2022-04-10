package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/resource/templates/register")
public class Register extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMSG","register failure, please check your input!");
        super.processTemplate("resource/templates/index", req, resp);
        String userName=req.getParameter("userName");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
//        System.out.println("user:"+userName+"\n"+"email:"+email+"\n"+"password:"+password);
        //数据库
        String JDBC_URL = "jdbc:mysql://localhost:3306/mypan?useUnicode=true&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "111111";
        try(Connection conn= DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            try(Statement stm=conn.createStatement()){
                String sql=String.format(
                        "INSERT INTO users(name,password,email) VALUES('%s','%s','%s')",
                        userName,password,email
                );
                stm.executeUpdate(sql);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}

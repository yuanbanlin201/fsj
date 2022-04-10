package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/resource/templates/login")
public class Login extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMSG", "登录失败，请检查邮箱及密码是否正确！");
        super.processTemplate("resource/templates/index", req, resp);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
//        打印到控制台，模拟数据库
//        System.out.println("email:"+email+"\n"+"password:"+password);
        //数据库
        String JDBC_URL = "jdbc:mysql://localhost:3306/mypan?useUnicode=true&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "111111";
        String userName;
        try(Connection conn=DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)){
            try(Statement stm=conn.createStatement()){
                String sql=String.format(
                        "SELECT name FROM users WHERE email ='%s' and password='%s' ",
                        email,password
                );
                try(ResultSet resultSet= stm.executeQuery(sql)){
                    if(resultSet.next()){
                        userName=(String)resultSet.getObject(1);
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}

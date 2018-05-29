package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDAO;
import model.User;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        
        UserDAO dao = new UserDAO();
        User u = dao.getUser(username);
        u.setName(username);
    }
}
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

public class LoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        
	RequestDispatcher rd;
	UserDAO dao = new UserDAO();
        User u = dao.getUser(username);
	
	if (u != null) {
	    if (senha.equals(u.getPassword())) {
		request.setAttribute("user_id", u.getID());
		request.setAttribute("user", u.getLogin());
		request.setAttribute("warning", null);
		rd = request.getRequestDispatcher("WEB-INF/list.jsp");
                rd.forward(request, response);
	    }
	} else {
	    request.setAttribute("user_id", null);
	    request.setAttribute("warning", "Usuário ou senha incorretos");
	    rd = request.getRequestDispatcher("index.jsp");
	    rd.forward(request, response);
	}
	
    }
}
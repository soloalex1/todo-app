package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;


public class MoveUploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = (String) request.getParameter("username");
        String picture = (String) request.getParameter("picture");
                     
        UserDAO ud = new UserDAO();
        User u = ud.getUser(username);
        request.setAttribute("username", username);
        request.setAttribute("picture", picture);       
          
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/picture.jsp");
        rd.forward(request, response);
    }
    
}

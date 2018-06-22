package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

// invalida a sessão e encaminha o usuário para a página inicial

public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("warning", "Sessão encerrada");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
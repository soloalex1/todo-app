/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogoutServlet extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("warning", "Sessão encerrada");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

}

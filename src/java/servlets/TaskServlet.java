package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class TaskServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	// nesse servlet é obtida e salva a lista de tarefas
	
	// obter username da sessão atual
	HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
	
	// obter string tasks, formatadas como XML
        String tasks = request.getParameter("tasks");
        
	// inicialização dos DAOs de usuário e tarefa
        UserDAO ud = new UserDAO();
        TaskDAO td = new TaskDAO();
	
	// cria o XMLParser e faz a decodificação da string
	XMLParser xmp = new XMLParser();
	ArrayList<Task> tl = xmp.stringToTaskList(tasks);
        
	// substitui as tarefas do usuário com a ArrayList<Task> gerada
	User u = ud.getUser(username);
	if (u != null) {
	    td.setTasks(u, tl);
	}
    }
}
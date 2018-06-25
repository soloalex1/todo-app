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
import jdk.internal.org.xml.sax.InputSource;
import model.Task;
import model.TaskDAO;
import model.User;
import model.UserDAO;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class TaskServlet extends HttpServlet {

    @Override
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
	
	// inicializa fábricas e objeto Document para tratamento do XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
	
	// transforma string tasks no objeto Document
	// TODO: não funciona com caracteres UTF-8 (é, ô, à etc)
        try {
            builder = factory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(tasks.getBytes());
            doc = builder.parse(is);
	    doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
        
	// navega pelo Document e obtem dados das tags para gerar o novo ArrayList<Task>
        NodeList nl = doc.getElementsByTagName("task");
        ArrayList<Task> tl = new ArrayList<>();
        for (int i=0; i< nl.getLength(); i++) {
            
            Node node = nl.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Task t = new Task();
                t.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                t.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                boolean stat = false;
                if (element.getElementsByTagName("stat").item(0).getTextContent().equals("true")) {
                    stat = true;
                }
                t.setStat(stat);
                tl.add(t);
            }
        }
        
	// substitui as tarefas do usuário com a ArrayList<Task> gerada
	User u = ud.getUser(username);
	if (u != null) {
	    td.setTasks(u, tl);
	}
    }
}